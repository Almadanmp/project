package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.SensorList;


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
        return mSensorList.setTypeSensorByString(name,typeToSet);
    }

    public SensorList getSensorList() {
        return this.mSensorList;
    }
}


