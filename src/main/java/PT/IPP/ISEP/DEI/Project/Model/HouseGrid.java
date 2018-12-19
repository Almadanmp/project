package PT.IPP.ISEP.DEI.Project.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * House Class. Defines the HouseGrid
 */
public class HouseGrid {

    private String mHouseGridDesignation;
    private double mMaximumContractedPower;
    private List<Room> mRoomsInTheHouseGridList;

    public HouseGrid(String houseGridDesignation,double maxContractedPower){
        this.mHouseGridDesignation = houseGridDesignation;
        this.mMaximumContractedPower = maxContractedPower;
    }

    public void RoomList(Room roomToAdd) {
        mRoomsInTheHouseGridList = new ArrayList<>();
        mRoomsInTheHouseGridList.add(roomToAdd);
    }

    public void changeMaximumContractedPower(double maxContractedPower){
        this.mMaximumContractedPower = maxContractedPower;
    }

}

