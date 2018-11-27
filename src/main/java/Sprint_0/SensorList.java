package Sprint_0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SensorList {
    private List<Sensor> sensorList;

    public SensorList(Sensor[] sensorsToAdd) {
        sensorList = new ArrayList<>();
        if (sensorsToAdd.length > 0) {
            for (int i = 0; i < sensorsToAdd.length; i++) {
                sensorList.add(sensorsToAdd[i]);
            }
        }
    }

    public SensorList(){}

    public SensorList(Sensor sensorToAdd) {
        sensorList = new ArrayList<>();
        sensorList.add(sensorToAdd);
    }

    public void addSensor(Sensor sensorToAdd) {
        if (!(sensorList.contains(sensorToAdd))) {
            sensorList.add(sensorToAdd);
        }
    }

    public boolean containsSensor(Sensor sensor){
        return sensorList.contains(sensor);
    }

    public Sensor[] getSensors() {
        int sizeOfResultArray = sensorList.size();
        Sensor[] result = new Sensor[sizeOfResultArray];
        for (int i = 0; i < sensorList.size(); i++) {
            result[i] = sensorList.get(i);
        }
        return result;
    }

    public List getSensorList() {
        List result = this.sensorList;
        return result;
    }

    public void removeSensor(Sensor sensorToRemove) {
        sensorList.remove(sensorToRemove);
    }

    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof SensorList)) {
            return false;
        }
        SensorList list = (SensorList) testObject;
        if (Arrays.equals(this.getSensors(), list.getSensors())){
            return true;
        }
        return false;
    }
}
