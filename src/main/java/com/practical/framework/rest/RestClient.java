package com.practical.framework.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 5/27/16.
 */
public class RestClient extends RestTemplate {

    private final String baseUrl;

    public RestClient(String baseUrl) {
        this.baseUrl = baseUrl;
        init();
    }

    public <T> ResponseEntity<T> get(String url, Class<T> responseType, Object... params) {
        return getForEntity(baseUrl + url,responseType,params);
    }

    private List<HttpMessageConverter<?>> init() {
        List<HttpMessageConverter<?>> messageConverterList = getMessageConverters();
        MappingJacksonHttpMessageConverter jsonMessageConverter = new MappingJacksonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(new MediaType("text", "plain"));
        jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        PaymentHttpMsgConverter paymentHttpmsgConverter = new PaymentHttpMsgConverter();
        messageConverterList.add(paymentHttpmsgConverter);
        messageConverterList.add(jsonMessageConverter);
        return messageConverterList;

    }
}
