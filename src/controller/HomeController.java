package controller;

import http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeController implements Controller{

    @Override
    public String doLogic(HttpRequest httpRequest) {
        return createResponse();
    }

    private String createResponse(){
        try{
            String responseBody = Files.readString(Paths.get("./src/resources/home.html"));
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Length: " + responseBody.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "\r\n" +
                    responseBody;
            return httpResponse;

        }
        catch (Exception e){
            System.out.print("ERROR");
            return null;
        }

    }

}
