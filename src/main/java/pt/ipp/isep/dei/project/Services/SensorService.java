package pt.ipp.isep.dei.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.repository.*;

import java.util.Date;

@Service
public class SensorService {

    @Autowired
    private SensorListRepository sensorListRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private TypeSensorRepository typeSensorRepository;

    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private ReadingRepository readingRepository;

    public void addSensor(Sensor sensorToAdd, SensorList sensorList) {
        sensorList.add(sensorToAdd);
        sensorToAdd.setSensorList(sensorList);
        sensorRepository.save(sensorToAdd);
    }

    public void addSensorLocalization(Sensor sensor, Local local) {
        sensor.setLocal(local);
        localRepository.save(local);
    }

    public void setSensorList(GeographicArea geographicArea, SensorList sensorList) {
        geographicArea.setSensorList(sensorList);
        sensorListRepository.save(sensorList);
    }

    public void setSensorType(Sensor sensor, TypeSensor typeSensor) {
        sensor.setTypeSensor(typeSensor);
        typeSensorRepository.save(typeSensor);
    }

    public void setReadingList(Sensor sensor, ReadingList readingList) {
        sensor.setReadingList(readingList);
        readingListRepository.save(readingList);
    }

    public boolean addReadingToMatchingSensor(SensorList sensorList, String sensorID, Double readingValue, Date readingDate) {
        sensorList.addReadingToMatchingSensor(sensorID, readingValue, readingDate);
        Sensor sensor = sensorRepository.findById(sensorID).get();
        Reading reading = new Reading(readingValue, readingDate);
        reading.setReadingList(sensor.getReadingList());
        readingRepository.save(reading);
        return true;
    }

}
