package com.smartpay.gateway.utils;

import java.time.LocalDateTime;

public class LoggerUtil {
    public static void logInfo(String message) {
        System.out.println("[INFO] " + LocalDateTime.now() + " - " + message);
    }
    public static void logError(String message, Exception e) {
        System.err.println("[ERROR] " + LocalDateTime.now() + " - " + message);
        if (e != null) {
            System.err.println("Cause: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    public static void logError(String message) {
        logError(message, null); 
    }
    public static String maskSensitive(String data) {
        if (data == null || data.length() < 3) return "***";
        return data.substring(0, 2) + "*****";
    }
}
