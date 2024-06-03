package io.github.mawngo.lark.message.template;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Accessors(fluent = true)
public final class Option {
    private final String value;
    private final String text;
}
