package pt.ipp.isep.dei.project.io.ui.reader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * CustomFormatter Class. Formats the output of the logs.
 */
public class CustomFormatter extends Formatter {

    public String format(LogRecord record) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date(record.getMillis()))).append(" - ");
        builder.append("[").append(record.getSourceClassName()).append(".");
        builder.append(record.getSourceMethodName()).append("] - ");
        builder.append("[").append(record.getLevel()).append("] - ");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
    }

}