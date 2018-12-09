package Sprint0.Controller;

import Sprint0.Model.Sensor;
import Sprint0.UI.US05UI;

/**
 * User Story 05
 * As a system administrator, I wish to specify the type of reading that a sensor is capable
 * of registering.
 */


public class US05Controller {
    private Sensor mModel;
    private US05UI mUI;

    public US05Controller(Sensor model, US05UI UI) {
        this.mModel = model;
        this.mUI = UI;
    }

    public void setTypeReadingOfSensor(String typeSensor) {
        mModel.getTypeSensor().setName(typeSensor);
    }

    public String getTypeReadingOfSensor(){
        return mModel.getTypeSensor().getName();
    }

    public void updateUI(){
        mUI.printSensorTypeOfReadings(mModel.getTypeSensor().getName());
    }
}


