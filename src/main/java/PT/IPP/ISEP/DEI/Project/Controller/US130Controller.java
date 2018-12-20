package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.EnergyGrid;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

/**
 * As an Administrator, I want to create a energy grid,
 * so that I can define the rooms that are attached to it and the contracted maximum power for that grid.
 **/

public class US130Controller {

    private EnergyGrid mEnergyGrid;

    public US130Controller() {
    }

    public EnergyGrid getEnergyGrid() {
        return this.mEnergyGrid;
    }

    public boolean addExistingRoomToEnergyGrid(RoomList mainRoomList, String roomToAttach) {
        return mEnergyGrid.getmListOfRooms().addRoom(mainRoomList.getRoomByName(roomToAttach));
    }


    public void createEnergyGrid(String designation, double maxPower) {
        this.mEnergyGrid = new EnergyGrid(designation, maxPower);
    }
}
