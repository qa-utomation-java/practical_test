package com.practical.framework.matchers;

import com.practical.framework.entity.PaymentWrapper;
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

    public static BaseMatcher<ResponseEntity<PaymentWrapper>> withPaymentAmount(final double amount) {
        return new TypeSafeMatcher<ResponseEntity<PaymentWrapper>>() {

            @Override
            protected boolean matchesSafely(ResponseEntity<PaymentWrapper> item) {
                return item != null && item.getBody().getPayment().getAmount().equals(amount);
            }

            @Override
            protected void describeMismatchSafely(ResponseEntity<PaymentWrapper> item, Description mismatchDescription) {
                mismatchDescription.appendText("was ").appendValue(item.getBody().getPayment().getAmount());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("payment amount ").appendValue(amount);
            }
        };
    }

    public static BaseMatcher<ResponseEntity<PaymentWrapper>> withPaymentError(final String errorMsg) {
        return new TypeSafeMatcher<ResponseEntity<PaymentWrapper>>() {

            @Override
            protected boolean matchesSafely(ResponseEntity<PaymentWrapper> item) {
                if(item == null || item.getBody().getPayment().getError() == null){
                    return false;
                }
                return item.getBody().getPayment().getError().equals(errorMsg);
            }

            @Override
            protected void describeMismatchSafely(ResponseEntity<PaymentWrapper> item, Description mismatchDescription) {
                mismatchDescription.appendText("was ").appendValue(item.getBody().getPayment().getError());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("payment error ").appendValue(errorMsg);
            }
        };
    }
}
