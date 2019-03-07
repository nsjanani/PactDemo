package Utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by janani.sampathkumar on 08/02/2017.
 */
public class JsonUtil {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }

}
