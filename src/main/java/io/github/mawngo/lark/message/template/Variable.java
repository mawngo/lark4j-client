package io.github.mawngo.lark.message.template;

/**
 * Base interface for template-variable builder
 */
public interface Variable<T> {
    /**
     * Build and return the var.
     */
    T build();

    static Button button(String text) {
        return new Button(text);
    }

    /**
     * Create an option param
     */
    static Option option(String value, String text) {
        return new Option(value, text);
    }

    static Option option(String value) {
        return new Option(value, value);
    }

    static MultipleLink multipleLink(String href) {
        return new MultipleLink(href);
    }

    static Loop loop() {
        return new Loop();
    }

    static Variables vars() {
        return new Variables();
    }

    static Variables vars(String name, String value) {
        return new Variables().variable(name, value);
    }
}
