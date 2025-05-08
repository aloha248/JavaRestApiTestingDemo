package ro.shiftleft.apiTestingDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import ro.shiftleft.apiTestingDemo.apiClient.ApiClient;

@SpringBootConfiguration
public class TestConfig {

    @Value("${baseUrl:https://jsonplaceholder.typicode.com}")
    private String defaultBaseUrl;

    @Bean
    public ApiClient apiClient() {
        String baseUrl = System.getenv("API_BASE_URL");
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = defaultBaseUrl;
        }
        return new ApiClient(baseUrl);
    }
}
