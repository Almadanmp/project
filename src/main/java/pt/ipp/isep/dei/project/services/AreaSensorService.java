package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.sensor.AreaReadingList;
import pt.ipp.isep.dei.project.model.sensor.AreaReading;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.repository.SensorRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class AreaSensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public AreaSensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    /**
     * Method to add a reading to a matching sensor contained in the repository. The sensor is found by its ID.
     * <p>
     * sensorList   is the sensorList we want to add a reading to.
     * <p>
     * to avoid duplicated readings we will check first if the reading list of the sensor already has the reading to import
     * (has the same date) and will only add it if false.
     *
     * @param sensorID     is the ID of the sensor we want to add a reading to.
     * @param readingValue is the value of the reading we want to add.
     * @param readingDate  is the date of the reading we want to add.
     * @return is true if the reading is added (if there is a sensor with an ID that matches the given ID), false
     * if there is no sensor with that ID.
     */

    public boolean addReadingToMatchingSensor(String sensorID, Double readingValue, Date readingDate, String unit) {
        Optional<AreaSensor> value = sensorRepository.findById(sensorID);
        if (value.isPresent()) {
            AreaSensor areaSensor = value.get();
            AreaReading areaReading = new AreaReading(readingValue, readingDate, unit);
            AreaReadingList sensorAreaReadingList = areaSensor.getAreaReadingList();
            if (sensorAreaReadingList.contains(areaReading)) {
                return false;
            }
            areaSensor.addReading(areaReading);
            sensorRepository.save(areaSensor);
            return true;
        }
        return false;
    }
}
