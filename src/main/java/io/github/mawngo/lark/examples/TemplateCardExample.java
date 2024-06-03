package io.github.mawngo.lark.examples;

import io.github.mawngo.lark.message.template.Variable;
import io.github.mawngo.lark.client.BotException;
import io.github.mawngo.lark.client.FixedNotificationBot;
import io.github.mawngo.lark.message.Message;

public class TemplateCardExample {
    public static void complexTemplate(FixedNotificationBot bot) {
        final var message = Message.template("<templateid>")
                .textVar("title", "test")
                .linkVar("link", "https://google.com", l -> l.linkPC("https://google.com").linkAndroid("https://google.com"))
                .imageVar("image", "img_v2_e5d9761f-3b78-47f2-9fa6-f8438c46861h")
                .optionsVar("options", Variable.option("test", "test"), Variable.option("test2"))
                .peoplesVar("people", "chika")
                .buttonsVar("buttons", Variable.button("button1").link("https://google.com"), Variable.button("button2").value("b2"))
                .loopVar("loop", l -> l
                        .variables(v -> v.textVar("title", "test"))
                        .variables(v -> v.textVar("title", "title")
                                .loopVar("nested_loop_module", Variable.vars("title", "test")))
                );
        try {
            bot.send(message);
        } catch (BotException e) {
            throw new RuntimeException(e);
        }
    }
}
