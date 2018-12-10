package Sprint0.Controller;

import Sprint0.Model.AuxiliaryMethods;
import Sprint0.Model.Sensor;
import Sprint0.UI.MainUI;
import Sprint0.UI.US05UI;
import sun.applet.Main;

import java.util.List;

/**
 * User Story 05
 * As a system administrator, I wish to specify the type of reading that a sensor is capable
 * of registering.
 */


public class US05Controller {
    private Sensor mModel;

    public US05Controller() {
    }

    public boolean setTypeSensor(String name, String typeToSet) {
        if (checkIfListValid(MainUI.mSensorList.getSensorList())) {
            for (Sensor sensor : MainUI.mSensorList.getSensorList())
                if (sensor.getName().equals(name)) {
                    sensor.getTypeSensor().setName(typeToSet);
                    return true;
                }
        }
        return false;
    }

    public String getTypeSensor() {
        return mModel.getTypeSensor().getName();
    }

    private boolean checkIfListValid(List<Sensor> values) {
        if (values == null || values.isEmpty()) {
            return false;
        }
        return true;
    }
}


