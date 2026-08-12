package com.example.demo.service;

import com.example.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

@Service
public class TokenService {

    private static final String HMAC_ALGORITHM = "HmacSHA256";

    private final String secret;
    private final long expirationMillis;

    public TokenService(
            @Value("${app.jwt.secret:demo-secret-key}") String secret,
            @Value("${app.jwt.expiration-ms:3600000}") long expirationMillis) {
        this.secret = secret;
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(Usuario usuario) {
        long expiration = Instant.now().plusMillis(expirationMillis).toEpochMilli();
        String payload = usuario.getEmail() + ":" + expiration;
        String encodedPayload = encodeBase64Url(payload);
        String signature = sign(encodedPayload);
        return encodedPayload + "." + signature;
    }

    public String getSubject(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 2) {
            throw new RuntimeException("Token inválido");
        }

        String encodedPayload = parts[0];
        String signature = parts[1];
        String expectedSignature = sign(encodedPayload);

        if (!constantTimeEquals(signature, expectedSignature)) {
            throw new RuntimeException("Token inválido");
        }

        String decodedPayload = decodeBase64Url(encodedPayload);
        String[] payloadParts = decodedPayload.split(":");
        if (payloadParts.length != 2) {
            throw new RuntimeException("Token inválido");
        }

        long expiration = Long.parseLong(payloadParts[1]);
        if (Instant.now().toEpochMilli() > expiration) {
            throw new RuntimeException("Token expirado");
        }

        return payloadParts[0];
    }

    private String sign(String data) {
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM));
            byte[] digest = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Erro ao criar assinatura do token", e);
        }
    }

    private String encodeBase64Url(String value) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    private String decodeBase64Url(String value) {
        return new String(Base64.getUrlDecoder().decode(value), StandardCharsets.UTF_8);
    }

    private boolean constantTimeEquals(String left, String right) {
        return java.security.MessageDigest.isEqual(left.getBytes(StandardCharsets.UTF_8), right.getBytes(StandardCharsets.UTF_8));
    }
}
