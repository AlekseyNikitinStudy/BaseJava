package ru.javawebinar.basejava.util;

public class StringUtil {
    public static String removeUselessSymbols(String string) {
        return string == null ? null
                : string.trim().replaceAll("\\n", "").replaceAll("\\r", "");
    }
}
