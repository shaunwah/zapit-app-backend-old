package com.shaunwah.zapitappbackend.utilities;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

public final class Utilities {
    public static String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).trim();
        if (token.startsWith("Bearer ")) {
            return token.substring("Bearer ".length());
        }
        return null;
    }
    public static JsonObject generateMessage(String message) {
        return Json.createObjectBuilder()
                .add("message", message)
                .build();
    }

    public static JsonObject generateTokenMessage(String token, String message) {
        return Json.createObjectBuilder()
                .add("token", token)
                .add("message", message)
                .build();
    }

    public static Sort.Direction generateSortByDirection(int direction) {
        if (direction >= 1) {
            return Sort.Direction.ASC;
        }
        return Sort.Direction.DESC;
    }
}
