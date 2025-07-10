package ru.voldemar.bonch_prak_2.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static int parseIntOrDefault(String toParse, int defaultValue) {
        try {
            int res = Integer.parseInt(toParse);
            return res <= 0 ? defaultValue : res;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
