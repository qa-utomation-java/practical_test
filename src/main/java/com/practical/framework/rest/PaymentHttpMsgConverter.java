package com.practical.framework.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practical.framework.entity.PaymentWrapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import static org.apache.commons.io.IOUtils.toString;

/**
 * Created by sergey on 5/27/16.
 */
public class PaymentHttpMsgConverter extends AbstractHttpMessageConverter<PaymentWrapper> {

    private static final Logger LOGGER = Logger.getLogger("Payment Converter");

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private ObjectMapper objectMapper = new ObjectMapper();

    public PaymentHttpMsgConverter(){
        super(new MediaType("text", "html", DEFAULT_CHARSET));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected PaymentWrapper readInternal(Class<? extends PaymentWrapper> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream body = inputMessage.getBody();
        String json = org.apache.commons.io.IOUtils.toString(body);
        LOGGER.info(json);
        return objectMapper.readValue(json, PaymentWrapper.class);
    }

    @Override
    protected void writeInternal(PaymentWrapper paymentWrapper, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

}
