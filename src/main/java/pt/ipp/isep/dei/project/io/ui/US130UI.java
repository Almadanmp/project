package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US130Controller;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

/** As an Administrator, I want to create a house grid,
 * so that I can define the rooms that are attached to it and the contracted maximum power for that grid. **/


public class US130UI {

    private EnergyGrid mEnergyGrid;
    private boolean mActive;

    public US130UI(){mActive = false;}

    public void run(RoomList mainRoomList){
        this.mActive = true;
        while (this.mActive){
            getInput();
            if (!displayExistingRoomList(mainRoomList)) {
                return;
            }else {
                updateEnergyGridRoomList(mainRoomList);
                displayState();
            }
        }
    }

    private void getInput(){
        US130Controller ctrl = new US130Controller();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to create: ");
        String name = scanner.next();
        System.out.println("Set the maximum potency of this energy grid: ");
        double maxPower = scanner.nextDouble();
        ctrl.createEnergyGrid(name, maxPower);
        this.mEnergyGrid = ctrl.getEnergyGrid();
    }

    private boolean displayExistingRoomList(RoomList mainRoomList) {
        if (mainRoomList.getListOfRooms().isEmpty()) {
            System.out.println("The list of rooms is empty!");
            return false;
        } else {
            System.out.println(mainRoomList);
            return true;
        }
    }

    private void updateEnergyGridRoomList(RoomList mainRoomList){
        US130Controller ctrl = new US130Controller();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the name of the room which you want to attach: ");
        String roomToAttach = scanner.next();
        if (ctrl.addExistingRoomToEnergyGrid(mainRoomList,roomToAttach)){ //controller boolean
            System.out.println("The room was attached to the the energy grid!");
        }else {
            System.out.println("The room FAILED to attach to the the energy grid!");
        }
    }

    private void displayState(){
        System.out.println("This energy grid contains the following rooms: \n" + mEnergyGrid.getmListOfRooms().printRoomList() + "\n And its maximum potency is: " + mEnergyGrid.getmMaxPower());
    }
}
