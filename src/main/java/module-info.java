module io.github.mawngo.lark {
    opens io.github.mawngo.lark.client;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires static lombok;
    requires java.desktop;

    exports io.github.mawngo.lark.client;
    exports io.github.mawngo.lark.message;
    exports io.github.mawngo.lark.message.richtext;
    exports io.github.mawngo.lark.message.template;
}
