package io.github.mawngo.lark.message.template;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Builder for loop module
 *
 * @see VariablesSet for initialization.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class Loop implements Variable<List<Map<String, Object>>> {
    private final List<VariablesSet<?>> variables = new ArrayList<>();

    @Override
    public List<Map<String, Object>> build() {
        return variables.stream().map(VariablesSet::build).toList();
    }

    public Loop add(VariablesSet<?> variables) {
        this.variables.add(variables);
        return this;
    }

    public Loop variables(Consumer<Variables> customizer) {
        final var builder = new Variables();
        customizer.accept(builder);
        this.variables.add(builder);
        return this;
    }
}
