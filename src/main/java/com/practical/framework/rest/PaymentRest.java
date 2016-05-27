package com.practical.framework.rest;

import com.practical.framework.entity.PaymentMethod;
import com.practical.framework.entity.PaymentWrapper;
import org.springframework.http.ResponseEntity;

import static com.practical.framework.Configuration.BASE_URL;

/**
 * Created by sergey on 5/27/16.
 */
public class PaymentRest {

    private RestClient restClient;

    public ResponseEntity<PaymentWrapper> buyCoins(PaymentMethod method) {
        return buyCoins(method, null);
    }

    public ResponseEntity<PaymentWrapper> buyCoins(int amount) {
        return buyCoins(null, amount);
    }

    public ResponseEntity<PaymentWrapper> buyCoins(PaymentMethod method, Integer amount) {
        return restClient.get(BASE_URL + "/buyCoins/?method={method}&amount={amount}", PaymentWrapper.class, method, amount);
    }
}
