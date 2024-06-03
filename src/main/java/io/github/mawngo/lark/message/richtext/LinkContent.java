package io.github.mawngo.lark.message.richtext;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
final class LinkContent implements RichTextContent {
    private final String href;
    private final String text;

    @Override
    public RichTextTag getTag() {
        return RichTextTag.LINK;
    }

    @Override
    public Map<String, Object> toJsonData() {
        return Map.of(
                "tag", getTag().getTagName(),
                "text", text,
                "href", href
        );
    }
}
