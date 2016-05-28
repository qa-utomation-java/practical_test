package com.practical.tests;

import com.practical.framework.entity.PaymentWrapper;
import com.practical.framework.rest.PaymentRest;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import static com.practical.framework.entity.PaymentMethod.CREDITCARD;
import static com.practical.framework.matchers.ResponseMatchers.hasStatus;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;


/**
 * Created by sergey on 5/27/16.
 */
public class BuyCoinsTest {

    private PaymentRest paymentRest = new PaymentRest();

    @Test
    public void shouldBeZeroPaymentIfNoAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD);
        assertThat(response, hasStatus(OK));
    }
}
