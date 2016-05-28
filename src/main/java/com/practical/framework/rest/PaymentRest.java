package com.practical.framework.rest;

import com.practical.framework.Configuration;
import com.practical.framework.entity.PaymentMethod;
import com.practical.framework.entity.PaymentWrapper;
import org.springframework.http.ResponseEntity;

/**
 * Created by sergey on 5/27/16.
 */
public class PaymentRest {

    private RestClient restClient;

    public PaymentRest() {
        this.restClient = new RestClient(Configuration.BASE_URL);
    }

    public ResponseEntity<PaymentWrapper> buyCoins(PaymentMethod method) {
        return restClient.get("/buyCoins/?method={method}", PaymentWrapper.class, method);
    }

    public ResponseEntity<PaymentWrapper> buyCoins(int amount) {
        return restClient.get("/buyCoins/?amount={amount}", PaymentWrapper.class, amount);
    }

    public ResponseEntity<PaymentWrapper> buyCoins(PaymentMethod method, int amount) {
        return restClient.get("/buyCoins/?method={method}&amount={amount}", PaymentWrapper.class, method, amount);
    }
}
