package pt.ipp.isep.dei.project.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.io.ui.MainUI;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.repository.SensorRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {MainUI.class},
        loader = AnnotationConfigContextLoader.class)
class AreaSensorServiceTest {

    @Autowired
    private AreaSensorService areaSensorService;

    @Autowired
    private SensorRepository sensorRepository;


    @Test
    void seeIfAddReadingToMatchingSensorTrue() {
        // Arrange
        SensorType sensorType = new SensorType("Humidity", "23");
        Local local = new Local(23, 20, 10);
        Date dateStartFunction = new GregorianCalendar(2018, Calendar.APRIL, 27).getTime();
        String sensorId = "404";
        double value = 34;
        Date date = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();
        AreaSensor areaSensor = new AreaSensor(sensorId, "sensor", sensorType, local, dateStartFunction);
        sensorRepository.save(areaSensor);

        // Act

        boolean actualResult = areaSensorService.addReadingToMatchingSensor(sensorId, value, date, "C");

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void seeIfAddReadingToMatchingSensorFalse() {
        // Arrange
        String sensorId = "404";
        double value = 34;
        Date date = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();

        // Act

        boolean actualResult = areaSensorService.addReadingToMatchingSensor(sensorId, value, date, "C");

        // Assert

        assertFalse(actualResult);

    }
}
