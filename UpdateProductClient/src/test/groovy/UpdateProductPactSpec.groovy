import au.com.dius.pact.consumer.PactVerified$
import au.com.dius.pact.consumer.VerificationResult
import au.com.dius.pact.consumer.groovy.PactBuilder
import groovyx.net.http.RESTClient
import spock.lang.Specification

/**
 * Created by janani.sampathkumar on 09/02/2017.
 */
class UpdateProductPactSpec extends Specification {

    private final updateProductService = new PactBuilder()

    void setup() {
        updateProductService {
            serviceConsumer 'UpdateProduct'
            hasPactWith 'ProductCatalogue'
            port 4567
        }
    }

    def "Verify pact for a update product service"() {
        given:
        updateProductService {
            given('There is a product with id - 64b2a6a4-07e4-4dbd-9d66-74bbb911fd94 exist in Product Catalogue')
            uponReceiving('Update a product in Product Catalogue')
            withAttributes(method: 'put', path: '/products/64b2a6a4-07e4-4dbd-9d66-74bbb911fd94', query: [name: 'go1', price: '23.23', stock: '3'])
            willRespondWith(
                    status: 200,
                    headers: ['Content-Type': 'application/json']
            )
            withBody {
                id ('64b2a6a4-07e4-4dbd-9d66-74bbb911fd94')
                name 'go1'
                price 23.23
                stock 3
            }
        }

        when:
        updateProductService.buildInteractions()

        then:
        VerificationResult result = updateProductService.run {
            def client = new RESTClient('http://localhost:4567/')
            def response = client.put(path: '/products/64b2a6a4-07e4-4dbd-9d66-74bbb911fd94', query: [name: 'go1', price: '23.23', stock: '3'])

            assert response.status == 200
            assert response.contentType == 'application/json'

            def data = response.data;
            String responseData = String.valueOf(data)
            assert responseData == '{id=64b2a6a4-07e4-4dbd-9d66-74bbb911fd94, name=go1, price=23.23, stock=3}'
        }
        assert result == PactVerified$.MODULE$
        assert updateProductService.interactions.size() == 1
        print result
    }
}
