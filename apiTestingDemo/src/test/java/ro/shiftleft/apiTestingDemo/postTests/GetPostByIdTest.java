package ro.shiftleft.apiTestingDemo.postTests;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.shiftleft.apiTestingDemo.TestBase;
import ro.shiftleft.apiTestingDemo.assertions.HttpResponseAsserts;
import ro.shiftleft.apiTestingDemo.apiClient.Endpoints;
import ro.shiftleft.apiTestingDemo.models.posts.Post;
import ro.shiftleft.apiTestingDemo.utils.CollectionUtils;
import ro.shiftleft.apiTestingDemo.utils.JsonUtils;

import java.io.IOException;

import static ro.shiftleft.apiTestingDemo.assertions.ObjectAsserts.assertThat;

@SpringBootTest
public class GetPostByIdTest extends TestBase {

    private Post existingPost;

    @BeforeEach
    void setup() throws IOException, InterruptedException {

        var response = apiClient.get(Endpoints.POSTS);
        this.existingPost = CollectionUtils
                .getRandomItem(JsonUtils.deserializeList(response, Post.class));
    }

    @Test
    void getPostById() throws IOException, InterruptedException {
        // Perform a GET request to the /posts/{id} endpoint
        var response = apiClient.get(Endpoints.POSTS + "/" + existingPost.getId());

        // Assert the response status code is 200 (OK)
        HttpResponseAsserts.assertThat(response)
                .hasStatusCode(HttpStatus.SC_OK);

        // Deserialize the response body to a Post object
        Post fetchedPost = JsonUtils.deserialize(response, Post.class);

        // Assert that the post ID matches the requested ID
        assertThat(fetchedPost)
                .isEquivalentTo(existingPost);
    }
}