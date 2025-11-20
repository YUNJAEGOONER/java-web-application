package http;

import java.util.Map;

public record HttpResponse(
        String version,
        String status,
        Map<String, String> headers,
        String body
)
{}
