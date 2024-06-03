package io.github.mawngo.lark.message.template;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Builder for peoples group var.
 *
 * @see VariablesSet for initialization.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class PeoplesGroupBuilder implements Variable<List<Map<String, Object>>> {

    private final List<String> peoples = new ArrayList<>();

    @Override
    public List<Map<String, Object>> build() {
        return peoples.stream()
                .map(p -> Map.<String, Object>of("value", p))
                .toList();
    }

    public PeoplesGroupBuilder add(String value) {
        this.peoples.add(value);
        return this;
    }
}
