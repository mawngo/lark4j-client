package io.github.mawngo.lark.message;

import java.util.Map;

public final class ShareChatMessage extends BaseMessage {
    private final String shareChatId;

    ShareChatMessage(String shareChatId) {
        this.shareChatId = shareChatId;
    }

    @Override
    public MessageType getType() {
        return MessageType.SHARE_CHAT;
    }

    @Override
    protected Map<String, Object> getContent() {
        return Map.of("share_chat_id", shareChatId);
    }
}
