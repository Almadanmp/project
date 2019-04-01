package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.repository.ReadingRepository;
import pt.ipp.isep.dei.project.repository.SensorRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ReadingRepository readingRepository;

    /**
     * Method to add a reading to a matching sensor contained in the repository. The sensor is found by its ID.
     * <p>
     * sensorList   is the sensorList we want to add a reading to.
     *
     * @param sensorID     is the ID of the sensor we want to add a reading to.
     * @param readingValue is the value of the reading we want to add.
     * @param readingDate  is the date of the reading we want to add.
     * @return is true if the reading is added (if there is a sensor with an ID that matches the given ID), false
     * if there is no sensor with that ID.
     */

    public boolean addReadingToMatchingSensor(String sensorID, Double readingValue, Date readingDate) {
        Optional<Sensor> value = sensorRepository.findById(sensorID);
        if (value.isPresent()) {
            Sensor sensor = value.get();
            Reading reading = new Reading(readingValue, readingDate);
            reading.setReadingList(sensor.getReadingList());
            sensor.addReading(reading);
            readingRepository.save(reading);
            sensorRepository.save(sensor);
            return true;
        }
        return false;
    }
}
