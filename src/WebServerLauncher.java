import controller.FrontController;
import http.HttpRequest;
import http.HttpRequestParser;
import http.HttpResponse;
import http.HttpResponseBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

public class WebServerLauncher {

    private static final FrontController frontController = new FrontController();
    private static final HttpRequestParser httpRequestParser = new HttpRequestParser();
    private static final HttpResponseBuilder HTTP_RESPONSE_BUILDER = new HttpResponseBuilder();

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("[info]Listening for connection on port 8080...");

        while (true){
            try(Socket clientSocket = serverSocket.accept()){
                InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                Optional<HttpRequest> optionalHttpRequest = httpRequestParser.parseHttpRequest(bufferedReader);

                if(optionalHttpRequest.isEmpty()){
                    continue;
                }

                HttpResponse httpResponse = frontController.toController(optionalHttpRequest.get());
                String response = HTTP_RESPONSE_BUILDER.httpResponseToString(httpResponse);
                clientSocket.getOutputStream().write(response.getBytes("UTF-8"));
            }

        }

    }
}