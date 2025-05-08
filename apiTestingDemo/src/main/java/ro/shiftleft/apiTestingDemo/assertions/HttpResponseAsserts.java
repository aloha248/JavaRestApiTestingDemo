package ro.shiftleft.apiTestingDemo.assertions;

import org.assertj.core.api.AbstractAssert;

import java.net.http.HttpResponse;

public class HttpResponseAsserts extends AbstractAssert<HttpResponseAsserts, HttpResponse<?>> {

    public HttpResponseAsserts(HttpResponse<?> actual) {
        super(actual, HttpResponseAsserts.class);
    }

    public static HttpResponseAsserts assertThat(HttpResponse<?> actual) {
        return new HttpResponseAsserts(actual);
    }

    public HttpResponseAsserts hasStatusCode(int expectedStatusCode) {
        isNotNull();
        if (actual.statusCode() != expectedStatusCode) {
            failWithMessage("Expected status code to be <%d> but was <%d>", expectedStatusCode, actual.statusCode());
        }
        return this;
    }
}
