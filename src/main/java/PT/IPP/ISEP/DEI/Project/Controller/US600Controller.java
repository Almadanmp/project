package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.Sensor;

public class US600Controller {
    private House mHouse;

    public US600Controller(){}

    public double getTheMinorDistanceFromTheHouseToTheSensor(House house){
        double distance=0;

        for (Sensor s: house.getmMotherGA().getSensorList().getSensors()){
            if (house.calculateDistanceToSensor(s)>distance){
                distance = house.calculateDistanceToSensor(s);

            }
    }
    return distance;
    }

    public Sensor getSensorWithTheMinimumDistanceToHouse(House house){
        for (Sensor s: house.getmMotherGA().getSensorList().getSensors()) {
            if (getTheMinorDistanceFromTheHouseToTheSensor(house) == s.getDistanceToHouse(house)){
                return s;
            }
        }
        return null;
    }

    public double getTheMaximumTemperatureInTheHouseArea(){
        double result=-275;
        for (Sensor s: mHouse.getmMotherGA().getSensorList().getSensors()){
            result = s.getReadingList().getMostRecentValueOfReading();
        }
        return result;
        }
}
