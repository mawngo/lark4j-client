package io.github.mawngo.lark.message;

import java.util.Map;

public final class TextMessage extends BaseMessage {
    private final String text;

    TextMessage(String text) {
        this.text = text;
    }

    @Override
    public MessageType getType() {
        return MessageType.TEXT;
    }

    @Override
    protected Map<String, Object> getContent() {
        return Map.of("text", text);
    }
}
