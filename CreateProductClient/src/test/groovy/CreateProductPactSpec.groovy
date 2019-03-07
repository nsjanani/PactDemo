import au.com.dius.pact.consumer.PactVerified$
import au.com.dius.pact.consumer.VerificationResult
import au.com.dius.pact.consumer.groovy.PactBuilder
import groovyx.net.http.RESTClient
import spock.lang.Specification

/**
 * Created by janani.sampathkumar on 09/02/2017.
 */
class CreateProductPactSpec extends Specification {

    private final createProductService = new PactBuilder()

    void setup() {
        createProductService {
            serviceConsumer 'CreateProduct'
            hasPactWith 'ProductCatalogue'
            port 4567
        }
    }

    def "Verify pact for a create product service"() {
        given:
        createProductService {
            given('There may be some products already available in Product Catalogue')
            uponReceiving('Create a new product in Product Catalogue')
            withAttributes(method: 'post', path: '/products', query: [name: 'macbook', price: '3319.45', stock:'3'])
            willRespondWith(
                    status: 200,
                    headers: ['Content-Type': 'application/json']
            )
            withBody {
                id regexp('[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}','e8cda07e-849f-49c2-94d6-aaa5c4ab7fcd')
                name 'macbook'
                price 3319.45
                stock 3
            }
        }

        when:
        createProductService.buildInteractions()

        then:
        VerificationResult result = createProductService.run {
            def client = new RESTClient('http://localhost:4567/')
            def response = client.post(path: '/products', query: [name: 'macbook', price: '3319.45', stock:'3'])

            assert response.status == 200
            assert response.contentType == 'application/json'

            def data = response.data;
            String responseData = String.valueOf(data)
            assert responseData == '{id=e8cda07e-849f-49c2-94d6-aaa5c4ab7fcd, name=macbook, price=3319.45, stock=3}'
        }
        assert result == PactVerified$.MODULE$
        assert createProductService.interactions.size() == 1
        print result
    }
}
