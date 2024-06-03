package io.github.mawngo.lark.message.richtext;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
final class TextContent implements RichTextContent {
    private final String text;
    private final boolean unEscape;

    @Override
    public RichTextTag getTag() {
        return RichTextTag.TEXT;
    }

    @Override
    public Map<String, Object> toJsonData() {
        return Map.of(
                "tag", getTag().getTagName(),
                "text", text,
                "un_escape", unEscape
        );
    }
}
