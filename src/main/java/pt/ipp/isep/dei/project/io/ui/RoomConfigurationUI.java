package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseMonitoringController;
import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.controller.US610Controller;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

public class RoomConfigurationUI {
    private Scanner mScanner;
    private RoomConfigurationController mRoomConfigurationController;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private String mNameRoom;

    public RoomConfigurationUI() {
        this.mScanner = new Scanner(System.in);
        this.mRoomConfigurationController = new RoomConfigurationController();

    }

    public void run(GeographicAreaList newGeoListUi, RoomList roomList) {

        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().size() == 0) {
            System.out.println("Invalid Geographic Area List - List Is Empty");
            return;
        }
    }
        private boolean getInputRoom(RoomList list){
            US610Controller ctrl = new US610Controller(list);
            System.out.print("Please insert the name of the Room you want to add sensor to: ");

            this.mNameRoom = mScanner.next();
            if (ctrl.doesListContainRoomByName(this.mNameRoom)) {
                System.out.println("You chose the Room " + this.mNameRoom);
            } else {
                System.out.println("This room does not exist in the list of rooms.");
                return false;
            }
            return true;
        }

}
