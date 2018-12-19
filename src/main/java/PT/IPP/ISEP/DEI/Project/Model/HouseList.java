package PT.IPP.ISEP.DEI.Project.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HouseList {

    private List<House> mHouseList;

    public HouseList() {
        mHouseList = new ArrayList<>();
    }

    public HouseList(House house) {
        mHouseList = new ArrayList<>();
        mHouseList.add(house);
    }

    public boolean addHouseToHouseList(House house) {
        if (!mHouseList.contains(house)) {
            mHouseList.add(house);
            return true;
        }
        return false;
    }

    public boolean checkIfHouseListIsValid() {
        {
            return !mHouseList.isEmpty();
        }
    }

    public List<House> getHouseList() {
        return mHouseList;
    }

    public boolean checkIfHouseListContainsHouseWithGivenDesignation(String houseDesignation) {
        for(House h : this.mHouseList) {
            String houseDesignationToTest = h.getHouseDesignation();
            if(houseDesignationToTest.equals(houseDesignation)) {
                return true;
            }
        }
        return false;
    }

    public boolean addRoomToHouseInHouseList(String houseDesignation, Room roomToAdd) {
        for(House h : this.mHouseList) {
            String houseDesignationToTest = h.getHouseDesignation();
            if(houseDesignationToTest.equals(houseDesignationToTest)) {
                if(h.addRoomToRoomList(roomToAdd)) {
                 return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof HouseList)) {
            return false;
        }
        HouseList list = (HouseList) testObject;
        return Arrays.equals(this.getHouseList().toArray(), list.getHouseList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
