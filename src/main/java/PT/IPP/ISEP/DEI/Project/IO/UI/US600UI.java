package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US600Controller;
import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;
import PT.IPP.ISEP.DEI.Project.Model.House;

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
            if (!getInputGeographicArea(list)) {
                return;
            }
            if(!getInputHouse(list)){
                return;
            }
            updateModel();
            displayState();
            return;
        }

    }


    private boolean getInputGeographicArea(GeographicAreaList newGeoListUi) {
        boolean activeInput = false;
        Double option;
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");
        Scanner mScanner = new Scanner(System.in);
        US600Controller ctrl = new US600Controller(newGeoListUi);
        while (!activeInput) {
            ctrl.printGAList(newGeoListUi);

            while (!mScanner.hasNextDouble()) {
                System.out.println("Please enter a valid option");
                mScanner.next();
            }

            option = mScanner.nextDouble();
            int aux = option.intValue();
            if (aux >= 0 && aux < newGeoListUi.getGeographicAreaList().size()) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                activeInput = true;

            } else {
                System.out.println("Please enter a valid option");
                return false;
            }
        } return true;
    }

    private boolean getInputHouse(GeographicAreaList newGeoListUi) {
        boolean activeInput = false;
        Double option;
        System.out.println("Please select one of the existing houses on the selected geographic area: ");
        Scanner mScanner = new Scanner(System.in);
        US600Controller ctrl = new US600Controller(newGeoListUi);
        while (!activeInput) {
            ctrl.printHouseList(mGeoArea);

            while (!mScanner.hasNextDouble()) {
                System.out.println("Please enter a valid option");
                mScanner.next();
            }

            option = mScanner.nextDouble();
            int aux = option.intValue();
            if (aux >= 0 && aux < mGeoArea.getHouseList().getHouseList().size()) {
                mHouse = mGeoArea.getHouseList().getHouseList().get(aux);
                activeInput = true;
            } else {
                System.out.println("Please enter a valid option");
                return false;
            }
        }
        return true;
    }



    public void updateModel(){
        US600Controller ctrl = new US600Controller(mHouse);
        mCurrentHouseAreaTemperature = ctrl.getCurrentTemperatureInTheHouseArea(mHouse,mGeoArea);
    }

    public void displayState(){
        System.out.println("The current temperature in the house area is: " + mCurrentHouseAreaTemperature);
    }

}
