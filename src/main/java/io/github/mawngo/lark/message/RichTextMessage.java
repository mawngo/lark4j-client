package io.github.mawngo.lark.message;

import io.github.mawngo.lark.message.richtext.FluentLineBuilder;
import io.github.mawngo.lark.message.richtext.LineBuilder;
import io.github.mawngo.lark.message.richtext.RichTextContent;
import io.github.mawngo.lark.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Consumer;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public final class RichTextMessage extends BaseMessage {
    private final String lang;

    private String title;

    private final List<List<Map<String, Object>>> lines = new ArrayList<>();

    /**
     * Set title of this message.
     */
    public RichTextMessage title(String title) {
        if (title == null || title.isEmpty()) {
            return this;
        }
        this.title = title;
        return this;
    }

    /**
     * Use {@link LineBuilder line builder functional style} to build next line.
     */
    public RichTextMessage line(Consumer<LineBuilder<?>> customizer) {
        final var lineCustomizer = new LineBuilder<>();
        customizer.accept(lineCustomizer);
        line(lineCustomizer.toLineArray());
        return this;
    }

    /**
     * Use {@link FluentLineBuilder line builder} to build next line.
     */
    public FluentLineBuilder line() {
        return new FluentLineBuilder(this);
    }

    /**
     * Add a line using {@link RichTextContent} which can include multiple type of content on same line
     */
    public RichTextMessage line(RichTextContent... contents) {
        final var line = new ArrayList<Map<String, Object>>(contents.length);
        for (RichTextContent content : contents) {
            line.add(content.toJsonData());
        }
        lines.add(line);
        return this;
    }

    /**
     * Add a link as a separate line
     */
    public RichTextMessage linkLine(String href, String text) {
        lines.add(Collections.singletonList(RichTextContent.link(href, text).toJsonData()));
        return this;
    }

    /**
     * Add a link as a separate line
     */
    public RichTextMessage linkLine(String href) {
        lines.add(Collections.singletonList(RichTextContent.link(href).toJsonData()));
        return this;
    }

    /**
     * Add an image as a separate line
     */
    public RichTextMessage imageLine(String imageKey) {
        lines.add(Collections.singletonList(RichTextContent.image(imageKey).toJsonData()));
        return this;
    }

    /**
     * Add a @people as a separate line.
     */
    public RichTextMessage mentionLine(String userId, String userName) {
        lines.add(Collections.singletonList(RichTextContent.mention(userId, userName).toJsonData()));
        return this;
    }

    /**
     * Add a @people as a separate line.
     */
    public RichTextMessage mentionLine(String userId) {
        lines.add(Collections.singletonList(RichTextContent.mention(userId).toJsonData()));
        return this;
    }

    /**
     * Add a @all as a separate line.
     */
    public RichTextMessage mentionAllLine() {
        lines.add(Collections.singletonList(RichTextContent.mentionAll().toJsonData()));
        return this;
    }

    /**
     * Add a text as a separate line. Multiple texts are automatically joined with ", "
     */
    public RichTextMessage textLine(String... texts) {
        final var line = new ArrayList<Map<String, Object>>(texts.length);
        for (int i = 0; i < texts.length; i++) {
            var text = texts[i];
            if (StringUtils.isEmpty(text)) {
                continue;
            }
            // Auto append comma.
            if (i < texts.length - 1 && !StringUtils.isEndWithSeparator(text)) {
                text += ", ";
            }
            line.add(RichTextContent.text(text).toJsonData());
        }
        if (line.isEmpty()) {
            return this;
        }
        lines.add(line);
        return this;
    }

    @Override
    protected Map<String, Object> getContent() {
        final var map = new HashMap<>(8);
        map.put("content", lines);
        if (title != null) {
            map.put("title", title);
        }
        return Collections.singletonMap("post", Collections.singletonMap(lang, map));
    }

    @Override
    public MessageType getType() {
        return MessageType.RICH_TEXT;
    }
}
