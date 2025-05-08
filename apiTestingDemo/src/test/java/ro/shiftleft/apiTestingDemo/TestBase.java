package ro.shiftleft.apiTestingDemo;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.shiftleft.apiTestingDemo.apiClient.ApiClient;
import ro.shiftleft.apiTestingDemo.utils.LoggingConfigurator;

@SpringBootTest
public abstract class TestBase {

    protected static final Faker faker = new Faker();

    @Autowired
    protected ApiClient apiClient;

    protected Logger logger;

    @BeforeEach
    void setUp() throws Exception {
        logger = LoggingConfigurator.configureLogger(this.getClass());
        logger.info("Test setup completed.");
    }

    @AfterEach
    void tearDown() {
        logger.info("Test teardown completed.");
    }
}