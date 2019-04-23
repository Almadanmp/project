package pt.ipp.isep.dei.project.controller.utils;

import pt.ipp.isep.dei.project.reader.CustomHTMLFormatter;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LogUtils {
    /**
     * Do not allow anyone to instantiate this class.
     */
    LogUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Method used to create a logger.
     * @param filePath is the path where the file is; the logger will log entries into this file.
     * @param level is the level of the logger, for instance WARNING.
     * @return is the created Logger.
     */
    public static Logger getLogger(String name, String filePath, Level level) {
        Logger logger = Logger.getLogger(name);
        try {
            CustomHTMLFormatter myFormat = new CustomHTMLFormatter();
            FileHandler fileHandler = new FileHandler(filePath, true);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            logger.setLevel(level);
        } catch (IOException io) {
            io.getMessage();
        }
        return logger;
    }

    /**
     * Closes handlers on a logger so that no .lck or extra files are generated.
     * @param logger is the logger we want to close the handlers of.
     */
    public static void closeHandlers(Logger logger){
        Handler[] handlers = logger.getHandlers();
        for (Handler h : handlers) {
            h.close();
        }
    }
}
