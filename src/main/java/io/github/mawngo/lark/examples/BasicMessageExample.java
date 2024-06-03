package io.github.mawngo.lark.examples;

import io.github.mawngo.lark.client.BotException;
import io.github.mawngo.lark.client.FixedNotificationBot;
import io.github.mawngo.lark.client.NotificationBot;
import io.github.mawngo.lark.client.NotificationException;
import io.github.mawngo.lark.message.Message;

import java.util.Map;

public class BasicMessageExample {
    public static FixedNotificationBot createBot() {
        return NotificationBot.newBot("my webhook url");
    }

    public static FixedNotificationBot createSecuredBot() {
        return NotificationBot.newSecuredBot("my webhook url", "my secret");
    }

    public static void sendTextMessageToUrl() {
        NotificationBot bot = NotificationBot.newBot();
        try {
            bot.sendTo(Message.text("hello world"), "webhookurl", "secret");
        } catch (NotificationException e) {
            throw new IllegalArgumentException("invalid message" + e.getCode());
        } catch (BotException e) {
            throw new RuntimeException("connection and other error", e);
        }
    }

    public static void sendTextMessage(FixedNotificationBot bot) {
        try {
            bot.send(Message.text("hello world"));
        } catch (NotificationException e) {
            throw new IllegalArgumentException("invalid message" + e.getCode());
        } catch (BotException e) {
            throw new RuntimeException("connection and other error", e);
        }
    }

    public static void sendShareChatMessage(FixedNotificationBot bot) {
        try {
            bot.send(Message.shareChat("<id>"));
        } catch (NotificationException e) {
            throw new IllegalArgumentException("invalid message" + e.getCode());
        } catch (BotException e) {
            throw new RuntimeException("connection and other error", e);
        }
    }


    public static void sendRawMessage(FixedNotificationBot bot) {
        try {
            bot.send(Message.raw("text", Map.of("text", "example")));
        } catch (NotificationException e) {
            throw new IllegalArgumentException("invalid message" + e.getCode());
        } catch (BotException e) {
            throw new RuntimeException("connection and other error", e);
        }
    }
}
