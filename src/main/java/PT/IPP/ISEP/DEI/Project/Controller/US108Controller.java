package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.*;

/**
 * As an Administrator, I want to have a list of existing rooms, so that I can choose one
 * to edit it.
 **/

public class US108Controller {

    private HouseList mHouseList;
    private RoomList mRoomList;
    private Room mRoom;
    private House mHouse;

    public US108Controller(HouseList houseList) {
        this.mHouseList = houseList;
    }
    public US108Controller(RoomList roomList) {
        this.mRoomList = roomList;
    }
    public HouseList getHouseList() {
        return this.mHouseList;
    }

    public RoomList getRoomList() {
        return this.mRoomList;
    }

    public String printHouseListNames() {
        return mHouseList.printHouseList();
    }

    public String printRoomListNames() {
        return mRoomList.printRoomList();
    }

    public boolean matchHouse(String houseDesignation) {
        return mHouseList.matchHouse(houseDesignation);
    }

    public boolean matchRoom(String roomDesignation) {
        return mRoomList.matchRoom(roomDesignation);
    }

    public void setRoom(String roomDesignation, int roomHouseFloor, double roomDimensions) {
        this.mRoom = new Room(roomDesignation, roomHouseFloor ,roomDimensions);
    }

}

