package com.shaunwah.zapitappbackend.misc.utilities;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.io.File;
import java.util.UUID;

public final class Utilities {
    public static String generateStringForSoftDelete(long id) {
//        MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
        UUID uuid = UUID.randomUUID();
        Long now = System.currentTimeMillis() / 1000L;
        return "Deleted-%d-%d-%s".formatted(id, now, uuid.toString());
    }
    public static String getFileExtension(File file) {
        String[] fileNames = file.getName().split("\\.");
        return fileNames[fileNames.length - 1];
    }
    public static <T> Long getIdFromObject(Object object, Class<T> clazz) throws NoSuchFieldException {
        return Long.parseLong(object.getClass().getField("id").toString());
    }

    public static Long getUserIdFromTokenRequest(HttpServletRequest request, JwtDecoder jwtDecoder) {
        Jwt jwt = jwtDecoder.decode(Utilities.getTokenFromRequest(request));
        return Long.parseLong(jwt.getSubject());
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        final String REGEX = "Bearer\\s+";
        final String TOKEN = request.getHeader(HttpHeaders.AUTHORIZATION).trim().replaceFirst(REGEX, "");
        if (!TOKEN.isEmpty()) {
            return TOKEN;
        }
        return null;
    }
    public static JsonObject generateMessage(String message) {
        return Json.createObjectBuilder()
                .add("message", message)
                .build();
    }

    public static JsonObject generateTokenMessage(String token, String username, String message) {
        return Json.createObjectBuilder()
                .add("token", token)
                .add("username", username)
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
