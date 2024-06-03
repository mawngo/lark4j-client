package io.github.mawngo.lark.message.template;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Builder for link var.
 *
 * @see VariablesSet for initialization.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class MultipleLink implements Variable<Map<String, Object>> {
    private String linkAndroid;
    private String linkIOS;
    private String linkPC;
    private final String url;

    @Override
    public Map<String, Object> build() {
        final var map = new HashMap<String, Object>(6);
        map.put("android", Objects.toString(linkAndroid, url));
        map.put("ios", Objects.toString(linkIOS, url));
        map.put("pc", Objects.toString(linkPC, url));
        map.put("url", url);
        return map;
    }

    /**
     * Set link for ios platform, for multiple terminal jump link.
     */
    public MultipleLink linkIOS(String href) {
        this.linkIOS = href;
        return this;
    }

    /**
     * Set link for pc platform, for multiple terminal jump link.
     */
    public MultipleLink linkPC(String href) {
        this.linkPC = href;
        return this;
    }

    /**
     * Set link for android platform, for multiple terminal jump link.
     */
    public MultipleLink linkAndroid(String href) {
        this.linkAndroid = href;
        return this;
    }
}
