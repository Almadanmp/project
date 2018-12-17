package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.*;

import java.util.Date;

/** US620: As a Regular User, I want to get the total rainfall in the house area for a given day.
 **/

public class US620Controller {

    private House mHouse;
    private Reading mReading;

    public US620Controller() {
        /*
         * Builder US620Controller(), with no parameters,
         * as it will only be used in UI to apply methods on given inputs
         */
    }
    public double getTotalRainfallOnGivenDay(Date dia) {
        GeographicArea geoAreaHouse = this.mHouse.getmMotherGA();
        return geoAreaHouse.getSensorList().getSensorByName("Pluviosidade").getReadingList().getTotalSumOfGivenDayValueReadings(dia);
    }
}
