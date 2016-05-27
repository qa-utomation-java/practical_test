package com.practical.tests;

import com.practical.framework.entity.PaymentWrapper;
import com.practical.framework.rest.PaymentRest;
import org.junit.BeforeClass;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import static com.practical.framework.entity.PaymentMethod.CREDITCARD;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by sergey on 5/27/16.
 */
public class BuyCoinsTest {


    @BeforeClass
    public void setUp() throws Exception {

    }

    @Test
    public void shouldBeZeroPaymentIfNoAmount() {
        PaymentRest paymentRest = new PaymentRest();
        ResponseEntity<PaymentWrapper> response = paymentRest.buyCoins(CREDITCARD);
        assertThat(response.getStatusCode(), equalTo(OK));
    }
}
