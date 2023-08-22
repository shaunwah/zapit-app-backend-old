package com.shaunwah.zapitappbackend.misc.external;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class MapboxService {
    @Value("${mapbox.api.url}")
    private String apiUrl;

    @Value("${mapbox.api.key}")
    private String apiKey;
    public Double[] forwardGeocode(String query) {
        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .path("/mapbox.places")
                .path("/%s.json".formatted(URLEncoder.encode(query, StandardCharsets.UTF_8)))
                .queryParam("access_token", apiKey)
                .queryParam("country", "SG")
                .queryParam("language", "en")
                .queryParam("limit", 1)
                .toUriString();

        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .build();

        String result = webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return null; // TODO
    }
}
