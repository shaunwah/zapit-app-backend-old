package com.shaunwah.zapitappbackend.misc.external;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StripeService {
    @Value("${stripe.api.key.publishable}")
    private String apiKeyPublishable;

    @Value("${stripe.api.key.secret}")
    private String apiKeySecret;

    public void sendPayment() throws StripeException {
        Stripe.apiKey = apiKeySecret;

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("{SUCCESS_URL}")
                .setCancelUrl("{CANCEL_URL}")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPrice("{PRICE}")
                                .build()
                )
                .build();
        Session session = Session.create(params);
        System.out.println(session);
    }
}
