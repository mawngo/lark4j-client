package io.github.mawngo.lark.message;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
final class RawMessage implements Message {
    private final MessageType type;
    private final Map<String, Object> content;
    private final String typeName;

    @Override
    public MessageType getType() {
        return Objects.requireNonNullElse(type, MessageType.UNKNOWN);
    }

    @Override
    public Map<String, Object> toJsonData() {
        return Map.of(
                "msg_type", Objects.isNull(typeName) ? getType().getTypeName() : typeName,
                "content", content
        );
    }
}
