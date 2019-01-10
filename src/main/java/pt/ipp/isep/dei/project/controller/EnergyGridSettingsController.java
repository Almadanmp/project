package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

public class EnergyGridSettingsController {
    private EnergyGrid mEnergyGrid;
    private PowerSource mPowerSource;

    public EnergyGridSettingsController() { // Class controller doesn't need attributes to be initialized.
    }

    //SHARED METHODS THROUGH DIFFERENT UIS

    public List<Integer> matchGridIndexByString(String gridName, House house) {
        return house.getEGList().matchGridListElementsByString(gridName);
    }

    public String printEnergyGridByIndex(List<Integer> indexList, EnergyGridList egList) {
        return egList.printElementsByIndex(indexList);
    }

    public String printEnergyGrid(EnergyGrid grid) {
        return grid.printGrid();
    }

    public String printRooms(RoomList roomList) {
        return roomList.printRooms();
    }

    public String printRoomList(House house) {
        return house.getRoomList().printRoomList(house);
    }

    public String printGridList(House house) {
        return house.printGridList();
    }

    public List<Integer> getIndexRoomsByString(String input, House house) {
        return house.getRoomList().matchRoomIndexByString(input);
    }

    public String printRoomElementsByIndex(List<Integer> listOfIndexesOfRoom, House house) {
        return house.getRoomList().printElementsByIndex(listOfIndexesOfRoom);
    }

    public String printRoom(Room room) {
        return room.printRoom();
    }

    /*
     * USER STORY 130 - As an Administrator, I want to create a energy grid, so that I can define the rooms that are
     * attached to it and the contracted maximum power for that grid.
     */

    /**
     * This method directly adds the desired energy grid to the energy grid list from a selected house;
     */
    public boolean addEnergyGridToHouse(House programHouse) {
        return programHouse.getEGList().addEnergyGridToEnergyGridList(mEnergyGrid);
    }

    /**
     * This method creates an energy grid using a name and a max potency.
     */
    public void createEnergyGrid(String designation, double maxPower) {
        this.mEnergyGrid = new EnergyGrid(designation, maxPower);
    }

    /* USER STORY 135 - As an Administrator, I want to add a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid. */

    public void createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage) {
        this.mPowerSource = new PowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    public boolean addPowerSourceToEnergyGrid(EnergyGrid grid) {
        return grid.addPowerSource(mPowerSource);
    }

    /* USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    energy consumption is included in that grid.*/

    public boolean addRoomToTheGrid(EnergyGrid grid, Room room) {
        return grid.addRoomToAnEnergyGrid(room);
    }

    /*USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed. */

    public boolean removeRoomFromGrid(EnergyGrid grid, Room room) {
        return grid.removeRoom(room);
    }
}

