package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US145Controller;
import pt.ipp.isep.dei.project.model.*;

import java.util.Scanner;

public class US145UI {
    /**
     * /**
     * US145Controller As an Administrator, I want to have a list of existing rooms attached to a house grid,
     * so that I can attach/detach rooms from it.
     * US147 As an Administrator, I want to attach a room to a house grid, so that the room’s
     * power and energy consumption is included in that grid.
     * US149 As an Administrator, I want to detach a room from a house grid, so that the room’s
     * power and energy consumption is not included in that grid. The room’s
     * characteristics are not changed.
     */

    private US145Controller ctrl145;
    private boolean mActive;
    private String mHouseName;
    private String mRoomName;
    private String mEnergyGridName;


    US145UI() {
        this.mActive = false;
    }

    public void run(HouseList houseList) {
        this.ctrl145 = new US145Controller(houseList);
        this.mActive = true;
        while (this.mActive) {
            getInputHouseName();
            updateHouseName();
            updateRoomList();
            getInputRoomName();
            updateRoomName();
            getInputEnergyGridName();
            updateEnergyGridList();
            updateEnergyGrid();
            updateFinalState();
            this.mActive = false;
        }
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

    private void updateRoomList() {
        this.ctrl145.getRoomListByHouseName(mHouseName);
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
