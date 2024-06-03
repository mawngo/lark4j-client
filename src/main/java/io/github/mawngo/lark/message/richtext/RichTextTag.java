package io.github.mawngo.lark.message.richtext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RichTextTag {
    LINK("a"),
    TEXT("text"),
    IMAGE("image"),
    MENTION("at");

    private final String value;

    public String getTagName() {
        return value;
    }
}
