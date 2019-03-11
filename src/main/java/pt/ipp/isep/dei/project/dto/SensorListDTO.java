package pt.ipp.isep.dei.project.dto;

import java.util.ArrayList;
import java.util.List;

public class SensorListDTO {
    private List<SensorDTO> sensors;

    /**
     * SensorList() empty constructor that initializes an ArrayList of Sensors.
     */
    public SensorListDTO() {
        this.sensors = new ArrayList<>();
    }

    /**
     * Constructor to always create an Array of Sensors.
     *
     * @param sensorToAdd the selected sensor.
     */
    public SensorListDTO(SensorDTO sensorToAdd) {
        sensors = new ArrayList<>();
        sensors.add(sensorToAdd);
    }

    public List<SensorDTO> getSensors() {
        return sensors;
    }

    /**
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param sensorToAdd is the sensor we want to add to the sensorList.
     * @return true if sensor was successfully added to the SensorList, false otherwise.
     */

    public boolean add(SensorDTO sensorToAdd) {
        if (!(sensors.contains(sensorToAdd))) {
            sensors.add(sensorToAdd);
            return true;
        }
        return false;
    }
}
