package be.kdg.logging;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * This class extends the Formatter class and overrides the format method.
 * The format method is used to format the log records.
 */
public class SmallLogFormatter extends Formatter {
    /**
     * This method formats the log record.
     * It gets the instant of the log record and converts it to LocalDateTime.
     * It then appends the level of the log record and the formatted message.
     *
     * @param record the log record to be formatted
     * @return the formatted log record as a string
     */
    @Override
    public String format(LogRecord record) {
        return LocalDateTime.ofInstant(record.getInstant(), ZoneId.systemDefault()) +
                " Level: " + record.getLevel() +
                "\nmelding:\n" + MessageFormat.format(record.getMessage(), record.getParameters()) + "\n";
    }
}