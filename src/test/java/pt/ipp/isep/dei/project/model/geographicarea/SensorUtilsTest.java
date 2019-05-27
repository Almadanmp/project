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
class SensorUtilsTest {
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private Date validDate3; // Date 12/12/2018
    private Date validDate4;
    private Date validDate5;
    private Date sensorCreationTime;
    private Date validReadingDate;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat readingSD = new SimpleDateFormat("yyyy-MM-dd");
        try {
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate2 = validSdf.parse("03/09/2018 00:00:00");
            validDate3 = validSdf.parse("12/12/2018 00:00:00");
            validDate4 = validSdf.parse("01/09/2018 00:00:00");
            validDate5 = validSdf.parse("1/1/2017 00:00:00");
            validReadingDate = readingSD.parse("2018-10-03");
            sensorCreationTime = readingSD.parse("2016-10-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GeographicArea firstValidArea = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        firstValidArea.setId(12L);
        SensorType validSensorTypeTemp = new SensorType("Temperature", "Celsius");

        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", validSensorTypeTemp.getName(), new Local(2, 2, 2), validDate1);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", validSensorTypeTemp.getName(), new Local(10, 10, 10),
                validDate5);
        secondValidAreaSensor.setActive(true);
        AreaSensor thirdValidAreaSensor = new AreaSensor("SensorThree", "SensorTwo", validSensorTypeTemp.getName(), new Local(10, 10, 10),
                validDate1);
        thirdValidAreaSensor.setActive(true);
        AreaSensor validAreaSensor = new AreaSensor("SensorThree", "SensorThree", validSensorTypeTemp.getName(), new Local(10, 10, 10),
                sensorCreationTime);
        validAreaSensor.setActive(true);
        Reading validReading2 = new Reading(23, validReadingDate, "C", "SensorThree");
        validAreaSensor.addReading(validReading2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
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
        Reading forthValidReading = new Reading(15, validDate4, "C", "SensorTwo");
        firstValidAreaSensor.addReading(firstValidReading);
        secondValidAreaSensor.addReading(secondValidReading);
        secondValidAreaSensor.addReading(thirdValidReading);
        secondValidAreaSensor.addReading(forthValidReading);
        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(firstValidAreaSensor);
        listAreaSensor.add(secondValidAreaSensor);

        //Act

        AreaSensor actualResult = SensorUtils.getMostRecentlyUsedAreaSensor(listAreaSensor);

        //Assert

        assertEquals(secondValidAreaSensor, actualResult);
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensorWorksNoReadings() {
        //Arrange

        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(firstValidAreaSensor);

        //Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> SensorUtils.getMostRecentlyUsedAreaSensor(listAreaSensor));


        //Assert

        assertEquals("The sensor list has no readings available.", exception.getMessage());
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensorWorksNoSensors() {
        //Arrange

        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> SensorUtils.getMostRecentlyUsedAreaSensor(listAreaSensor));

        //Assert

        assertEquals("The sensor list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensorWorksForRecentDateBeforeTestDateWorks() {
        // Arrange

        Reading firstValidReading = new Reading(31, validDate1, "C", "SensorOne");
        Reading secondValidReading = new Reading(11, validDate2, "C", "SensorTwo");
        Reading thirdValidReading = new Reading(11, validDate3, "C", "SensorTwo");
        firstValidAreaSensor.addReading(firstValidReading);
        secondValidAreaSensor.addReading(secondValidReading);
        secondValidAreaSensor.addReading(thirdValidReading);

        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(firstValidAreaSensor);
        listAreaSensor.add(secondValidAreaSensor);

        // Act

        AreaSensor actualResult = SensorUtils.getMostRecentlyUsedAreaSensor(listAreaSensor);

        // Assert

        assertEquals(secondValidAreaSensor, actualResult);
    }

    @Test
    void seeIfGetAreaSensorsWorks() {
        //Arrange

        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act


        Throwable exception = assertThrows(IllegalArgumentException.class, () -> SensorUtils.getAreaSensorsWithReadings(listAreaSensor));

        //Assert
        assertEquals("The sensor list is empty", exception.getMessage());
    }

    @Test
    void seeIfGetAreaSensorsOfGivenTypeWorksEmpty() {
        //Act

        List<AreaSensor> areaSensors = new ArrayList<>();
        List<AreaSensor> actualResult = SensorUtils.getAreaSensorsOfGivenType(areaSensors, "Humidity");

        //Assert

        assertEquals(areaSensors, actualResult);
    }

    @Test
    void seeIfGetAreaSensorsOfGivenTypeWorksWrongType() {
        //Act

        List<AreaSensor> expectedResult = new ArrayList<>();
        List<AreaSensor> areaSensors = new ArrayList<>();
        areaSensors.add(firstValidAreaSensor);
        areaSensors.add(secondValidAreaSensor);
        List<AreaSensor> actualResult = SensorUtils.getAreaSensorsOfGivenType(areaSensors, "Humidity");

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAreaSensorsOfGivenTypeWorksSameType() {
        //Act

        List<AreaSensor> areaSensors = new ArrayList<>();
        areaSensors.add(firstValidAreaSensor);
        areaSensors.add(secondValidAreaSensor);
        List<AreaSensor> actualResult = SensorUtils.getAreaSensorsOfGivenType(areaSensors, "Temperature");

        //Assert

        assertEquals(areaSensors, actualResult);
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensorsWorks(){
        // Arrange

        Reading firstValidReading = new Reading(31, validDate3, "C", "SensorOne");
        Reading secondValidReading = new Reading(11, validDate1, "C", "SensorTwo");
        firstValidAreaSensor.addReading(firstValidReading);
        secondValidAreaSensor.addReading(secondValidReading);
        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(secondValidAreaSensor);
        listAreaSensor.add(firstValidAreaSensor);

        // Act

        AreaSensor actualResult = SensorUtils.getMostRecentlyUsedAreaSensor(listAreaSensor);

        // Assert

        assertEquals(firstValidAreaSensor, actualResult);
    }
}
