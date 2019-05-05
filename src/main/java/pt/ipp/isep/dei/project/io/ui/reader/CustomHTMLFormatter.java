package pt.ipp.isep.dei.project.io.ui.reader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class CustomHTMLFormatter extends Formatter {

    public String format(LogRecord record) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        StringBuffer buf = new StringBuffer(1000);
        if (record.getLevel().intValue() >= Level.FINE.intValue()) {
            buf.append("<b>");
            buf.append(record.getLevel());
            buf.append("</b>");
        } else {
            buf.append(record.getLevel());
        }
        buf.append(' ');
        buf.append("||");
        buf.append(' ');
        buf.append("<b>Date: </b>");
        buf.append(df.format(new Date(record.getMillis())));
        buf.append(' ');
        buf.append("||");
        buf.append(' ');
        buf.append("<b>Local: </b>");
        buf.append(record.getSourceClassName()).append(".");
        buf.append(record.getSourceMethodName());
        buf.append(' ');
        buf.append("||");
        buf.append(' ');
        buf.append("<b>Message: </b>");
        buf.append(formatMessage(record));
        buf.append('\n');
        return buf.toString();
    }

    public String getHead(Handler h) {
        return "<HTML><body background=https://wallpapercave.com/wp/wp2646233.jpg><HEAD>" + "</HEAD><BODY><PRE>\n";
    }

    public String getTail(Handler h) {
        return "</PRE></BODY></HTML>\n";
    }

}


