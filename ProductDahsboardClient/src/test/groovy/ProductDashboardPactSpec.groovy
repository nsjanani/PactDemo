import au.com.dius.pact.consumer.PactVerified$
import au.com.dius.pact.consumer.VerificationResult
import au.com.dius.pact.consumer.groovy.PactBuilder
import au.com.dius.pact.model.Pact
import groovyx.net.http.RESTClient
import spock.lang.Specification

/**
 * Created by janani.sampathkumar on 09/02/2017.
 */
class ProductDashboardPactSpec extends Specification {

    private final productDashboardService = new PactBuilder()

    void setup() {
        productDashboardService {
            serviceConsumer 'ProductDashboard'
            hasPactWith 'ProductCatalogue'
            port 4567
        }
    }

    def "Verify pact for get product by id"() {
        given:
        productDashboardService {
            given('There is a product with id - 64b2a6a4-07e4-4dbd-9d66-74bbb911fd94 exist in Product Catalogue')
            uponReceiving('Create a new product in Product Catalogue')
            withAttributes(method: 'get', path: '/products/64b2a6a4-07e4-4dbd-9d66-74bbb911fd94')
            willRespondWith(
                    status: 200,
                    headers: ['Content-Type': 'application/json']
            )
            withBody {
                id '64b2a6a4-07e4-4dbd-9d66-74bbb911fd94'
                name 'go'
                price 23.23
                stock 2
            }
        }

        when:
        productDashboardService.buildInteractions()

        then:
        VerificationResult result = productDashboardService.run {
            def client = new RESTClient('http://localhost:4567/')
            def response = client.get(path: '/products/64b2a6a4-07e4-4dbd-9d66-74bbb911fd94')

            assert response.status == 200
            assert response.contentType == 'application/json'

            def data = response.data;
            String responseData = String.valueOf(data)
            assert responseData == '{id=64b2a6a4-07e4-4dbd-9d66-74bbb911fd94, name=go, price=23.23, stock=2}'
        }
        assert result == PactVerified$.MODULE$
        assert productDashboardService.interactions.size() == 1
        print result
    }

   /* def "Verify pact for get all products"() {
        given:
        productDashboardService {
            given('There may be some products already available in Product Catalogue')
            uponReceiving('Create a new product in Product Catalogue')
            withAttributes(method: 'get', path: '/products')
            willRespondWith(
                    status: 200,
                    headers: ['Content-Type': 'application/json']
            )
            withBody {
                id regexp('[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}','e8cda07e-849f-49c2-94d6-aaa5c4ab7fcd')
                name regexp(~/\w+/,'sony')
                price regexp(generate :"02/11/2013", matcher:/\d{2}\/\d{2}\/\d{4}/)
            }
        }

        when:
        productDashboardService.buildInteractions()

        then:
        VerificationResult result = productDashboardService.run {
            def client = new RESTClient('http://localhost:4567/')
            def response = client.get(path: '/products')

            assert response.status == 200
            assert response.contentType == 'application/json'

            def data = response.data;
            String responseData = String.valueOf(data)
            assert responseData == '{id=5929465b-f734-4885-a356-7607241940b3, name=sony, price=1234.45}'
        }
        assert result == PactVerified$.MODULE$
        assert productDashboardService.interactions.size() == 1
        print result
    }*/
}
