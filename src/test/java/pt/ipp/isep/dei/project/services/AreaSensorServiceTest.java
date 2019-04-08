package pt.ipp.isep.dei.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.io.ui.MainUI;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;

@ExtendWith(MockitoExtension.class)
class AreaSensorServiceTest {


    private AreaSensorService areaSensorService;

    @Mock
    private AreaSensorRepository areaSensorRepository;


//    @Test
//    void seeIfAddReadingToMatchingSensorTrue() {
//        areaSensorService = new AreaSensorService(areaSensorRepository);
//        // Arrange
//        SensorType sensorType = new SensorType("Humidity", "23");
//        Local local = new Local(23, 20, 10);
//        Date dateStartFunction = new GregorianCalendar(2018, Calendar.APRIL, 27).getTime();
//        String sensorId = "404";
//        double value = 34;
//        Date date = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();
//        AreaSensor areaSensor = new AreaSensor(sensorId, "sensor", sensorType, local, dateStartFunction);
//        areaSensorRepository.save(areaSensor);
//
//        // Act
//
//        boolean actualResult = areaSensorService.addReadingToMatchingSensor(sensorId, value, date, new Celsius());
//
//        // Assert
//
//        assertTrue(actualResult);
//
//    }
//
//    @Test
//    void seeIfAddReadingToMatchingSensorFalse() {
//        // Arrange
//        String sensorId = "404";
//        double value = 34;
//        Date date = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();
//
//        // Act
//
//        boolean actualResult = areaSensorService.addReadingToMatchingSensor(sensorId, value, date, new Celsius());
//
//        // Assert
//
//        assertFalse(actualResult);
//
//    }
}
