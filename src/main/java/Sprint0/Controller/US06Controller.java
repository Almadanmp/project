package Sprint0.Controller;

import Sprint0.Model.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class US06Controller {

   private Local mLocal;
   private Date mDate;
   private TypeSensor mType;
   private Sensor mSensor;
   private SensorList mSensorList;


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
        this.mSensorList = new SensorList(mSensor);
        if (!(mSensorList.containsSensor(mSensor))) {
            mSensorList.addSensor(mSensor);
        }
        return false;
    }
    public void setType(Sensor sensor,String name){
        TypeSensor type = new TypeSensor();
        type.setName(name);
    }

}

