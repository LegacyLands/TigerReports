package fr.mrtigreroux.tigerreports.logs;

/**
 * @author MrTigreroux
 */
public enum Level {
    
    DEBUG('D', "DEBUG", java.util.logging.Level.FINE, "\033[37m"),
    INFO('I', "INFO", java.util.logging.Level.INFO, null),
    WARN('W', "WARN", java.util.logging.Level.WARNING, "\033[33m"),
    ERROR('E', "ERROR", java.util.logging.Level.SEVERE, "\033[31m");
    
    private final char id;
    private final String displayName;
    private final java.util.logging.Level loggingLevel;
    final String ansiColor;
    
    public static Level fromId(char id) {
        for (Level level : values()) {
            if (level.id == id) {
                return level;
            }
        }
        return null;
    }
    
    public static Level fromDisplayName(String name) {
        for (Level level : values()) {
            if (level.getDisplayName().equals(name)) {
                return level;
            }
        }
        return null;
    }
    
    public static Level fromLoggingLevel(java.util.logging.Level loggingLevel) {
        return fromLoggingLevelName(loggingLevel.getName(), true);
    }
    
    public static Level fromLoggingLevelName(String name, boolean useInfoAsDefault) {
        switch (name) {
            case "INFO":
                return Level.INFO;
            case "WARNING":
                return Level.WARN;
            case "SEVERE":
                return Level.ERROR;
            case "FINE":
                return Level.DEBUG;
            default:
                return useInfoAsDefault ? Level.INFO : null;
        }
    }
    
    private Level(char id, String displayName, java.util.logging.Level loggingLevel,
            String ansiColor) {
        this.id = id;
        this.displayName = displayName;
        this.loggingLevel = loggingLevel;
        this.ansiColor = ansiColor;
    }
    
    String getColoredMessage(String message) {
        if (ansiColor != null && !ansiColor.isEmpty()) {
            return ansiColor + message + "\033[0m";
        } else {
            return message;
        }
    }
    
    public java.util.logging.Level getLoggingLevel() {
        return loggingLevel;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDisplayName(boolean fixedSize) {
        return (fixedSize && displayName.length() == 4 ? " " : "") + displayName;
    }
    
}
