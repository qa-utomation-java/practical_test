package com.practical.tests;

import com.practical.framework.entity.PaymentWrapper;
import com.practical.framework.rest.PaymentRest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static com.practical.framework.entity.PaymentMethod.CREDITCARD;
import static com.practical.framework.matchers.ResponseMatchers.hasStatus;
import static com.practical.framework.matchers.ResponseMatchers.withPaymentAmount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by sergey on 28.05.16.
 */
public class BuyCoinsCreditCardTest {

    private PaymentRest paymentRest = new PaymentRest();

    @Test
    public void shouldBeZeroPaymentAmountIfMethodCCAndZeroAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD, 0);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(0.0));
    }

    @Test
    public void shouldBeZeroPaymentAmountIfMethodCCAndNoAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(0.0));
    }


    @Test
    public void shouldBePaymentWithFeeIfMethodCCAndAmountLessThan25() {
        int amount = 164;
        double fee = 0.02;
        double expectedAmount = getCoinsPriceWithFee(amount,fee);

        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD, amount);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(expectedAmount));
    }

    @Test
    public void shouldBePaymentWithFeeIfMethodCCAndAmountMoreThan25AndLessThan50() {
        int amount = 165;
        double fee = 0.01;
        double expectedAmount = getCoinsPriceWithFee(amount,fee);

        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD, amount);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(expectedAmount));
    }

    @Test
    public void shouldBePaymentWithFeeIfMethodCCAndAmountMoreThan50() {
        int amount = 416;
        double fee = 0.00;
        double expectedAmount = getCoinsPriceWithFee(amount,fee);

        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD, amount);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(expectedAmount));
    }

    private double getCoinsPriceWithFee(int amount, double fee) {
        Double coinsPrice = paymentRest.coins(amount).getBody().getPayment().getAmount();
        return coinsPrice + coinsPrice * fee;
    }
}
