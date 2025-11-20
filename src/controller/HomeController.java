package controller;

import http.HttpRequest;
import http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class HomeController implements Controller{

    @Override
    public HttpResponse doLogic(HttpRequest httpRequest) {
        return createResponse();
    }

    private HttpResponse createResponse(){
        try{
            String responseBody = Files.readString(Paths.get("./src/resources/home.html"));

            String version = "HTTP/1.1";
            String status = "200 OK";

            Map<String, String> headers = new HashMap<>();

            headers.put("Content-Length", Integer.toString(responseBody.getBytes(StandardCharsets.UTF_8).length));
            headers.put("Content-Type", "text/html; charset=UTF-8");

            return new HttpResponse(version, status, headers, responseBody);

        }
        catch (Exception e){
            System.out.print("ERROR");
            return null;
        }

    }

}
