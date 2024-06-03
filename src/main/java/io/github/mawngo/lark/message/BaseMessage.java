package io.github.mawngo.lark.message;

import java.util.Map;

public abstract class BaseMessage implements Message {

    /**
     * Returns the content path of message as json (map).
     */
    protected abstract Map<String, Object> getContent();

    protected String getContentKey() {
        return "content";
    }

    @Override
    public Map<String, Object> toJsonData() {
        return Map.of(
                "msg_type", getType().getTypeName(),
                getContentKey(), getContent()
        );
    }
}
