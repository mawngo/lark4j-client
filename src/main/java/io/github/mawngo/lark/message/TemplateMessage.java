package io.github.mawngo.lark.message;

import io.github.mawngo.lark.message.template.VariablesSet;

import java.util.Map;

public final class TemplateMessage extends VariablesSet<TemplateMessage> implements Message {
    private final String templateId;

    TemplateMessage(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public MessageType getType() {
        return MessageType.CARD;
    }

    @Override
    public Map<String, Object> toJsonData() {
        return Map.of(
                "msg_type", getType().getTypeName(),
                "card", getContent()
        );
    }

    private Map<String, Object> getContent() {
        return Map.of(
                "type", "template",
                "data", Map.of(
                        "template_id", templateId,
                        "template_variable", build()
                )
        );
    }
}
