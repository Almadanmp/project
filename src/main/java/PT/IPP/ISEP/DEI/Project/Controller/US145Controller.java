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
    private EnergyGrid mEnergyGrid;
    private RoomList mRoomList;
    private EnergyGridList mEnergyGridList;
    private Room mRoom;


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

    public void setRoomListByHouseName (String houseName, HouseList houseList){
        this.mRoomList = houseList.getHouseByDesignation(houseName).getmRoomList();
}
    public boolean seeIfRoomExistsInHouse(String roomName){
        if (this.mRoomList.matchRoom(roomName)){
            return true;
        } else {
            return false;
        }
    }
    public void setmEnergyGridListByHouseName (String houseName, HouseList houseList){
        this.mEnergyGridList = houseList.getHouseByDesignation(houseName).getmEGList();
    }

    public boolean seeIfEnergyGridExistsInEnergyGridList(String energyGridName){
        if(this.mEnergyGridList.seeIfContainsEnergyGrid(energyGridName)) {
            return true;
        }else {
            return false;
        }
    }



    public void matchRoomByName(String roomName){
        this.mRoom = mRoomList.getRoomByName(roomName);
    }

    public boolean addRoomToEnergyGrid() {
        if (this.mEnergyGrid.getmListOfRooms().addRoom(this.mRoom)){
            return true;
        } else{
            return false;
        }
    }
}
