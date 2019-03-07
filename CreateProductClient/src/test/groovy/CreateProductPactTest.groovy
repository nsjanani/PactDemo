import au.com.dius.pact.consumer.PactVerified$
import au.com.dius.pact.consumer.VerificationResult
import au.com.dius.pact.consumer.groovy.PactBuilder
import groovyx.net.http.RESTClient
import org.junit.Test

/**
 * Created by janani.sampathkumar on 09/02/2017.
 */
class CreateProductPactTest {
  /*  @Test
    void "Pact Test for CreateProduct with ProductCatelogue"() {

        def createProductService = new PactBuilder()
        createProductService {
            serviceConsumer 'CreateProduct'
            hasPactWith 'ProductCatalogue'
            port 4567

            given('There may be some products already available in Product Catalogue')
            uponReceiving('Create a new product in Product Catalogue')
            withAttributes(method: 'post', path: '/products', query: [name: 'ipad', price: '319.45'])

            willRespondWith(
                    status: 200,
                    headers: ['Content-Type': 'text/html']
            )
            withBody {
                id regexp('[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}','e8cda07e-849f-49c2-94d6-aaa5c4ab7fcd')
                name 'ipad'
                price 319.45
            }
        }

        VerificationResult result = createProductService.run {
            def client = new RESTClient('http://localhost:4567/')
            def response = client.post(path: '/products', query: [name: 'ipad', price: '319.45'])

            assert response.status == 200
            assert response.contentType == 'text/html'

            def data = response.data.text()
            assert data == '{\n' +
                    '    "id": "e8cda07e-849f-49c2-94d6-aaa5c4ab7fcd",\n' +
                    '    "name": "ipad",\n' +
                    '    "price": 319.45\n' +
                    '}'
        }
        print result
        assert result == PactVerified$.MODULE$
    }*/
}
