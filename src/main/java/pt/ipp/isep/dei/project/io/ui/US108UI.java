package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controller.US108Controller;

import pt.ipp.isep.dei.project.model.HouseList;

import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

/**
 * As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it.
 */

public class US108UI {
    private HouseList mHouseList;
    private RoomList mRoomList;
    private String mHouseDesignation;
    private String mRoomDesignation;
    private String mNewRoomDesignation;
    private int mRoomHouseFloor;
    private double mRoomDimensions;
    private boolean active;

    public void US108UI() {
        this.active = false;
    }

    public void run(HouseList newHouseList, RoomList newRoomList) {
        this.active = true;
        this.mHouseList = newHouseList;
        this.mRoomList = newRoomList;

        while (this.active) {
            if (!displayHouseList()) {
                return;
            } else {
                getHouse();
                displayRoomList(newRoomList);
                getRoom();
                setInputRoom();
                updateState(newRoomList);
                displayState();
                return;
            }
        }

    }


    public boolean displayHouseList() {
        US108Controller ctrl = new US108Controller(mHouseList);
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
        US108Controller ctrl = new US108Controller(mHouseList);
        if (ctrl.matchHouse(mHouseDesignation)) {
            System.out.println("You chose the House " + this.mHouseDesignation);
        } else {
            System.out.println("This house does not exist in the list of houses.");
            return false;
        }
        return true;
    }


    private boolean displayRoomList(RoomList roomList) {
        US108Controller ctrl = new US108Controller(roomList);
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
        US108Controller ctrl = new US108Controller(mRoomList);
        if (ctrl.matchRoom(mRoomDesignation)) {
            System.out.println("You chose the Room " + this.mRoomDesignation);
        } else {
            System.out.println("This room does not exist in the list of rooms.");
            return false;
        }
        return true;
    }


    private void setInputRoom() {
        Scanner input = new Scanner(System.in);

        ////GET ROOM DESIGNATION
        System.out.println("Please insert the room name: ");
        this.mNewRoomDesignation = input.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Please insert a valid number.");
        }
        this.mRoomHouseFloor = input.nextInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's dimensions in square meters: ");
        while (!input.hasNextDouble()) {
            input.next();
            System.out.println("Please insert a valid number.");
        }
        this.mRoomDimensions = input.nextDouble();
    }

    private void updateState(RoomList newRoomList) {
        US108Controller ctrl = new US108Controller(newRoomList);
        ctrl.setRoom(this.mNewRoomDesignation, this.mRoomHouseFloor, this.mRoomDimensions);
    }


    private void displayState() {
        if (mRoomHouseFloor == 1) {
            System.out.println("Your room is now called " + mNewRoomDesignation + ", it is located on the " + mRoomHouseFloor + "st floor and has " + mRoomDimensions + " square meters.");
        } else if (mRoomHouseFloor == 2) {
            System.out.println("Your room is now called " + mNewRoomDesignation + ", it is located on the " + mRoomHouseFloor + "nd floor and has " + mRoomDimensions + " square meters.");
        } else if (mRoomHouseFloor == 3) {
            System.out.println("Your room is now called " + mNewRoomDesignation + ", it is located on the " + mRoomHouseFloor + "rd floor and has " + mRoomDimensions + " square meters.");
        } else {
            System.out.println("Your room is now called " + mNewRoomDesignation + ", it is located on the " + mRoomHouseFloor + "th floor and has " + mRoomDimensions + " square meters.");
        }
    }

}



