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
    public void shouldBe1UahPaymentIfMethodIdealAndNoAmount() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(IDEAL);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(1.0));
    }

    @Test
    public void shouldBePaymentWithFeeIfMethodIdealAndAmountMore25Uah() {
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(IDEAL,156);
        assertThat(response, hasStatus(OK));
        assertThat(response, withPaymentAmount(25.1));
    }
}
