package ro.shiftleft.apiTestingDemo.postTests;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.shiftleft.apiTestingDemo.TestBase;
import ro.shiftleft.apiTestingDemo.assertions.HttpResponseAsserts;
import ro.shiftleft.apiTestingDemo.apiClient.Endpoints;
import ro.shiftleft.apiTestingDemo.models.posts.Post;
import ro.shiftleft.apiTestingDemo.utils.JsonUtils;

import java.io.IOException;
import java.net.http.HttpResponse;

@SpringBootTest
public class GetPostsTest extends TestBase {

    private static final Logger logger = LogManager.getLogger(GetPostsTest.class);

    @Test
    void getAllPosts() throws IOException, InterruptedException {
        // Perform a GET request to the /posts endpoint
        HttpResponse<String> response = apiClient.get(Endpoints.POSTS);

        // Assert the response status code is 200 (OK)
        HttpResponseAsserts.assertThat(response)
                .hasStatusCode(HttpStatus.SC_OK);

        // Deserialize the response body to a list of Post objects
        var posts = JsonUtils.deserializeList(response, Post.class);

        // Assert that the list of posts is not empty
        org.assertj.core.api.Assertions.assertThat(posts)
                .isNotEmpty();
    }
}