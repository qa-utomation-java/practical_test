package com.practical.tests;

import com.practical.framework.entity.PaymentWrapper;
import com.practical.framework.rest.PaymentRest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static com.practical.framework.entity.PaymentMethod.CREDITCARD;
import static com.practical.framework.entity.PaymentMethod.IDEAL;
import static com.practical.framework.entity.PaymentMethod.PAYPAL;
import static com.practical.framework.matchers.ResponseMatchers.hasStatus;
import static com.practical.framework.matchers.ResponseMatchers.withPaymentAmount;
import static com.practical.framework.matchers.ResponseMatchers.withPaymentError;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;


/**
 * Created by sergey on 5/27/16.
 */
public class BuyCoinsTest {

    private PaymentRest paymentRest = new PaymentRest();

    @Test
    public void shouldBeZeroPaymentIfMethodCCAndNoAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(0.0));
    }

    @Test
    public void shouldBe1UahPaymentIfMethodIdealAndNoAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(IDEAL);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(1.0));
    }

    @Test
    public void shouldBeErrorPaymentMsgIfMethodPaypalAndNoAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(PAYPAL);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentError("Your amount is too low"));
    }

}
