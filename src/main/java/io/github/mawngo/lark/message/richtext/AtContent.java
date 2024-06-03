package io.github.mawngo.lark.message.richtext;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
final class AtContent implements RichTextContent {
    private final String userId;
    private final String userName;

    @Override
    public RichTextTag getTag() {
        return RichTextTag.MENTION;
    }

    @Override
    public Map<String, Object> toJsonData() {
        final var map = new HashMap<String, Object>(8);
        map.put("tag", getTag().getTagName());
        map.put("user_id", userId);
        if (userName != null && !userName.isEmpty()) {
            map.put("user_name", userName);
        }
        return map;
    }
}
