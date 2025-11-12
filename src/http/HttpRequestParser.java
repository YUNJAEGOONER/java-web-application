package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HttpRequestParser {

    public Optional<HttpRequest> parseHttpRequest(BufferedReader br) throws IOException {

        //HTTP 요청을 한 줄씩 끊어 읽기
        String line = br.readLine();

        if (line == null) {
            return Optional.empty();
        }

        //startline
        String[] startline = line.split(" ");
        String httpMethod = startline[0];
        String requestPath = startline[1];

        //headers
        Map<String, String> headers = new HashMap<>();
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            String[] header = line.split(": ");
            headers.put(header[0], header[1]);
        }

        //body
        int contentLength = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < contentLength; i++) {
            body.append((char) br.read());
        }
        String decodedBody = URLDecoder.decode(body.toString(), StandardCharsets.UTF_8); // UTF-8로 디코딩
        return Optional.of(new HttpRequest(httpMethod, requestPath, headers, decodedBody));

    }

}
