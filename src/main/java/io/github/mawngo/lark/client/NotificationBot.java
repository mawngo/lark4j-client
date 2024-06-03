package io.github.mawngo.lark.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.mawngo.lark.utils.Json;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import io.github.mawngo.lark.message.Message;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Simple support for webhook notification bot
 */
public class NotificationBot {
    /**
     * (Optional) default UA.
     */
    private final String userAgent;

    private final HttpClient httpClient;

    protected NotificationBot(String userAgent, HttpClient httpClient) {
        this.userAgent = userAgent;
        this.httpClient = Objects.requireNonNullElse(httpClient, HttpClient.newHttpClient());
    }

    /**
     * Returns a {@link FixedNotificationBot} with default settings.
     */
    public static NotificationBot newBot() {
        return new NotificationBot(null, null);
    }

    /**
     * Returns a {@link FixedNotificationBot} with default settings.
     */
    public static FixedNotificationBot newBot(String webhookUrl) {
        return new FixedNotificationBot(webhookUrl, null, null, null);
    }

    /**
     * Returns a {@link FixedNotificationBot} with default settings and secured using a secret key.
     */
    public static FixedNotificationBot newSecuredBot(String webhookUrl, String secret) {
        return new FixedNotificationBot(webhookUrl, secret, null, null);
    }

    /**
     * Returns a {@link NotificationBot.Builder Builder} for customization.
     */
    public static NotificationBot.Builder newBuilder() {
        return new NotificationBot.Builder();
    }


    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @Accessors(fluent = true, chain = true)
    @Setter
    public static class Builder {
        private String userAgent;
        private HttpClient httpClient;

        /**
         * Build a {@link NotificationBot}
         */
        public NotificationBot build() {
            return new NotificationBot(userAgent, httpClient);
        }

        /**
         * Build a {@link FixedNotificationBot}
         */
        public FixedNotificationBot build(String webhookUrl, String secret) {
            return new FixedNotificationBot(webhookUrl, secret, userAgent, httpClient);
        }

        /**
         * Build a {@link FixedNotificationBot}
         */
        public FixedNotificationBot build(String webhookUrl) {
            return new FixedNotificationBot(webhookUrl, null, userAgent, httpClient);
        }
    }

    private static String genSign(String secret, int timestamp) throws SignatureException {
        try {
            // Take timestamp + "\n" + key as signature string
            final String stringToSign = timestamp + "\n" + secret;
            // Use the HmacSHA256 algorithm to calculate the signature
            final Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(stringToSign.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            final byte[] signData = mac.doFinal(new byte[]{});
            return new String(Base64.getEncoder().encode(signData), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new SignatureException(e);
        }
    }


    /**
     * Send the specified message to webhook
     *
     * @param message The {@link Message} to be sent
     */
    public void sendTo(Message message, String webhookUrl, String secret) throws ConnectException, NotificationException, SignatureException {
        sendRaw(message.toJsonData(), webhookUrl, secret);
    }

    public void sendTo(Message message, String webhookUrl) throws ConnectException, NotificationException, SignatureException {
        sendRaw(message.toJsonData(), webhookUrl, null);
    }

    /**
     * Variant of {@link #sendTo(Message, String webhookUrl, String secret)} that throw runtime exception when fail instead.
     */
    public void sendToUnchecked(Message message, String webhookUrl, String secret) {
        try {
            sendRaw(message.toJsonData(), webhookUrl, secret);
        } catch (Exception e) {
            throw new UncheckedBotException(e);
        }
    }

    public void sendToUnchecked(Message message, String webhookUrl) {
        sendToUnchecked(message, webhookUrl, null);
    }

    /**
     * Send the specified data to webhook.
     */
    protected void sendRaw(Map<String, Object> jsonMessage, String webhookUrl, String secret) throws ConnectException, NotificationException, SignatureException {
        final var request = HttpRequest.newBuilder()
                .uri(URI.create(webhookUrl))
                .header("Content-Type", "application/json")
                .POST(prepareRequestBody(jsonMessage, secret));
        if (userAgent != null && !userAgent.isEmpty()) {
            request.header("User-Agent", userAgent);
        }

        try {
            final var httpResponse = httpClient.send(request.build(), HttpResponse.BodyHandlers.ofString());
            final var response = Json.unmarshalFromString(httpResponse.body(), Response.class);
            if (response.isSucceed()) {
                return;
            }
            if (response.code == 19021) {
                throw new SignatureException();
            }
            throw new NotificationException(response.code, response.msg);
        } catch (IOException | InterruptedException e) {
            throw new ConnectException("Cannot connect to webhook", e);
        }
    }

    private HttpRequest.BodyPublisher prepareRequestBody(Map<String, Object> message, String secret) throws SignatureException {
        if (secret != null && !secret.isEmpty()) {
            final int timestamp = (int) (System.currentTimeMillis() / 1000);
            final var signedJson = new HashMap<String, Object>();
            signedJson.put("timestamp", String.valueOf(timestamp));
            signedJson.put("sign", genSign(secret, timestamp));
            signedJson.putAll(message);
            message = signedJson;
        }
        return HttpRequest.BodyPublishers.ofString(Json.marshalToString(message), StandardCharsets.UTF_8);
    }


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response {
        private int code;
        private String msg;

        public boolean isSucceed() {
            return code == 0;
        }
    }
}
