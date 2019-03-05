package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.BeforeEach;
import pt.ipp.isep.dei.project.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CSVReader test class.
 */
class CSVReaderTest {

    // Common artifacts for testing in this class.
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";
    private House validHouse;
    private Sensor validSensor;
    private Date validDate1 = new Date();

    @BeforeEach
    void arrangeArtifacts() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        SimpleDateFormat validSdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = validSdf2.parse("2018-10-31T08:00:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60,
                180, deviceTypeString);
        validSensor = new Sensor("Sensor1", new TypeSensor("Temperature", "Celsius"), new Local(
                30, 20, 10), new Date());
    }
}