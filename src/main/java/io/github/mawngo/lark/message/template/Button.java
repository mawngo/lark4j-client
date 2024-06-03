package io.github.mawngo.lark.message.template;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import io.github.mawngo.lark.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Builder for
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public final class Button implements Variable<Map<String, Object>> {
    private final String text;
    private Map<String, Object> link;
    private String value;


    public Button value(String value) {
        this.value = value;
        return this;
    }

    public Button link(String href) {
        this.link = new MultipleLink(href).build();
        return this;
    }

    public Button link(String href, Consumer<MultipleLink> consumer) {
        final var builder = new MultipleLink(href);
        consumer.accept(builder);
        this.link = builder.build();
        return this;
    }

    @Override
    public Map<String, Object> build() {
        final var map = new HashMap<String, Object>(6);
        map.put("text", text);
        if (!StringUtils.isEmpty(value)) {
            map.put("value", value);
        }
        if (link != null) {
            map.put("multi_url", link);
        }
        return map;
    }
}
