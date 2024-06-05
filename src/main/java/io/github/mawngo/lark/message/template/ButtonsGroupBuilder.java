package io.github.mawngo.lark.message.template;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Builder for buttons group var.
 *
 * @see VariablesSet for initialization.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class ButtonsGroupBuilder implements Variable<List<Map<String, Object>>> {
    private final List<Button> buttons = new ArrayList<>();

    @Override
    public List<Map<String, Object>> build() {
        return buttons.stream().map(Button::build)
                .collect(Collectors.toUnmodifiableList());
    }

    public ButtonsGroupBuilder button(String text) {
        this.buttons.add(new Button(text));
        return this;
    }

    public ButtonsGroupBuilder button(String text, Consumer<Button> customizer) {
        final var builder = new Button(text);
        customizer.accept(builder);
        this.buttons.add(builder);
        return this;
    }

    public ButtonsGroupBuilder add(Button builder) {
        this.buttons.add(builder);
        return this;
    }
}
