package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US145Controller;
import PT.IPP.ISEP.DEI.Project.Model.*;

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
    private String mHouseName;
    private HouseList mHouseList;
    private String mEnergyGridName;
    private boolean mActive;
    private String mRoomName;


    public US145UI() {
        this.mActive = false;
    }

    public void run(HouseList houseList) {
        this.mActive = true;
        this.mHouseList = houseList;
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
        }
    }

    private void getInputHouseName() {
        System.out.println("Please insert the House Name You Want To Add The Room To: ");
        Scanner input = new Scanner(System.in);
        this.mHouseName = input.nextLine();
    }

    private void updateHouseName() {
        US145Controller ctrl145 = new US145Controller();
        if (ctrl145.seeIfHouseExistsInHouseList(this.mHouseName,this.mHouseList)) {
            System.out.println("The House you have inserted is on the List.");
        } else {
            System.out.println("The House you have inserted is not on the List.");
        }
    }

    private void updateRoomList() {
        US145Controller ctrl145 = new US145Controller();
        ctrl145.setRoomListByHouseName(this.mHouseName, this.mHouseList);
    }

    private void getInputRoomName() {
        System.out.println("Please insert The Room You Want to Add To The Energy Grid: ");
        Scanner input = new Scanner(System.in);
        this.mRoomName = input.nextLine();
    }

    private void updateRoomName() {
        US145Controller ctrl145 = new US145Controller();
        if (ctrl145.seeIfRoomExistsInHouse(this.mRoomName)) {
            System.out.println("The Room you have inserted is on the List.");
        } else {
            System.out.println("The Room you have inserted is not on the List.");
        }
    }

    private void getInputEnergyGridName() {
        System.out.println("Please insert The Name of The Energy Grid you want to add the Room to: ");
        Scanner input = new Scanner(System.in);
        this.mEnergyGridName = input.nextLine();
    }

    private void updateEnergyGridList() {
        US145Controller ctrl145 = new US145Controller();
        ctrl145.setmEnergyGridListByHouseName(this.mHouseName, this.mHouseList);
    }

    private void updateEnergyGrid() {
        US145Controller ctrl145 = new US145Controller();
        if (ctrl145.seeIfEnergyGridExistsInEnergyGridList(this.mEnergyGridName)) {
            System.out.println("The Energy Grid you have inserted exists on the house.");
        } else {
            System.out.println("The Energy Grid you have inserted does not exist.");
        }
    }
    private void updateFinalState(){
        US145Controller ctrl145 = new US145Controller();
        ctrl145.matchRoomByName(this.mRoomName);
        if(ctrl145.addRoomToEnergyGrid()){
            System.out.println("The room was successfully added to the Energy Grid.");
        }else {
            System.out.println("The room already exists in the Energy Grid.");
        }
    }
}
