package http;
import java.util.Map;

public class HttpResponseBuilder {

    public String httpResponseToString(HttpResponse httpResponse){
        StringBuilder sb = new StringBuilder();

        //Status Line
        sb.append(httpResponse.version()).append(" ").append(httpResponse.status()).append('\n');

        //headers
        Map<String, String> headers = httpResponse.headers();
        for(String key : headers.keySet()){
            sb.append(key).append(": ").append(headers.get(key)).append('\n');
        }

        sb.append('\n');

        //body
        sb.append(httpResponse.body());

        return sb.toString();
    }

}
