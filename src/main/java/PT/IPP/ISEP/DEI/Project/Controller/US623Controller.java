package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.Reading;

import java.util.Date;

/**
 * US623: As a Regular User, I want to get the average daily rainfall in the house area for a
 * given period (days), as it is needed to assess the garden’s watering needs.
 **/

public class US623Controller {

    private House mHouse;
    private Reading mReading;

    public double getAVGDailyRainfallOnGivenPeriod(House casa, Date minDay, Date maxDay) {
        GeographicArea geoAreaHouse = casa.getmMotherGA();
        return geoAreaHouse.getSensorList().getSensorByType("Pluviosidade").getReadingList().getAverageReadingsBetweenTwoDays(minDay, maxDay);
    }
}

