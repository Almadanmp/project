package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.*;

public class US145Controller {
    /**
     * US145Controller As an Administrator, I want to have a list of existing rooms attached to a house grid,
     * so that I can attach/detach rooms from it.
     * US147 As an Administrator, I want to attach a room to a house grid, so that the room’s
     * power and energy consumption is included in that grid.
     * US149 As an Administrator, I want to detach a room from a house grid, so that the room’s
     * power and energy consumption is not included in that grid. The room’s
     * characteristics are not changed.
     */


    private Room mRoom;
    private EnergyGrid mEnergyGrid;


    public US145Controller() {


        /*
         * Builder US253Controller(), with no parameters,
         * as it will only be used in UI to apply methods on given inputs
         */
    }
    public boolean seeIfHouseExistsInHouseList(String houseName,HouseList houseList){
        if (houseList.checkIfHouseListContainsHouseWithGivenDesignation(houseName)){
            return true;
        } else {
            return false;
        }
    }

    public RoomList getRoomListByHouseName (String houseName, HouseList houseList){
        return houseList.getHouseByDesignation(houseName).getmRoomList();
}
    public boolean seeIfRoomExistsInHouse(String roomName, RoomList roomList){
        if (roomList.matchRoom(roomName)){
            return true;
        } else {
            return false;
        }
    }
    public EnergyGridList getmEnergyGridListByHouseName (String houseName, HouseList houseList){
        return houseList.getHouseByDesignation(houseName).getmEGList();
    }

    public boolean seeIfEnergyGridExistsInEnergyGridList(String energyGridName, EnergyGridList energyGridList){
        if(energyGridList.seeIfContainsEnergyGrid(energyGridName)) {
            return true;
        }else {
            return false;
        }
    }

    public EnergyGrid getEnergyGrid (String energyGrid, EnergyGridList energyGridList){
        return energyGridList.matchEnergyGrid(energyGrid);
    }

    public Room matchRoomByName(String roomName, RoomList roomList){
        return roomList.getRoomByName(roomName);
    }

    public boolean addRoomToEnergyGrid(Room room, EnergyGrid energyGrid) {
        if (energyGrid.getmListOfRooms().addRoom(room)){
            return true;
        } else{
            return false;
        }
    }
}
