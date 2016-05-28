package com.practical.framework.rest;

import com.practical.framework.entity.PaymentMethod;
import com.practical.framework.entity.PaymentWrapper;
import org.springframework.http.ResponseEntity;

import static com.practical.framework.PropertiesLoader.getConfig;

/**
 * Created by sergey on 5/27/16.
 */
public class PaymentRest {

    private RestClient restClient;

    public static final String BUY_COINS = "/buyCoins/";
    public static final String COINS = "/coins/";

    public PaymentRest() {
        this.restClient = new RestClient(getConfig().getBaseUrl());
    }

    public ResponseEntity<PaymentWrapper> buyCoins(PaymentMethod method) {
        return restClient.get(BUY_COINS + "?method={method}", PaymentWrapper.class, method);
    }

    public ResponseEntity<PaymentWrapper> buyCoins(int amount) {
        return restClient.get(BUY_COINS + "?amount={amount}", PaymentWrapper.class, amount);
    }

    public ResponseEntity<PaymentWrapper> buyCoins(PaymentMethod method, int amount) {
        return restClient.get(BUY_COINS + "?method={method}&amount={amount}", PaymentWrapper.class, method, amount);
    }

    public ResponseEntity<PaymentWrapper> coins(Object amount) {
        return restClient.get(COINS+"?&amount={amount}", PaymentWrapper.class, amount);
    }
}
