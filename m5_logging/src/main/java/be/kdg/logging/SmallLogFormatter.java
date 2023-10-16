package be.kdg.logging;

import javax.annotation.processing.Messager;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SmallLogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return LocalDateTime.ofInstant(record.getInstant(), ZoneId.systemDefault()) +
                " Level: " + record.getLevel() +
                "\nmelding:\n" + MessageFormat.format(record.getMessage(), record.getParameters()) + "\n";
    }
}
