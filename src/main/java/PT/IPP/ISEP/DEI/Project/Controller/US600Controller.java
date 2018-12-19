package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.Sensor;

public class US600Controller {
    private House mHouse;

    public US600Controller(House house){
        this.mHouse=house;
    }

    public double getTheMinorDistanceFromTheHouseToTheSensor(){
        return mHouse.getTheMinorDistanceFromTheHouseToTheSensor();
    }

    public Sensor getSensorWithTheMinimumDistanceToHouse(){
        return mHouse.getSensorWithTheMinimumDistanceToHouse(mHouse);
    }

    public double getTheMaximumTemperatureInTheHouseArea(){
        return getSensorWithTheMinimumDistanceToHouse().getReadingList().getMostRecentValueOfReading();
        }
}
