package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.HouseList;
import PT.IPP.ISEP.DEI.Project.Model.Room;

/** US105: As an Administrator, I want to add a new room to the house, in order to configure
it (name, house floor and dimensions). **/

public class US105Controller {

    private Room mRoom;
    private HouseList mHouseList;

    public US105Controller() {}

    public void createNewRoom(String roomDesignation, int roomHouseFloor, double roomDimensions) {
        this.mRoom = new Room(roomDesignation, roomHouseFloor ,roomDimensions);
    }

    public Room getRoom() {
        return this.mRoom;
    }

    public boolean checkIfHouseExistsInList(String houseDesignation, HouseList houseList) {
        mHouseList = houseList;
        if(mHouseList.checkIfHouseListContainsHouseWithGivenDesignation(houseDesignation)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addRoomToHouse(String houseDesignation, HouseList houseList, Room roomGiven) {
        mHouseList = houseList;
        if(mHouseList.addRoomToHouseInHouseList(houseDesignation, roomGiven)) {
            return true;
        }
        else {
            return false;
        }
    }

}
