package pt.ipp.isep.dei.project.controller.utils;

import org.junit.jupiter.api.Test;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class LogUtilsTest {
    @Test
    void seeIfConstructorThrowsException() {
        assertThrows(UnsupportedOperationException.class,
                LogUtils::new);
    }

    @Test
    void seeIfGetLoggerWorks(){
        // Act

        Logger l1 = LogUtils.getLogger("MyLogger", "resources/logs/houseReadingLogs.log", Level.FINE);
        LogUtils.closeHandlers(l1);

        // Assert

        assertEquals(l1.getLevel(), Level.FINE);
        assertEquals(l1.getName(), "MyLogger");
    }

    @Test
    void seeIfGetLoggerWorksWrongPath(){
        // Arrange

        Level expectedLevel = null;
        String expectedName = "myTestLogger";

        // Act

        Logger l2 = LogUtils.getLogger("myTestLogger", "Z:\\Users\\TestUser\\InvalidPath\\Invalid\\", Level.FINE);

        // Assert

        assertEquals(expectedName, l2.getName());
        assertEquals(expectedLevel, l2.getLevel());
    }

    @Test
    void seeIfCloseHandlersWorks(){
        // Arrange

        Logger l1 = LogUtils.getLogger("MyLogger", "resources/logs/houseReadingLogs.log", Level.FINE);
        LogRecord recordToLog = new LogRecord(Level.FINE, "testLog");


        // Act

        LogUtils.closeHandlers(l1);
        Handler[] handlers = l1.getHandlers();
        Handler handler = handlers[0];

        // Assert

        assertFalse(handler.isLoggable(recordToLog));
    }
}
