package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.sensor.Reading;
import pt.ipp.isep.dei.project.model.sensor.ReadingList;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;
import pt.ipp.isep.dei.project.services.units.Unit;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AreaSensorService {

    @Autowired
    private AreaSensorRepository areaSensorRepository;

    public AreaSensorService(AreaSensorRepository areaSensorRepository) {
        this.areaSensorRepository = areaSensorRepository;
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
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        if (value.isPresent()) {
            AreaSensor areaSensor = value.get();
            Reading reading = new Reading(readingValue, readingDate, unit);
           // reading.setSensorId(sensorID);
            ReadingList sensorReadingList = areaSensor.getReadingList();
            if (sensorReadingList.contains(reading)) {
                return false;
            }
            areaSensor.addReading(reading);
            areaSensorRepository.save(areaSensor);
            return true;
        }
        return false;
    }

    /**
     * This method receives the parameters to create an Area reading and tries to add that
     * to its corresponding Area Sensor. It also receives a Logger so that it can register every
     * reading that was not added to its corresponding sensor.
     * This method will look for the sensor in the repository by its ID.
     *
     * @param sensorID     is the ID of the area sensor we want to add a reading to.
     * @param readingValue is the value of the reading we want to add.
     * @param readingDate  is the date of the reading we want to add.
     * @param unit         is the Unit of the reading we want to add.
     * @return true in case the reading was added false otherwise.
     */
    public boolean addAreaReadingToAreaSensor(String sensorID, Double readingValue, Date readingDate, Unit unit, Logger logger) {
        try {
            AreaSensor areaSensor = getAreaSensorFromRepository(sensorID);
            Reading reading = new Reading(readingValue, readingDate, "Should receive the variable unit");  //TODO Change the Reading parameter unit from STRING to UNIT
            if (addReadingToSensorInRepository(reading, areaSensor)) {
                return true;
            }
            logger.warning("The reading " + readingValue + " " + unit + " from " + readingDate + " with a sensor ID "
                    + sensorID + " wasn't added.");
            return false;

        } catch (IllegalArgumentException illegal) {
            logger.warning("The reading " + readingValue + " " + unit + " from " + readingDate + " with a sensor ID "
                    + sensorID + " wasn't added because a sensor with that ID wasn't found.");
            return false;
        }
    }

    /**
     * This method receives a sensor ID, checks if that sensor exists in the repository and returns
     * the area sensor. It throws an Illegal Argument Exception in case the sensor does not exist in repository.
     *
     * @param sensorID String of sensor ID
     * @return the area sensor that corresponds to the sensor ID.
     **/
    private AreaSensor getAreaSensorFromRepository(String sensorID) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        if (value.isPresent()) {
            return value.get();
        }
        throw new IllegalArgumentException("There is no sensor with that ID in the repository");
    }

    /**
     * This method receives a Area Reading and a Area Sensor and tries to add the reading to the
     * area sensor.
     *
     * @return true in case the reading is added to sensor, false otherwise.
     **/
    private boolean addReadingToSensorInRepository(Reading reading, AreaSensor areaSensor) {
        if (areaSensor.addReading(reading)) {
            areaSensorRepository.save(areaSensor);
            return true;
        }
        return false;
    }
}
