package io.github.mawngo.lark.message.template;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Builder for options group var.
 *
 * @see VariablesSet for initialization.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class OptionsGroupBuilder implements Variable<List<Map<String, Object>>> {

    private final List<Option> options = new ArrayList<>();

    @Override
    public List<Map<String, Object>> build() {
        return options.stream()
                .map(o -> Map.<String, Object>of("text", o.text(), "value", o.value()))
                .toList();
    }

    public OptionsGroupBuilder option(String value, String text) {
        this.options.add(new Option(value, text));
        return this;
    }

    public OptionsGroupBuilder option(String value) {
        return option(value, value);
    }

    public OptionsGroupBuilder add(Option option) {
        this.options.add(option);
        return this;
    }
}
