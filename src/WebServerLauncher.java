import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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

                while (!line.isEmpty()){
                    System.out.println(line);
                    line = bufferedReader.readLine();
                }

                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "Hello World!";
                clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            }

        }


    }
}