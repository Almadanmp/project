package Sprint0.Controller;

import Sprint0.Model.Local;
import Sprint0.Model.ReadingList;
import Sprint0.Model.Sensor;
import Sprint0.Model.TypeSensor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class US06Controller {

    private Sensor mSensor;
    private String mName;
    private TypeSensor mTypeSensor;
    private Local mLocal;
    private Date mDateStartedFunctioning;
    private ReadingList mReadingList;
    private List<Sensor> mSensorList;

    public US06Controller(Sensor sensor) {
        this.mSensor = sensor;
    }

    boolean addSensor(Sensor sensorToAdd){
        mSensorList = new ArrayList<>();
        if (!(mSensorList.contains(sensorToAdd))) {
            mSensorList.add(sensorToAdd);
        }
        return false;
    }

}
