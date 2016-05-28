package com.practical.tests;

import com.practical.framework.entity.PaymentWrapper;
import com.practical.framework.rest.PaymentRest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static com.practical.framework.matchers.ResponseMatchers.hasStatus;
import static com.practical.framework.matchers.ResponseMatchers.withPaymentAmount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by sergey on 28.05.16.
 */
public class CoinsTest {

    private PaymentRest paymentRest = new PaymentRest();

    @Test
    public void shouldBeRightAmountIfCoinsLessThen5() {
        ResponseEntity<PaymentWrapper> response = paymentRest.coins(4);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(2.0));
    }

    @Test
    public void shouldBeRightAmountIfCoinsMoreThen5() {
        ResponseEntity<PaymentWrapper> response = paymentRest.coins(6);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(2.9));
    }

    @Test
    public void shouldBeRightAmountIfCoinsMoreThen10() {
        ResponseEntity<PaymentWrapper> response = paymentRest.coins(11);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(4.8));
    }

    @Test
    public void shouldBeRightAmountIfCoinsMoreThen20() {
        ResponseEntity<PaymentWrapper> response = paymentRest.coins(21);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(7.7));
    }

    @Test
    public void shouldBeRightAmountIfCoinsMoreThen40() {
        ResponseEntity<PaymentWrapper> response = paymentRest.coins(41);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(11.65));
    }

    @Test
    public void shouldBeRightAmountIfCoinsMoreThan60() {
        ResponseEntity<PaymentWrapper> response = paymentRest.coins(61);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(14.6));
    }

    @Test
    public void shouldBeRightAmountIfCoinsIs0() {
        ResponseEntity<PaymentWrapper> response = paymentRest.coins(0);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(0.0));
    }

    @Test
    public void shouldBeRightAmountIfCoinsIsMaxInteger() {
        ResponseEntity<PaymentWrapper> response = paymentRest.coins(Integer.MAX_VALUE);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(10014.400000000001));
    }
}
