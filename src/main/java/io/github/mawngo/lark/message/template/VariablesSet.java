package io.github.mawngo.lark.message.template;

import io.github.mawngo.lark.message.TemplateMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import io.github.mawngo.lark.utils.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Variable sets. Used in {@link TemplateMessage root} and {@link Loop loop}
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class VariablesSet<T extends VariablesSet<T>> implements Variable<Map<String, Object>> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final Map<String, Object> variables = new HashMap<>();

    /**
     * Bind options variable
     */
    public T optionsVar(String name, Option first, Option... options) {
        return optionsVar(name, g -> {
            g.add(first);
            for (final Option option : options) {
                g.add(option);
            }
        });
    }

    public T optionsVar(String name, String first, String... options) {
        return optionsVar(name, g -> {
            g.option(first);
            for (final String option : options) {
                g.option(option);
            }
        });
    }

    public T optionsVar(String name, Consumer<OptionsGroupBuilder> customizer) {
        final var builder = new OptionsGroupBuilder();
        customizer.accept(builder);
        return variable(name, builder.build());
    }

    /**
     * k Bind peoples variable
     */
    public T peoplesVar(String name, String first, String... peoples) {
        return peoplesVar(name, p -> {
            p.add(first);
            for (final String people : peoples) {
                p.add(people);
            }
        });
    }

    public T peoplesVar(String name, Consumer<PeoplesGroupBuilder> customizer) {
        final var builder = new PeoplesGroupBuilder();
        customizer.accept(builder);
        return variable(name, builder.build());
    }

    public T buttonsVar(String name, Consumer<ButtonsGroupBuilder> customizer) {
        final var builder = new ButtonsGroupBuilder();
        customizer.accept(builder);
        return variable(name, builder.build());
    }

    /**
     * Bind button group variable
     */
    public T buttonsVar(String name, Button first, Button... buttons) {
        return buttonsVar(name, p -> {
            p.add(first);
            for (final Button button : buttons) {
                p.add(button);
            }
        });
    }

    /**
     * Bind loop module variable
     */
    public T loopVar(String name, Consumer<Loop> customizer) {
        final var builder = new Loop();
        customizer.accept(builder);
        return variable(name, builder.build());
    }

    public T loopVar(String name, Loop builder) {
        return variable(name, builder.build());
    }

    /**
     * Bind loop module variable
     */
    public T loopVar(String name, Variables first, Variables... variables) {
        return loopVar(name, p -> {
            p.add(first);
            for (final Variables variableSet : variables) {
                p.add(variableSet);
            }
        });
    }

    /**
     * Bind image variable
     */
    public T imageVar(String name, String imageKey) {
        return variable(name, imageKey);
    }

    /**
     * Bind link variable
     */
    public T linkVar(String name, String link) {
        return variable(name, link);
    }

    /**
     * Bind multiple-link variable
     */
    public T linkVar(String name, String link, Consumer<MultipleLink> customizer) {
        final var builder = new MultipleLink(link);
        customizer.accept(builder);
        return variable(name, builder.build());
    }

    public T linkVar(String name, MultipleLink link) {
        return variable(name, link.build());
    }

    /**
     * Bind text variable. Multiple texts are automatically joined with ", "
     */
    public T textVar(String name, String first, String... texts) {
        if (texts.length == 0) {
            return variable(name, first);
        }
        final var text = new StringBuilder(first);
        if (!StringUtils.isEndWithSeparator(first)) {
            text.append(", ");
        }
        for (int i = 0; i < texts.length; i++) {
            final var t = texts[i];
            if (StringUtils.isEmpty(t)) {
                continue;
            }
            text.append(t);
            // Auto append comma.
            if (i < texts.length - 1 && !StringUtils.isEndWithSeparator(t)) {
                text.append(", ");
            }
        }
        return variable(name, text.toString());
    }

    public T dateVar(String name, LocalDate date) {
        return variable(name, DateTimeFormatter.ISO_LOCAL_DATE.format(date));
    }

    public T dateVar(String name, LocalDateTime dateTime) {
        return variable(name, DATE_TIME_FORMATTER.format(dateTime));
    }

    public T dateVar(String name, LocalTime time) {
        return variable(name, TIME_FORMATTER.format(time));
    }


    /**
     * Bind integer variable
     */
    public T integerVar(String name, int integer) {
        return variable(name, integer);
    }

    /**
     * Bind custom variable
     */
    @SuppressWarnings("unchecked")
    public T variable(String name, Map<String, Object> content) {
        this.variables.put(name, content);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T variable(String name, List<Map<String, Object>> content) {
        this.variables.put(name, content);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T variable(String name, int content) {
        this.variables.put(name, content);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T variable(String name, String content) {
        this.variables.put(name, content);
        return (T) this;
    }

    @Override
    public Map<String, Object> build() {
        return variables;
    }
}
