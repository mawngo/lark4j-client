package io.github.mawngo.lark.message;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
final class MultilingualRichTextMessage extends BaseMessage {
    private final RichTextMessage[] messages;

    @SuppressWarnings("unchecked")
    @Override
    protected Map<String, Object> getContent() {
        final var map = new HashMap<String, Object>();
        for (final RichTextMessage message : messages) {
            map.putAll((Map<? extends String, ?>) message.getContent().get("post"));
        }
        return Map.of("post", map);
    }

    @Override
    public MessageType getType() {
        return MessageType.RICH_TEXT;
    }
}
