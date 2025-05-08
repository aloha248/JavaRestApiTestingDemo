package ro.shiftleft.apiTestingDemo.assertions;

import org.assertj.core.api.AbstractAssert;

public class ObjectAsserts<T> extends AbstractAssert<ObjectAsserts<T>, T> {

    public ObjectAsserts(T actual) {
        super(actual, ObjectAsserts.class);
    }

    public static <T> ObjectAsserts<T> assertThat(T actual) {
        return new ObjectAsserts<>(actual);
    }

    public ObjectAsserts<T> isEquivalentTo(T expected) {
        return isEquivalentTo(expected, "id");
    }

    public ObjectAsserts<T> isEquivalentTo(T expected, String... ignoredFields) {
        isNotNull();
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields(ignoredFields)
                .isEqualTo(expected);
        return this;
    }
}