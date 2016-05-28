package com.practical.tests;

import com.practical.framework.entity.PaymentWrapper;
import com.practical.framework.rest.PaymentRest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static com.practical.framework.entity.PaymentMethod.CREDITCARD;
import static com.practical.framework.entity.PaymentMethod.PAYPAL;
import static com.practical.framework.matchers.ResponseMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;


/**
 * Created by sergey on 5/27/16.
 */
public class BuyCoinsTest {

    private PaymentRest paymentRest = new PaymentRest();


    @Test
    public void shouldBeErrorPaymentMsgIfMethodPaypalAndNoAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(PAYPAL);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentError("Your amount is too low"));
    }

    @Test
    public void shouldBeAmountIfMethodPaypalAndAmountSumMore20Uah() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(PAYPAL,116);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(20.1));
    }

    @Test
    public void shouldBeAmountIfMethodPaypalAndAmountSumLess20Uah() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(PAYPAL,114);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentError("Your amount is too low"));
    }

    @Test
    public void shouldBeZeroPaymentIfMethodCCAndNoAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(0.0));
    }

    @Test
    public void shouldBePaymentIfMethodCCAndAmountItem() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD,1);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(0.51));
    }





}
