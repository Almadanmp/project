package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US105Controller;
import PT.IPP.ISEP.DEI.Project.Model.HouseList;

import java.util.Scanner;

/** As an Administrator, I want to add a new room to the house, in order to configure
it (name, house floor and dimensions).
 **/

public class US105UI {
    private boolean active;
    private HouseList mHouseList;
    private String mHouseDesignation;
    private String mRoomName;
    private int mRoomHouseFloor;
    private double mRoomDimensions;

    public void run(HouseList houseList) {
        this.active = true;
        this.mHouseList = houseList;
        while(updateInputHouseAndDisplayState()) {
            getInputRoom();
            updateInputRoom();
            displayStateRoom();
            getInputHouse();
            updateInputHouseAndDisplayState();
            updateRoomAndDisplayState();
        }
        this.active = false;
    }

    public void getInputRoom() {
        Scanner input = new Scanner(System.in);

        //GET ROOM DESIGNATION
        System.out.println("Please insert the room name: ");
        this.mRoomName = input.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        while(!input.hasNextInt()) {
            input.next();
            System.out.println("Please insert a valid number.");
        }
        this.mRoomHouseFloor = input.nextInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's dimensions in square meters: ");
        while(!input.hasNextDouble()) {
            input.next();
            System.out.println("Please insert a valid number.");
        }
        this.mRoomDimensions = input.nextDouble();
    }

    public void updateInputRoom() {
        US105Controller ctrl105 = new US105Controller();
        ctrl105.createNewRoom(mRoomName, mRoomHouseFloor, mRoomDimensions);
    }

    public void displayStateRoom() {
        //SHOW ROOM ENTERED BY USER
        if(mRoomHouseFloor==1) {
            System.out.println("Your new room is called " + mRoomName + ", it is located on the " + mRoomHouseFloor + "st floor and has " + mRoomDimensions + " square meters.");
        }
        else if(mRoomHouseFloor==2) {
            System.out.println("Your new room is called " + mRoomName + ", it is located on the " + mRoomHouseFloor + "nd floor and has " + mRoomDimensions + " square meters.");
        }
        else if(mRoomHouseFloor==3) {
            System.out.println("Your new a room is called " + mRoomName + ", it is located on the " + mRoomHouseFloor + "rd floor and has " + mRoomDimensions + " square meters.");
        }
        else {
            System.out.println("Your new a room is called " + mRoomName + ", it is located on the " + mRoomHouseFloor + "th floor and has " + mRoomDimensions + " square meters.");
        }
    }

    public void getInputHouse() {
        //GET HOME DESIGNATION
        Scanner input = new Scanner(System.in);
        System.out.println("Please insert the house name you want to add your room to: ");
        this.mHouseDesignation = input.nextLine();

        //SHOW NAME ENTERED BY USER
        System.out.println("You entered the house name " + this.mHouseDesignation);
    }

    public boolean updateInputHouseAndDisplayState() {
        US105Controller ctrl105 = new US105Controller();
        if(ctrl105.checkIfHouseExistsInList(mHouseDesignation, mHouseList)) {
            System.out.println("The house you entered is currently on the list.");
            return true;
        }
        else {
            System.out.println("The house you entered is not on the list. Try again!");
            return false;
        }
    }

    public void updateRoomAndDisplayState() {
        US105Controller ctrl105 = new US105Controller();
        if(ctrl105.addRoomToHouse(mHouseDesignation, mHouseList)){
            System.out.println("The room " + mRoomName + " has been added to house " + mHouseDesignation);
        }
        else {
            System.out.println("The room you entered already exists in house " + mHouseDesignation);
        }
    }




}
