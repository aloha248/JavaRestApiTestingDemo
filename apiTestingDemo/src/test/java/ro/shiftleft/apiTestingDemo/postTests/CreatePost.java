package ro.shiftleft.apiTestingDemo.postTests;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.shiftleft.apiTestingDemo.TestBase;
import ro.shiftleft.apiTestingDemo.assertions.HttpResponseAsserts;
import ro.shiftleft.apiTestingDemo.constants.Endpoints;
import ro.shiftleft.apiTestingDemo.models.posts.Post;
import ro.shiftleft.apiTestingDemo.utils.JsonUtils;

import java.io.IOException;
import java.net.http.HttpResponse;

import static ro.shiftleft.apiTestingDemo.assertions.ObjectAsserts.assertThat;

@SpringBootTest
public class CreatePost extends TestBase {

    private static final Logger logger = LogManager.getLogger(CreatePost.class);

    @Test
    void createNewPost() throws IOException, InterruptedException {
        // Create a Post object using the builder
        Post newPost = Post.builder()
                .title(faker.book().title())
                .body(faker.lorem().paragraph(3))
                .userId(faker.number().numberBetween(10, 1000))
                .build();

        // Perform a POST request to the /posts endpoint
        HttpResponse response = apiClient.post(Endpoints.POSTS, newPost);

        // Assert the response status code is 201 (Created)
        assertThat(response.statusCode())
                .isEqualTo(HttpStatus.SC_CREATED);

        HttpResponseAsserts.assertThat(response)
                .hasStatusCode(201);

        // Deserialize the response body to a Post object
        Post createdPost = JsonUtils.deserialize(response, Post.class);

        // Assert that the fields of newPost and createdPost are equivalent
        assertThat(createdPost)
                .isEquivalentTo(newPost);
    }
}