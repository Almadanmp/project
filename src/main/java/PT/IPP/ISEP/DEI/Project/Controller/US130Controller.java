package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.EnergyGrid;
import PT.IPP.ISEP.DEI.Project.Model.Room;
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

    public void setRoomList(RoomList list){this.mEnergyGrid.setmListOfRooms(list);}

    public RoomList getRoomList(){return this.mEnergyGrid.getmListOfRooms();}

    public boolean attachRoomToEnergyGrid(Room mRoomToAttach, double totalPower){
        if(mEnergyGrid.getmListOfRooms().checkIfListIsValid()){
            mEnergyGrid.getmListOfRooms().addRoom(mRoomToAttach);
            return true;
        }return false;
    }

    public void setGridMaximumPower(double gridMaxPower){
        this.mEnergyGrid.setmMaxPower(gridMaxPower);
    }

    public String printRoomListNames() {
        return mEnergyGrid.getmListOfRooms().printRoomList();
    }
}
