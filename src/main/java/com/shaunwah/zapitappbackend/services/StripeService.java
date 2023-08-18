package com.shaunwah.zapitappbackend.services;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log
public class StripeService {
    @Value("${stripe.api.url}")
    private String apiUrl;

    @Value("${stripe.api.key}")
    private String apiKey;
}
