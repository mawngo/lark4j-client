package io.github.mawngo.lark.utils;

import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class StringUtils {
    private static final Set<Character> separators = Set.of('.', ',', ';', '_', '-');

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isEndWithSeparator(String text) {
        final char last = text.charAt(text.length() - 1);

        return Character.isWhitespace(last) || separators.contains(last);
    }
}
