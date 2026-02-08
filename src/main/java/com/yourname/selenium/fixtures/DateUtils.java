package com.yourname.selenium.fixtures;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Default: "yyyy-MM-dd HH:mm:ss"
    public static String getCurrentFormattedDateTime() {
        return LocalDateTime.now().format(DEFAULT_FORMATTER);
    }

    // Custom pattern (e.g., "dd/MM/yyyy HH:mm")
    public static String getCurrentFormattedDateTime(String pattern) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(customFormatter);
    }
}
