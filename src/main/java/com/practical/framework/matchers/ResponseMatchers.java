package com.practical.framework.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by sergey on 5/27/16.
 */
public class ResponseMatchers {

    private ResponseMatchers() {
    }

    public static BaseMatcher<ResponseEntity<?>> hasStatus(final HttpStatus status) {
        return new TypeSafeMatcher<ResponseEntity<?>>() {

            @Override
            protected boolean matchesSafely(ResponseEntity<?> item) {
                return item.getStatusCode().equals(status);
            }

            @Override
            protected void describeMismatchSafely(ResponseEntity<?> item, Description mismatchDescription) {
                mismatchDescription.appendText("was ").appendValue(item.getStatusCode());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("status code ").appendValue(status);
            }
        };
    }
}
