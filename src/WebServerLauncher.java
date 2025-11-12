import controller.FrontController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class WebServerLauncher {

    private static final FrontController frontController = new FrontController();

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

                String httpResponse = frontController.toController();

                clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            }

        }

    }
}