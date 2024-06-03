package io.github.mawngo.lark.message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageType {
    TEXT("text"),
    RICH_TEXT("post"),
    IMAGE("image"),
    SHARE_CHAT("share_chat"),
    // TODO: the interactive (card) type is currently does not supported. You can still send "interactive" message using Message.raw(), or using templated card with Message.template()
    CARD("interactive"),
    UNKNOWN("unknown");

    private final String value;

    public String getTypeName() {
        return value;
    }
}
