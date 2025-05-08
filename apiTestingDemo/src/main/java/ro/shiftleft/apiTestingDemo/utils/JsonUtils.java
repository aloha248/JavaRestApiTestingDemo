package ro.shiftleft.apiTestingDemo.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

public class JsonUtils {

    private static final ObjectMapper objectMapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    public static <T> T deserialize(HttpResponse<String> response, Class<T> clazz) throws IOException {
        if (response == null || response.body() == null || response.body().isEmpty()) {
            throw new IllegalArgumentException("Response must not be null or empty");
        }

        return objectMapper.readValue(response.body(), clazz);
    }

    public static <T> List<T> deserializeList(HttpResponse<String> response, Class<T> clazz) throws IOException {
        if (response == null || response.body() == null || response.body().isEmpty()) {
            throw new IllegalArgumentException("Response must not be null or empty");
        }

        return objectMapper.readValue(
                response.body(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz)
        );
    }
}
