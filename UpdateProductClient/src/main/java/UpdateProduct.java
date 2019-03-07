import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by janani.sampathkumar on 09/02/2017.
 */
public class UpdateProduct {

    public static void main(String[] args) {
        try {
            String productId = "597f168d-b795-4b35-89b1-21a4da091ca9";
            String paramName = "go";
            String paramPrice = "23.23";
            String paramStock = "3";

            URL url = new URL("http://localhost:4567/products/"+ productId + "?name=" + paramName + "&price=" + paramPrice + "&stock=" + paramStock);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
