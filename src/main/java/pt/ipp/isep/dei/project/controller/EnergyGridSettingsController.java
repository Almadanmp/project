package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

public class EnergyGridSettingsController {
    private EnergyGrid mEnergyGrid;
    private PowerSource mPowerSource;

    public EnergyGridSettingsController() { // Class controller doesn't need attributes to be initialized.
    }

    //SHARED METHODS THROUGH DIFFERENT UIS

    /**
     * @param gridName is the name of the grid we're going to check for.
     * @param house    is the house we're going to check if the grid exists in.
     * @return gives a list of integers where all members are indexes of the House's EnergyGridList that contain a Grid
     * of wanted name.
     */

    public List<Integer> matchGridIndexByString(String gridName, House house) {
        return house.getEGList().matchGridListElementsByString(gridName);
    }

    /**
     * @param indexList is a list of integers containing indexes.
     * @param egList    is the list of energy grids we're going to print from.
     * @return from the given list of Energy Grids the grids in the indexes provided by the list, we build a string.
     */

    public String printEnergyGridByIndex(List<Integer> indexList, EnergyGridList egList) {
        return egList.printElementsByIndex(indexList);
    }

    /**
     * @param grid is the energy grid we're going to print.
     * @return we build a string from the provided grid.
     */

    public String printEnergyGrid(EnergyGrid grid) {
        return grid.printGrid();
    }

    /**
     * @param roomList is the list of Rooms we want to print.
     * @return builds a string of all the individual rooms contained in the list.
     */

    public String printRooms(RoomList roomList) {
        return roomList.printRooms();
    }

    /**
     * @param house is the house of which we're going to print the roomList.
     * @return builds a string of all the individual rooms contained in the house's roomList.
     */

    public String printHouseRoomList(House house) {
        return house.getRoomList().printRoomList(house);
    }

    /**
     * @param house is the house of which we're going to print the GridList.
     * @return builds a string of all the individual EnergyGrids contained in the house's EnergyGridList.
     */

    public String printGridList(House house) {
        return house.printGridList();
    }

    /**
     * @param input is the String, meaning the name of the room, which we want to look for.
     * @param house is the house where we're going to look for rooms with that name.
     * @return is a list of integers containing the indexes in the House's RoomList of Rooms with names that match
     * the input.
     */

    public List<Integer> getIndexHouseRoomsByString(String input, House house) {
        return house.getRoomList().matchRoomIndexByString(input);
    }

    /**
     * @param listOfIndexesOfRoom is a list of integers containing the indexes in the House's RoomList of Rooms with names that match.
     * @param house               is the house we're going to print Rooms from.
     * @return builds a string of the rooms in the House's RoomList that are contained in the indexes provided by the first parameter.
     */

    public String printHouseRoomsByIndex(List<Integer> listOfIndexesOfRoom, House house) {
        return house.getRoomList().printElementsByIndex(listOfIndexesOfRoom);
    }

    /**
     * @param room is the room we're going to print.
     * @return builds a string of the room.
     */

    public String printRoom(Room room) {
        return room.printRoom();
    }

    /**
     * @param energyGrid is the energy grid from which we're going to print rooms.
     * @return builds a string of all the rooms contained in the EnergyGrid's RoomList.
     */

    public String printGridRooms(EnergyGrid energyGrid) {
        String mStringSpacer = "---------------\n";
        StringBuilder result = new StringBuilder(mStringSpacer);
        if (energyGrid.getListOfRooms().getRoomList().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < energyGrid.getListOfRooms().getRoomList().size(); i++) {
            Room aux = energyGrid.getListOfRooms().getRoomList().get(i);
            result.append(i).append(") Designation: ").append(aux.getRoomName()).append(" | ");
            result.append("House Floor: ").append(aux.getHouseFloor()).append(" | \n");
        }
        result.append(mStringSpacer);
        return result.toString();
    }

    /*
     * USER STORY 130 - As an Administrator, I want to create a energy grid, so that I can define the rooms that are
     * attached to it and the contracted maximum power for that grid.
     */

    /**
     * This method directly adds the desired energy grid to the energy grid list from a selected house;
     */
    public boolean addEnergyGridToHouse(House programHouse) {
        if (mEnergyGrid != null) {
            programHouse.getEGList().addEnergyGridToEnergyGridList(mEnergyGrid);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param designation is the name we're going to give to the new EnergyGrid.
     * @param maxPower    is the new grid's maxPower.
     */
    public void createEnergyGrid(String designation, double maxPower) {
        this.mEnergyGrid = new EnergyGrid(designation, maxPower);
    }

    /* USER STORY 135 - As an Administrator, I want to add a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid. */

    /**
     * @param name             is the name we're going to give to the new power source.
     * @param maxPowerOutput   is the new power source's maximum power output.
     * @param maxEnergyStorage is the new power source's maximum capacity.
     */

    public void createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage) {
        this.mPowerSource = new PowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    /**
     * @param grid is the grid we're going to add the new powerSource to.
     * @return is true if the power source is added successfully, false if it isn't.
     */

    public boolean addPowerSourceToGrid(EnergyGrid grid) {
        if (mPowerSource != null) {
            grid.addPowerSource(mPowerSource);
            return true;

        } else {
            return false;
        }
    }
    /* USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    energy consumption is included in that grid. MIGUEL ORTIGAO*/

    /**
     * @param grid is the grid we're going to add a room to.
     * @param room is the room we're going to add to a grid.
     * @return is true if the room is added to the grid successfully, false if it isn't.
     */

    public boolean addRoomToGrid(EnergyGrid grid, Room room) {
        return grid.addRoomToAnEnergyGrid(room);
    }

    /*USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed. */

    /**
     * @param grid is the grid we're going to remove a room from.
     * @param room is the room we're going to remove from the grid.
     * @return is true if the room is removed from the grid successfully, false if it isn't.
     */

    public boolean removeRoomFromGrid(EnergyGrid grid, Room room) {
        return grid.removeRoom(room);
    }

    public double getTotalPowerFromGrid(EnergyGrid grid) {
        return grid.getNominalPower();
    }

    /*USER STORY 160 - As a Power User (or Administrator),
    I want to get a list of all devices in a grid, grouped by device type.
    It must include device location
    DANIEL OLIVEIRA*/


    public String printListOfDevicesByType(EnergyGrid energyGrid) {
        if (energyGrid.getListOfRooms().getRoomList().isEmpty()) {
            return "This energy grid has no rooms attached\n";
        }
        if (energyGrid.getDeviceListFromAllRooms().getDeviceList().isEmpty()) {
            return "This energy grid has no devices on it\n";
        }return energyGrid.printDeviceListByType(energyGrid);
    }
}

