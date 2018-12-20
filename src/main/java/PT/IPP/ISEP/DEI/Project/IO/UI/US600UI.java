package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US600Controller;
import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;
import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.Sensor;

import java.util.Scanner;

public class US600UI {
    private boolean active;
    private GeographicArea mGeoArea;
    private String mGeoAreaName;
    private String mHouseName;
    private House mHouse;
    private double mCurrentHouseAreaTemperature;

    public US600UI() {
        this.active = false;
    }

    public void run(GeographicAreaList list) {
        while (this.active = true) {
            if (!displayGeographicAreaListGetInputAndUpdate(list)) {
                return;
            }
            if(!displayHouseListGetInputAndDisplayState(list)){
                return;
            }
            updateModel();
            displayState();
            return;
        }

    }

    public boolean displayGeographicAreaListGetInputAndUpdate(GeographicAreaList list) {
        US600Controller ctrl = new US600Controller(list);
        ctrl.printGeoAreaList();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Geographic Area in which your House is in: ");
        this.mGeoAreaName = scanner.next();
        if (ctrl.validateIfGeographicAreaToGeographicAreaList(mGeoAreaName)) {
            System.out.println("You have inserted the Geographic Area " + mGeoAreaName);
            this.mGeoArea = ctrl.getGeographicAreaByName(mGeoAreaName);
        } else {
            System.out.println("That Geographic Area does not belong to the List.");
            return false;
        }
        return true;
    }

    public boolean displayHouseListGetInputAndDisplayState(GeographicAreaList list) {
        US600Controller ctrl = new US600Controller(list);
        ctrl.printHouseList(mGeoArea);
        System.out.println("Please insert name from the list: ");
        Scanner scanner = new Scanner(System.in);
        this.mHouseName=scanner.next();
        if (ctrl.matchHouseFromList(mHouseName)) {
            System.out.println("You have inserted the house " + mHouseName);
            this.mHouse = ctrl.getHouseByName(mHouseName);
        } else {
            System.out.println("That house does not belong to the list.");
            return false;
        }
        return true;
    }

    public void updateModel(){
        US600Controller ctrl = new US600Controller(mHouse);
        mCurrentHouseAreaTemperature = ctrl.getCurrentTemperatureInTheHouseArea();
    }

    public void displayState(){
        System.out.println("The current temperature in the house area is: " + mCurrentHouseAreaTemperature);
    }

}
