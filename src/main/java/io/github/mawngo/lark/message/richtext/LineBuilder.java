package io.github.mawngo.lark.message.richtext;

import lombok.RequiredArgsConstructor;
import io.github.mawngo.lark.message.MessageType;
import io.github.mawngo.lark.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class LineBuilder<T extends LineBuilder<T>> {
    protected final List<RichTextContent> line = new ArrayList<>();

    /**
     * Add a link to this line.
     */
    public T link(String href, String text) {
        line.add(RichTextContent.link(href, text));
        return (T) this;
    }

    /**
     * Add a link to this line.
     */
    public T link(String href) {
        line.add(RichTextContent.link(href));
        return (T) this;
    }

    public MessageType getType() {
        return MessageType.RICH_TEXT;
    }

    public RichTextContent[] toLineArray() {
        return line.toArray(RichTextContent[]::new);
    }

    /**
     * Add an image to this line.
     */
    public T image(String imageKey) {
        line.add(RichTextContent.image(imageKey));
        return (T) this;
    }

    /**
     * Add a @people to this line.
     */
    public T mention(String userId, String userName) {
        line.add(RichTextContent.mention(userId, userName));
        return (T) this;
    }

    public T mention(String userId) {
        line.add(RichTextContent.mention(userId));
        return (T) this;
    }

    /**
     * Add an @all to this line.
     */
    public T mentionAll() {
        line.add(RichTextContent.mentionAll());
        return (T) this;
    }

    /**
     * Add content into this line.
     */
    public T content(RichTextContent... contents) {
        line.addAll(Arrays.asList(contents));
        return (T) this;
    }

    /**
     * Add text to this line. Multiple texts are automatically joined with ", "
     */
    public T text(String... texts) {
        for (int i = 0; i < texts.length; i++) {
            var text = texts[i];
            if (StringUtils.isEmpty(text)) {
                continue;
            }
            // Auto append comma.
            if (i < texts.length - 1 && !StringUtils.isEndWithSeparator(text)) {
                text += ", ";
            }
            line.add(RichTextContent.text(text));
        }
        return (T) this;
    }
}
