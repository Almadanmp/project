package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Model.*;

import java.util.Scanner;

/**
 * US623UI: As a Regular User, I want to get the average daily rainfall in the house area for a
 * given period (days), as it is needed to assess the garden’s watering needs.
 */
public class US623UI {
    private boolean active;
    private Scanner mScanner;
    private House mHouse;
    private GeographicArea mGeoArea;



    public US623UI() {
        this.mScanner = new Scanner(System.in);
    }

    public void run(GeographicAreaList newGeoListUi, HouseList newHouseListUi) {
        this.active = true;
        getInputGeographicArea(newGeoListUi);
        getInputHouse(newHouseListUi);
      //  getInputRoom();
       // getInputDate();
        //updateModel();
       // displayState();
    }

    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        System.out.println("Please Select One Of The Existing Geographic Areas: " + newGeoListUi.printGeoAreaList());
        String  geoArea = mScanner.nextLine();
        mGeoArea = newGeoListUi.getGeographicAreaByName(geoArea);
        }

    private void getInputHouse(HouseList newHouseListUi) {
        System.out.println("Please Select One Of The Existing Houses on " + mGeoArea);
        String  house = mScanner.nextLine();
        mHouse = newHouseListUi.getHouseByDesignation(house);
    }

    private String createInputMsg(String inputType) {
        return "Please Insert " + inputType + " for the New Geographic Area: ";
    }

    private String createInvalidStringMsg(String inputType) {
        return "That's not a valid " + inputType + ". Please insert only Alphabetic Characters";
    }

    private String readInputString(String inputType) {
        System.out.print(createInputMsg(inputType));

        while (!mScanner.hasNext("[a-zA-Z\\sà-ùÀ-Ù]*")) {
            System.out.println(createInvalidStringMsg(inputType));
            mScanner.next();
        }
        return mScanner.next();
    }
}

