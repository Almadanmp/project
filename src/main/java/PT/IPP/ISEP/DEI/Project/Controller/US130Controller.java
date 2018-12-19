package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.EnergyGrid;
import PT.IPP.ISEP.DEI.Project.Model.Room;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

/** As an Administrator, I want to create a house grid,
 * so that I can define the rooms that are attached to it and the contracted maximum power for that grid. **/

public class US130Controller {

    private EnergyGrid mEnergyGrid;
    private RoomList mRoomList;

    public US130Controller(EnergyGrid grid) {this.mEnergyGrid = grid;}

    public void setRoomList(RoomList list){this.mRoomList = list;}

    public RoomList getRoomList(){return this.mRoomList;}

    public boolean attachRoomToEnergyGrid(Room mRoomToAttach, double totalPower){
        if(mRoomList.checkIfListIsValid()){
            mRoomToAttach.setRoomTotalPower(totalPower);
            mRoomList.addRoom(mRoomToAttach);
            return true;
        }return false;
    }

    public void setGridMaximumPower(double gridMaxPower){
        this.mEnergyGrid.setmMaxPower(gridMaxPower);
    }

    public double getGridMaximumPower(){return this.mEnergyGrid.getTotalPower();}

}
