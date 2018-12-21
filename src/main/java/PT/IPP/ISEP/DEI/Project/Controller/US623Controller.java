package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;
import PT.IPP.ISEP.DEI.Project.Model.House;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * US623UI: As a Regular User, I want to get the average daily rainfall in the house area for a
 * given period (days), as it is needed to assess the garden’s watering needs.
 **/

public class US623Controller {

    public void printGAList(GeographicAreaList geoAreaList){
        geoAreaList.printGaList(geoAreaList);
    }

    public void printHouseList(GeographicArea geoArea){
        geoArea.getHouseList().printHouseList(geoArea);
    }

    public double getAVGDailyRainfallOnGivenPeriod(GeographicArea geoArea, Date minDay, Date maxDay) {
        return geoArea.getAvgReadingsFromSensorTypeInGA("Rain", minDay, maxDay);
    }

    public Date createDate(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        return date;
    }
}

