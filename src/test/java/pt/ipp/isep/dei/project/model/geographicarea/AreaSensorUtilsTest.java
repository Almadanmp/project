package pt.ipp.isep.dei.project.model.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AreaSensorUtilsTest {
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private GeographicArea firstValidArea;
    private List<GeographicArea> validList;
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private AreaSensor validAreaSensor;
    private Date validDate3;
    private Date sensorCreationTime;
    private Date validReadingDate;
    private Date validReadingDate2;
    private Date validReadingDate3;
    private Reading validReading2;
    private Reading validReadingHotDay;
    private Reading validReadingColdDay;
    private List<Reading> validReadingList;
    private House validHouse;
    private List<String> deviceTypeString;
    private SensorType validSensortypeTemp;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat readingSD = new SimpleDateFormat("yyyy-MM-dd");
        try {
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate2 = validSdf.parse("03/09/2018 00:00:00");
            validDate3 = validSdf.parse("12/10/2018 00:00:00");
            validReadingDate = readingSD.parse("2018-10-03");
            validReadingDate2 = readingSD.parse("2018-10-04");
            validReadingDate3 = readingSD.parse("2018-10-05");
            sensorCreationTime = readingSD.parse("2016-10-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        firstValidArea = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        firstValidArea.setId(12L);
        validList = new ArrayList<>();
        validList.add(firstValidArea);
        validSensortypeTemp = new SensorType("Temperature", "Celsius");

        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", validSensortypeTemp.getName(), new Local(2, 2, 2), validDate1);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", validSensortypeTemp.getName(), new Local(10, 10, 10),
                validDate1);
        secondValidAreaSensor.setActive(true);
        validAreaSensor = new AreaSensor("SensorThree", "SensorThree", validSensortypeTemp.getName(), new Local(10, 10, 10),
                sensorCreationTime);
        validAreaSensor.setActive(true);
        validReading2 = new Reading(23, validReadingDate, "C", "SensorThree");
        validReadingHotDay = new Reading(50, validReadingDate2, "C", "SensorThree");
        validReadingColdDay = new Reading(0, validReadingDate3, "C", "SensorThree");

        validAreaSensor.addReading(validReading2);
        validReadingList = new ArrayList<>();
        validReadingList.add(validReading2);
        validReadingList.add(validReadingColdDay);
        validReadingList.add(validReadingHotDay);

        deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        validHouse.setMotherAreaID(firstValidArea.getId());
        firstValidArea.addSensor(validAreaSensor);
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensorWorks() {
        //Arrange
        Reading firstValidReading = new Reading(31, validDate1, "C", "SensorOne");
        Reading secondValidReading = new Reading(11, validDate2, "C", "SensorTwo");
        Reading thirdValidReading = new Reading(11, validDate3, "C", "SensorTwo");
        firstValidAreaSensor.addReading(firstValidReading);
        secondValidAreaSensor.addReading(secondValidReading);
        secondValidAreaSensor.addReading(thirdValidReading);

        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(firstValidAreaSensor);
        listAreaSensor.add(secondValidAreaSensor);

        //Act
        AreaSensor actualResult = AreaSensorUtils.getMostRecentlyUsedAreaSensor(listAreaSensor);

        //Assert
        assertEquals(firstValidAreaSensor, actualResult);
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensorNoReadings() {
        //Arrange
        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(firstValidAreaSensor);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> AreaSensorUtils.getMostRecentlyUsedAreaSensor(listAreaSensor));


        //Assert
        assertEquals("The sensor list has no readings available.", exception.getMessage());
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensorNoSensors() {
        //Arrange

        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> AreaSensorUtils.getMostRecentlyUsedAreaSensor(listAreaSensor));

        //Assert
        assertEquals("The sensor list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetAreaSensorsWithReadings() {
        //Arrange

        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> AreaSensorUtils.getAreaSensorsWithReadings(listAreaSensor));

        //Assert
        assertEquals("The sensor list is empty", exception.getMessage());

    }

    @Test
    void seeIfGetgetAreaSensorsOfGivenTypeEmpty() {

        //Act
        List<AreaSensor> areaSensors = new ArrayList<>();
        List<AreaSensor> actualResult = AreaSensorUtils.getAreaSensorsOfGivenType(areaSensors, "Humidity");

        //Assert
        assertEquals(areaSensors, actualResult);
    }

    @Test
    void seeIfGetgetAreaSensorsOfGivenTypeWrongType() {

        //Act
        List<AreaSensor> expectedResult = new ArrayList<>();
        List<AreaSensor> areaSensors = new ArrayList<>();
        areaSensors.add(firstValidAreaSensor);
        areaSensors.add(secondValidAreaSensor);
        List<AreaSensor> actualResult = AreaSensorUtils.getAreaSensorsOfGivenType(areaSensors, "Humidity");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetgetAreaSensorsOfGivenTypeSameType() {

        //Act
        List<AreaSensor> areaSensors = new ArrayList<>();
        areaSensors.add(firstValidAreaSensor);
        areaSensors.add(secondValidAreaSensor);
        List<AreaSensor> actualResult = AreaSensorUtils.getAreaSensorsOfGivenType(areaSensors, "Temperature");

        //Assert
        assertEquals(areaSensors, actualResult);
    }


}
