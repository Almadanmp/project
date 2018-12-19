package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.Sensor;

public class US600Controller {
    private House mHouse;

    public US600Controller(House house){
        this.mHouse=house;
    }

    public double getTheMinorDistanceFromTheHouseToTheSensor(){
        double distance=0;

        for (Sensor s: this.mHouse.getmMotherGA().getSensorList().getSensors()){
            if (this.mHouse.calculateDistanceToSensor(s)>distance){
                distance = this.mHouse.calculateDistanceToSensor(s);

            }
    }
    return distance;
    }

    public Sensor getSensorWithTheMinimumDistanceToHouse(){
        for (Sensor s: this.mHouse.getmMotherGA().getSensorList().getSensors()) {
            if (getTheMinorDistanceFromTheHouseToTheSensor() == s.getDistanceToHouse(this.mHouse)){
                return s;
            }
        }
        return null;
    }

    public double getTheMaximumTemperatureInTheHouseArea(){
        return getSensorWithTheMinimumDistanceToHouse().getReadingList().getMostRecentValueOfReading();
        }
}
