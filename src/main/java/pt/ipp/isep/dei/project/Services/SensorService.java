package pt.ipp.isep.dei.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.repository.LocalRepository;
import pt.ipp.isep.dei.project.repository.SensorListRepository;
import pt.ipp.isep.dei.project.repository.SensorRepository;
import pt.ipp.isep.dei.project.repository.TypeSensorRepository;

import java.util.List;

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

    public void addSensor(Sensor sensorToAdd, SensorList sensorList) {
        sensorList.add(sensorToAdd);
        sensorToAdd.setSensorList(sensorList);
        sensorRepository.save(sensorToAdd);
    }

    public void addSensorsToList(Sensor sensorToAdd, List<Sensor> sensorList) {
        sensorList.add(sensorToAdd);
        SensorList sensorList1 = new SensorList();
        sensorList1.setSensors(sensorList);
        sensorToAdd.setSensorList(sensorList1);
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
}
