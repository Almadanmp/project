package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US145Controller;
import pt.ipp.isep.dei.project.model.*;

import java.util.Scanner;

public class US145UI {
    /**
     * /**
     * US145 As an Administrator, I want to have a list of existing rooms attached to a house grid,
     * so that I can attach/detach rooms from it.
     * US147 As an Administrator, I want to attach a room to a house grid, so that the room’s
     * power and energy consumption is included in that grid.
     * US149 As an Administrator, I want to detach a room from a house grid, so that the room’s
     * power and energy consumption is not included in that grid. The room’s
     * characteristics are not changed.
     */

    private US145Controller ctrl145;
    private boolean mActive;
    private String mGeographicAreaName;
    private String mHouseName;
    private String mRoomName;
    private String mEnergyGridName;


    US145UI() {
        this.mActive = false;
    }

    public void run(GeographicAreaList geographicAreaList) {
        this.ctrl145 = new US145Controller(geographicAreaList);
        this.mActive = true;
        while (this.mActive) {
            getInputGeographicAreaName();
            updateGeographicAreaName();
            updateGeographicArea();
            getInputHouseName();
            updateHouseName();
            updateHouse();
            updateRoomList();
            getInputRoomName();
            updateRoomName();
            updateRoom();
            getInputEnergyGridName();
            updateEnergyGridList();
            updateEnergyGrid();
            updateFinalState();
            this.mActive = false;
        }
    }

    private void getInputGeographicAreaName() {
        System.out.println("Please insert the Geographic Area Name You Want To Add The Room To: ");
        Scanner input = new Scanner(System.in);
        String geographicAreaName = input.nextLine();
        mGeographicAreaName = geographicAreaName;
    }

    private void updateGeographicAreaName(){
        if (this.ctrl145.seeIfGeographicAreaExistsInGeographicAreaList(mGeographicAreaName)) {
            System.out.println("The Geographic Area you have inserted is on the List.");
        } else {
            System.out.println("The Geographic Area you have inserted is not on the List.");
        }
    }

    private void updateGeographicArea(){
        this.ctrl145.getGeographicArea(this.mGeographicAreaName);
    }

    private void getInputHouseName() {
        System.out.println("Please insert the House Name You Want To Add The Room To: ");
        Scanner input = new Scanner(System.in);
        String houseName = input.nextLine();
        mHouseName = houseName;
    }

    private void updateHouseName() {
        if (this.ctrl145.seeIfHouseExistsInHouseList(mHouseName)) {
            System.out.println("The House you have inserted is on the List.");
        } else {
            System.out.println("The House you have inserted is not on the List.");
        }
    }

    private void updateHouse() {
        this.ctrl145.getHouseByHouseName(mHouseName);
    }

    private void updateRoomList() {
        this.ctrl145.getRoomListByHouseName();
    }

    private void getInputRoomName() {
        System.out.println("Please insert The Room You Want to Add To The Energy Grid: ");
        Scanner input = new Scanner(System.in);
        String roomName = input.nextLine();
        mRoomName = roomName;
    }

    private void updateRoomName() {
        if (this.ctrl145.seeIfRoomExistsInHouse(mRoomName)) {
            System.out.println("The Room you have inserted is on the List.");
        } else {
            System.out.println("The Room you have inserted is not on the List.");
        }
    }

    private void updateRoom() {
        this.ctrl145.getRoomByRoomName(mRoomName);
    }

    private void getInputEnergyGridName() {
        System.out.println("Please insert The Name of The Energy Grid you want to add the Room to: ");
        Scanner input = new Scanner(System.in);
        String energyGridName = input.nextLine();
        mEnergyGridName = energyGridName;

    }

    private void updateEnergyGridList() {
        this.ctrl145.getmEnergyGridListByHouseName();
    }

    private void updateEnergyGrid() {
        if (this.ctrl145.seeIfEnergyGridExistsInEnergyGridList(mEnergyGridName)) {
            System.out.println("The Energy Grid you have inserted exists on the house.");
            this.ctrl145.getEnergyGrid(mEnergyGridName);
        } else {
            System.out.println("The Energy Grid you have inserted does not exist.");
        }
    }

    private void updateFinalState() {
        if (this.ctrl145.addRoomToEnergyGrid()) {
            System.out.println("The room was successfully added to the Energy Grid.");
        } else {
            System.out.println("The room already exists in the Energy Grid.");
        }
    }
}
