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
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.repository.SensorRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {MainUI.class},
        loader = AnnotationConfigContextLoader.class)
class SensorServiceTest {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private SensorRepository sensorRepository;


    @Test
    void seeIfAddReadingToMatchingSensorTrue() {
        // Arrange
        TypeSensor typeSensor = new TypeSensor("Humidity", "23");
        Local local = new Local(23, 20, 10);
        Date dateStartFunction = new GregorianCalendar(2018, Calendar.APRIL, 27).getTime();
        String sensorId = "404";
        double value = 34;
        Date date = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();
        Sensor sensor = new Sensor(sensorId, "sensor", typeSensor, local, dateStartFunction);
        sensorRepository.save(sensor);

        // Act

        boolean actualResult = sensorService.addReadingToMatchingSensor(sensorId, value, date);

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void seeIfAddReadingToMatchingSensorFalse() {
        // Arrange

        SensorList sensorList = new SensorList();
        String sensorId = "404";
        double value = 34;
        Date date = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();

        // Act

        boolean actualResult = sensorService.addReadingToMatchingSensor(sensorId, value, date);

        // Assert

        assertFalse(actualResult);

    }
}
