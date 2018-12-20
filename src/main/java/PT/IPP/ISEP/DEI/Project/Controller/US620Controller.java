package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.*;

import java.util.Date;
import java.util.List;

/** US620: As a Regular User, I want to get the total rainfall in the house area for a given day.
 **/

public class US620Controller {

    public US620Controller() {
        /*
         * Builder US620Controller(), with no parameters,
         * as it will only be used in UI to apply methods on given inputs
         */
    }
    //Approach number 2

    public double getTotalRainfallOnGivenDayHouseArea(House house, Date day) {
        double sum = 0;
        double counter = 0;
        GeographicArea geoAreaHouse = house.getmMotherGA();
        List<Sensor> listRain = geoAreaHouse.getSensorList().getSensorListByType("Rain");
        for (Sensor sensor : listRain) {
            sum = sum + sensor.getReadingList().getTotalSumOfGivenDayValueReadings(day);
            counter++;
        }
        return sum / counter;
    }
}