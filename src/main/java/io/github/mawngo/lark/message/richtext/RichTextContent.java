package io.github.mawngo.lark.message.richtext;

import java.util.Map;

/**
 * Represent a single content tag of a rich-text message. One line of a rich-text message can contain multiple content.
 *
 * @see RichTextTag for available type.
 */
public interface RichTextContent {
    RichTextTag getTag();

    /**
     * Returns the content as json (map).
     */
    Map<String, Object> toJsonData();

    /**
     * Create a text content
     */
    static RichTextContent text(String text) {
        return new TextContent(text, false);
    }

    /**
     * Create a text content
     */
    static RichTextContent text(String text, boolean unEscape) {
        return new TextContent(text, unEscape);
    }

    /**
     * Create a link content without text
     */
    static RichTextContent link(String href) {
        return new LinkContent(href, href);
    }

    /**
     * Create a link content.
     */
    static RichTextContent link(String href, String text) {
        return new LinkContent(href, text);
    }

    /**
     * Create an image content.
     */
    static RichTextContent image(String imageKey) {
        return new ImageContent(imageKey);
    }

    /**
     * Create @people content.
     */
    static RichTextContent mention(String userId, String userName) {
        return new AtContent(userId, userName);
    }

    /**
     * Create @people content.
     */
    static RichTextContent mention(String userId) {
        return new AtContent(userId, null);
    }

    /**
     * Create @all content.
     *
     * @deprecated use {@link #mentionAll()}
     */
    @Deprecated(forRemoval = true)
    static RichTextContent mentionEveryone() {
        return new AtContent("all", "Everyone");
    }

    /**
     * Create @all content.
     */
    static RichTextContent mentionAll() {
        return new AtContent("all", "Everyone");
    }
}
