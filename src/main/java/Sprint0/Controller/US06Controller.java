package Sprint0.Controller;

import Sprint0.Model.*;
import Sprint0.UI.MainUI;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * User Story 06
 * As a system administrator, I want to be able to manually input a new sensor and add it to a pre-input Geographic Area
 */

public class US06Controller {

   private Local mLocal;
   private Date mDate;
   private TypeSensor mType;
   private Sensor mSensor;


    public US06Controller() {
    }
    public Local createLocal(Double latitude,Double longitude,Double altitude){
        Local local = new Local(latitude,longitude,altitude);
        this.mLocal = local;
        return this.mLocal;
    }
    public Date createData(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        this.mDate = date;
        return this.mDate;
    }
    public TypeSensor createType(String sensorType){
        this.mType = new TypeSensor(sensorType);
        return this.mType;
    }
    public Sensor createSensor (String name, TypeSensor type, Local local, Date date){
        this.mSensor = new Sensor(name,type,local,date);
        return mSensor;
    }
    public boolean addSensor(){
        if (!(MainUI.mSensorList.containsSensor(this.mSensor))) {
            MainUI.mSensorList.addSensor(this.mSensor);
        }
        return false;
    }
}

