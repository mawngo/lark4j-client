package io.github.mawngo.lark.message.richtext;

import lombok.RequiredArgsConstructor;
import io.github.mawngo.lark.message.Message;
import io.github.mawngo.lark.message.RichTextMessage;

import java.util.Map;

@RequiredArgsConstructor
public final class FluentLineBuilder extends LineBuilder<FluentLineBuilder> implements Message {
    private final RichTextMessage content;

    @Override
    public Map<String, Object> toJsonData() {
        return end().toJsonData();
    }

    /**
     * End this line.
     */
    public RichTextMessage end() {
        content.line(line.toArray(RichTextContent[]::new));
        return content;
    }

    /**
     * End this line and start new line.
     */
    public FluentLineBuilder line() {
        return new FluentLineBuilder(end());
    }
}
