package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US108Controller;
import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.HouseList;
import PT.IPP.ISEP.DEI.Project.Model.Room;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

import java.util.Scanner;

/**
 * As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it.
 */

public class US108UI {
    private HouseList mHouseList;
    private RoomList mRoomList;
    private String mHouseDesignation;
    private String mRoomDesignation;
    private int mHouseRoomFloor;
    private double mRoomDimensions;
    private boolean active;

    public void US108UI() {
        this.active = false;
    }

    public void run(RoomList newRoomList) {
        this.active = true;
        this.mRoomList = newRoomList;

        while (this.active) {
            if (!displayHouseList()) {
                return;
            } else {
                getHouse();
                displayRoomList();
                getRoom();
                setInputRoom();
                updateState();
                displayState();
            }
        }

    }


    public boolean displayHouseList() {
        US108Controller ctrl = new US108Controller();
        if (ctrl.getHouseList().getHouseList().isEmpty()) {
            System.out.println(ctrl.printHouseListNames());
            return false;
        } else {
            System.out.println(ctrl.printHouseListNames());
            return true;
        }
    }

    private boolean getHouse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the house where the room is in:");
        while (!scanner.hasNext("[\\p{L}\\s]+")) {
            System.out.println("Please,try again:");
            scanner.next();
        }
        this.mHouseDesignation = scanner.next();
        US108Controller ctrl = new US108Controller();
        if (ctrl.matchHouse(mHouseDesignation)) {
            System.out.println("You chose the Room " + this.mHouseDesignation);
        } else {
            System.out.println("This room does not exist in the list of rooms.");
            return false;
        }
        return true;
    }


    private boolean displayRoomList() {
        US108Controller ctrl = new US108Controller();
        if (ctrl.getRoomList().getListOfRooms().isEmpty()) {
            System.out.println(ctrl.printRoomListNames());
            return false;
        } else {
            System.out.println(ctrl.printRoomListNames());
            return true;
        }
    }

    private boolean getRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the name of the room you want to reconfigure:");
        while (!scanner.hasNext("[\\p{L}\\s]+")) {
            System.out.println("Please,try again:");
            scanner.next();
        }
        this.mRoomDesignation = scanner.next();
        US108Controller ctrl = new US108Controller();
        if (ctrl.matchRoom(mRoomDesignation)) {
            System.out.println("You chose the Room " + this.mRoomDesignation);
        } else {
            System.out.println("This room does not exist in the list of rooms.");
            return false;
        }
        return true;
    }


    private void setInputRoom() {

    }

    private void updateState() {

    }

    private void displayState() {

    }

}

