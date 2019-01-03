package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controller.US108Controller;

import pt.ipp.isep.dei.project.model.HouseList;

import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

/**
 * As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it.
 */

public class US108UI {
    private US108Controller ctrl108;
    private boolean active;

    public void US108UI() {
        this.active = false;
    }

    public void run(HouseList houseList) {
        this.ctrl108 = new US108Controller(houseList);

        while (this.active = true) {
            if (!displayHouseList()) {
                return;
            } else {
                getHouse();
                displayRoomList();
                getRoom();
                setInputRoom();
                return;
            }
        }

    }

    public boolean displayHouseList() {
        if (this.ctrl108.getHouseList().getHouseList().isEmpty()) {
            System.out.println(this.ctrl108.printHouseListNames());
            return false;
        } else {
            System.out.println(this.ctrl108.printHouseListNames());
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
        String houseName = scanner.next();
        if (this.ctrl108.matchHouseByName(houseName)) {
            System.out.println("You chose the House " + houseName);
        } else {
            System.out.println("This house does not exist in the list of houses.");
            return false;
        }
        return true;
    }


    private boolean displayRoomList() {
        if (this.ctrl108.getRoomList().getListOfRooms().isEmpty()) {
            System.out.println(this.ctrl108.printRoomListNames());
            return false;
        } else {
            System.out.println(this.ctrl108.printRoomListNames());
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
        String roomName = scanner.next();
        if (this.ctrl108.matchRoom(roomName)) {
            System.out.println("You chose the Room " + roomName);
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
        String roomName = input.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Please insert a valid number.");
        }
        int roomHouseFloor = input.nextInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's dimensions in square meters: ");
        while (!input.hasNextDouble()) {
            input.next();
            System.out.println("Please insert a valid number.");
        }
        double roomDimensions = input.nextDouble();
        this.ctrl108.setRoom(roomName,roomHouseFloor,roomDimensions);
        if (roomHouseFloor == 1) {
            System.out.println("Your room is now called " + roomName + ", it is located on the " + roomHouseFloor + "st floor and has " + roomDimensions + " square meters.");
        } else if (roomHouseFloor == 2) {
            System.out.println("Your room is now called " + roomName + ", it is located on the " + roomHouseFloor + "nd floor and has " + roomDimensions + " square meters.");
        } else if (roomHouseFloor == 3) {
            System.out.println("Your room is now called " + roomName + ", it is located on the " + roomHouseFloor + "rd floor and has " + roomDimensions + " square meters.");
        } else {
            System.out.println("Your room is now called " + roomName + ", it is located on the " + roomHouseFloor + "th floor and has " + roomDimensions + " square meters.");
        }
    }
}



