package controller;

import java.nio.charset.StandardCharsets;
import view.View;

public class ViewController implements Controller{

    @Override
    public String doLogic() {
        return createResponse();
    }

    @Override
    public String getName() {
        return "ViewController";
    }

    private String createResponse(){
        String responseBody = View.home_view;

        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: " + responseBody.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "\r\n" +
                responseBody;

        return httpResponse;
    }

}
