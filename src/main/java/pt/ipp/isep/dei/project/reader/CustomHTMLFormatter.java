package pt.ipp.isep.dei.project.reader;

import pt.ipp.isep.dei.project.controller.ReaderController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class CustomHTMLFormatter extends Formatter {

    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");

    public String format(LogRecord record) {

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
        return "<HTML><body background=https://wallpapercave.com/wp/wp2646233.jpg><HEAD>" + "<h1> System Events Log - G2" + "</h1><br>"
                + "\n Generated on " + (new Date()) + "<hr>" +"</HEAD><BODY><PRE>\n";
    }

    public String getTail(Handler h) {
        return "</PRE></BODY></HTML>\n";
    }

    // Para efeitos de teste //
    public static void main(String[] args){

        Logger logger = Logger.getLogger(ReaderController.class.getName());
        try {
            CustomHTMLFormatter myFormat = new CustomHTMLFormatter();
            FileHandler fileHandler = new FileHandler("resources/logs/logTest.html");
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            logger.setLevel(Level.FINE);
        } catch (IOException io) {
            io.getMessage();
        }
        for (int i=0; i<50; i++){
            logger.fine("wow such html");
        }

    }

}


