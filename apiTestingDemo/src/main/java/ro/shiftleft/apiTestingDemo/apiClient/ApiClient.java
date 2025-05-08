package ro.shiftleft.apiTestingDemo.apiClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ro.shiftleft.apiTestingDemo.constants.Endpoints;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiClient {
    private static final Logger logger = LogManager.getLogger(ApiClient.class);

    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .build();
    }

    public HttpResponse<String> get(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .GET()
                .build();

        return execute(request);
    }

    public HttpResponse<String> get(Endpoints endpoint) throws IOException, InterruptedException {
        return get(endpoint.toString());
    }

    public HttpResponse<String> post(String endpoint, Object requestBody) throws IOException, InterruptedException {
        String jsonBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody);
        logger.info("Request body:\r\n{}", jsonBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return execute(request);
    }

    public HttpResponse<String> post(Endpoints endpoint, Object requestBody) throws IOException, InterruptedException {
        return post(endpoint.toString(), requestBody);
    }

    public HttpResponse<String> execute(HttpRequest request) throws IOException, InterruptedException {
        logger.info("Executing request: {} @ {}", request.method(), request.uri());
        long startTime = System.currentTimeMillis();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        long endTime = System.currentTimeMillis();
        long responseTimeInMilliseconds = endTime - startTime;

        logger.info("Response status: {} in {}ms \r\n{}", response.statusCode(), responseTimeInMilliseconds, response.body());
        return response;
    }
}