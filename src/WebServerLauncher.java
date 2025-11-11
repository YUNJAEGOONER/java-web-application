import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class WebServerLauncher {
    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("[info]Listening for connection on port 8080...");

        while (true){
            try(Socket clientSocket = serverSocket.accept()){
                InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //HTTP 요청을 한 줄씩 끊어 읽기
                String line = bufferedReader.readLine();

                if(line == null){
                    continue;
                }

                //startline
                String [] startline = line.split(" ");
                String httpMethod = startline[0];
                String requestPath = startline[1];

                //headers
                Map<String, String> headers = new HashMap<>();
                while ((line = bufferedReader.readLine()) != null){
                    if(line.isEmpty()){
                        break;
                    }
                    String [] header = line.split(": ");
                    headers.put(header[0], header[1]);
                }

                String responseBody = "";

                if(httpMethod.equals("GET") && requestPath.equals("/home")){
                    responseBody = "<h1>레이싱 게임</h1>";
                }

                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                        "Content-Length: " + responseBody.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "\r\n" +
                        responseBody;

                clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            }

        }


    }
}