package com.Makylone.OrderCLI.Utils;

import java.nio.file.Path;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeParseException;

public class DateParser {
    public static boolean isDirectoryAYear(Path directory) {
        try {
            Year.parse(directory.getFileName().toString());
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static boolean isDirectoryAMonth(Path directory) {
        try {
            Month.valueOf(directory.getFileName().toString().toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
