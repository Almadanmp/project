package pt.ipp.isep.dei.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.SensorList;
import pt.ipp.isep.dei.project.repository.ReadingRepository;
import pt.ipp.isep.dei.project.repository.SensorRepository;

import java.util.Date;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ReadingRepository readingRepository;


    public boolean addReadingToMatchingSensor(SensorList sensorList, String sensorID, Double readingValue, Date readingDate) {
        sensorList.addReadingToMatchingSensor(sensorID, readingValue, readingDate);
        Sensor sensor = sensorRepository.findById(sensorID).get();
        Reading reading = new Reading(readingValue, readingDate);
        reading.setReadingList(sensor.getReadingList());
        readingRepository.save(reading);
        return true;
    }

}
