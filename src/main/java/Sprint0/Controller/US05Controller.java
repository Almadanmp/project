package Sprint0.Controller;

import Sprint0.Model.Sensor;
import Sprint0.Model.SensorList;

import java.util.List;

/**
 * User Story 05
 * As a system administrator, I wish to specify the type of reading that a sensor is capable
 * of registering.
 */


public class US05Controller {
    private SensorList mSensorList;

    public US05Controller(SensorList list) {
        this.mSensorList = list;
    }

    public boolean setTypeSensor(String name, String typeToSet) {
        if (checkIfListValid(mSensorList.getSensorList())) {
            for (Sensor sensor : mSensorList.getSensorList())
                if (sensor.getName().equals(name)) {
                    sensor.getTypeSensor().setName(typeToSet);
                    return true;
                }
        }
        return false;
    }

    public SensorList getSensorList() {
        return this.mSensorList;
    }

    private boolean checkIfListValid(List<Sensor> values) {
        if (values == null || values.isEmpty()) {
            return false;
        }
        return true;
    }
}


