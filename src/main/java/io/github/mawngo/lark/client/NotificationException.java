package io.github.mawngo.lark.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotificationException extends BotException {
    private final int code;
    private final String message;
}
