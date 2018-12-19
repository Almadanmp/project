package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.EnergyGrid;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

/** As an Administrator, I want to create a energy grid,
 * so that I can define the rooms that are attached to it and the contracted maximum power for that grid. **/

public class US130Controller {

    private EnergyGrid mEnergyGrid;
    private RoomList mEnergyGridRoomList;

    public US130Controller(){}

    public EnergyGrid createEnergyGrid(String designation, double maxPower){
        return this.mEnergyGrid = new EnergyGrid(designation, maxPower);
    }
    public void createEnergyGridRoomList(){mEnergyGridRoomList = new RoomList();}

    public RoomList getRoomList(){return this.mEnergyGrid.getmListOfRooms();}

    public boolean attachRoomToEnergyGrid(String mRoomToAttach, RoomList mainList){
        if(mEnergyGrid.getmListOfRooms().checkIfListIsValid()){
            mEnergyGridRoomList.addRoom(mainList.getRoomByName(mRoomToAttach));
            return true;
        }return false;
    }

    public String printRoomListNames() {
        return mEnergyGrid.getmListOfRooms().printRoomList();
    }
}
