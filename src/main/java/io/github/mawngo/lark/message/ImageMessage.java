package io.github.mawngo.lark.message;

import java.util.Map;

public final class ImageMessage extends BaseMessage {
    private final String imageKey;

    ImageMessage(String imageKey) {
        this.imageKey = imageKey;
    }

    @Override
    public MessageType getType() {
        return MessageType.IMAGE;
    }

    @Override
    protected Map<String, Object> getContent() {
        return Map.of("image_key", imageKey);
    }
}
