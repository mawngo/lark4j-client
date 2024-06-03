package io.github.mawngo.lark.examples;

import io.github.mawngo.lark.message.richtext.RichTextContent;
import io.github.mawngo.lark.client.BotException;
import io.github.mawngo.lark.client.FixedNotificationBot;
import io.github.mawngo.lark.client.NotificationException;
import io.github.mawngo.lark.message.Message;

import static io.github.mawngo.lark.message.richtext.RichTextContent.link;
import static io.github.mawngo.lark.message.richtext.RichTextContent.text;

public class RichTextMessageExample {
    public static void sendRichText(FixedNotificationBot bot) {
        try {
            bot.send(
                    Message.richText()
                            .title("ok")
                            .textLine("test")
                            .line(RichTextContent.text("this must be "), RichTextContent.link("https://google.com", "on the same line"))
                            .textLine("this", "must", "separated.", " this must not")
                            .mentionAllLine()
            );
        } catch (NotificationException e) {
            throw new IllegalArgumentException("invalid message" + e.getCode());
        } catch (BotException e) {
            throw new RuntimeException("connection and other error", e);
        }
    }

    public static void sendRichTextLineBuilder(FixedNotificationBot bot) {
        try {
            bot.send(
                    Message.richText()
                            .title("ok")
                            .textLine("test")

                            .line()
                            .text("this must be ")
                            .link("https://google.com", "on the same line")
                            .end()

                            .textLine("this", "must", "separated.", " this must not")
                            .mentionAllLine()
            );
        } catch (NotificationException e) {
            throw new IllegalArgumentException("invalid message" + e.getCode());
        } catch (BotException e) {
            throw new RuntimeException("connection and other error", e);
        }
    }

    public static void sendRichTextLineCustomizer(FixedNotificationBot bot) {
        try {
            bot.send(
                    Message.richText()
                            .title("ok")
                            .textLine("test")
                            .line(l -> l
                                    .text("this must be ")
                                    .link("https://google.com", "on the same line"))
                            .textLine("this", "must", "separated.", " this must not")
                            .mentionAllLine()
            );
        } catch (NotificationException e) {
            throw new IllegalArgumentException("invalid message" + e.getCode());
        } catch (BotException e) {
            throw new RuntimeException("connection and other error", e);
        }
    }
}
