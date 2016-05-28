package com.practical.tests;

import com.practical.framework.entity.PaymentWrapper;
import com.practical.framework.rest.PaymentRest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static com.practical.framework.entity.PaymentMethod.IDEAL;
import static com.practical.framework.matchers.ResponseMatchers.hasStatus;
import static com.practical.framework.matchers.ResponseMatchers.withPaymentAmount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by sergey on 28.05.16.
 */
public class BuyCoinsIdealTest {

    private PaymentRest paymentRest = new PaymentRest();

    @Test
    //Probably bug, because amount of coins to buy is 0.
    public void shouldBe1UahPaymentIfMethodIdealAndNoAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(IDEAL, 0);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(0.0));
    }

    @Test
    public void shouldBePaymentWithAdditionalCostIfMethodIdealAndAmountLessThen25Uah() {
        int amount = 164;
        int ADDITIONAL_COST = 1;
        double coinsPrice = getCoinsPrice(amount) + ADDITIONAL_COST;

        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(IDEAL, amount);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(coinsPrice));
    }

    @Test
    public void shouldBePaymentWithoutAdditionalCostIfMethodIdealAndAmountIs25Uah() {
        int amount = 165;
        double coinsPrice = getCoinsPrice(amount);

        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(IDEAL, amount);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(coinsPrice));
    }

    @Test
    public void shouldBePaymentWithoutAdditionalCostIfMethodIdealAndAmountMore25Uah() {
        int amount = 166;
        double coinsPrice = getCoinsPrice(amount);

        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(IDEAL, amount);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(coinsPrice));
    }

    private double getCoinsPrice(int amount) {
        return paymentRest.coins(amount).getBody().getPayment().getAmount();
    }
}
