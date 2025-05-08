package ro.shiftleft.apiTestingDemo;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import ro.shiftleft.apiTestingDemo.apiClient.ApiClient;

@SpringBootConfiguration
public class TestConfig {

    @Bean
    public ApiClient apiClient() {
        return new ApiClient("https://jsonplaceholder.typicode.com");
    }
}
