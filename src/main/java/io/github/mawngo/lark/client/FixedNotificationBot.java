package io.github.mawngo.lark.client;

import io.github.mawngo.lark.message.Message;

import java.net.http.HttpClient;

/**
 * Notification Bot that send to a fixed webhook url.
 */
public final class FixedNotificationBot extends NotificationBot {
    /**
     * The webhook url.
     */
    private final String webhookUrl;
    /**
     * (Optional) the secret key of the bot.
     */
    private final String secret;

    FixedNotificationBot(String webhookUrk, String secret, String userAgent, HttpClient httpClient) {
        super(userAgent, httpClient);
        this.webhookUrl = webhookUrk;
        this.secret = secret;
    }

    public void send(Message message) throws NotificationException, SignatureException, ConnectException {
        sendTo(message, webhookUrl, secret);
    }

    public void sendUnchecked(Message message) {
        sendToUnchecked(message, webhookUrl, secret);
    }
}
