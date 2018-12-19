package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.Local;
import PT.IPP.ISEP.DEI.Project.Model.HouseList;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

/** US101: As an Administrator, I want to configure the location of the house. **/

public class US101Controller {



    public boolean addHouseToHouseList(HouseList newHouseList, String newHouseDesignation, String newHouseAddress, String newHouseZipCode, double latitude, double longitude) {
        if (newHouseList == null) {
            return false;
        }
        House houseToAdd = new House(newHouseDesignation, newHouseAddress, new Local (latitude, longitude), newHouseZipCode, new RoomList());
        return newHouseList.addHouseToHouseList(houseToAdd);
    }
}
