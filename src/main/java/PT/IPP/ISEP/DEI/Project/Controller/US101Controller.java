package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.Local;
import PT.IPP.ISEP.DEI.Project.Model.HouseList;

/** US101: As an Administrator, I want to configure the location of the house. **/

public class US101Controller {

    public boolean addHouseToHouseList(HouseList newHouseList, String houseName, String newHouseAddress, String newHouseZipCode, double latitude, double longitude) {
        if (newHouseList == null) {
            return false;
        }
        House houseToAdd = new House(houseName, newHouseAddress, new Local (latitude, longitude), newHouseZipCode);
        return newHouseList.addHouseToHouseList(houseToAdd);
    }
}
