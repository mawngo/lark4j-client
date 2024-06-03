package io.github.mawngo.lark.message;

import java.util.Map;

/**
 * Represent a message that will be sent to Lark
 *
 * @see MessageType for available type.
 */
public interface Message {

    /**
     * Get the type of this message.
     */
    MessageType getType();

    /**
     * Returns the message as json (map).
     */
    Map<String, Object> toJsonData();

    /**
     * Create a message using provided json content.
     */
    static Message raw(MessageType messageType, Map<String, Object> content) {
        return new RawMessage(messageType, content, messageType.getTypeName());
    }

    static Message raw(String messageType, Map<String, Object> content) {
        return new RawMessage(MessageType.UNKNOWN, content, messageType);
    }

    /**
     * Create a templated card message.
     */
    static TemplateMessage template(String templateId) {
        return new TemplateMessage(templateId);
    }

    /**
     * Create a group business card (share chat) message.
     */
    static Message shareChat(String shareChatId) {
        return new ShareChatMessage(shareChatId);
    }

    /**
     * Create a simple image message.
     */
    static Message image(String imageKey) {
        return new ImageMessage(imageKey);
    }

    /**
     * Create a simple text message.
     */
    static Message text(String text) {
        return new TextMessage(text);
    }

    /**
     * Create a {@link RichTextMessage}
     */
    static RichTextMessage richText() {
        return richText("en_us");
    }

    /**
     * Create a {@link RichTextMessage} for the specified locale
     */
    static RichTextMessage richText(String locale) {
        return new RichTextMessage(locale);
    }

    /**
     * Combine multiple {@link RichTextMessage} of different lang into single one.
     */
    static Message multilingual(RichTextMessage... richTextMessages) {
        return new MultilingualRichTextMessage(richTextMessages);
    }
}
