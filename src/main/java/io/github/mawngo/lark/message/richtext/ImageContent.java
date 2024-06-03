package io.github.mawngo.lark.message.richtext;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
final class ImageContent implements RichTextContent {
    private final String imageKey;

    @Override
    public RichTextTag getTag() {
        return RichTextTag.IMAGE;
    }

    @Override
    public Map<String, Object> toJsonData() {
        return Map.of(
                "tag", getTag().getTagName(),
                "image_key", imageKey
        );
    }
}
