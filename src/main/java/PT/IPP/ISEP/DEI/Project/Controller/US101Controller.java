package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.Local;

/** US101: As an Administrator, I want to configure the location of the house. **/

public class US101Controller {

    public boolean addNewHouseToList(HouseList newHouseList, String newHouseAdress, String newHouseZipCode, double latitude, double longitude) {
        if (newHouseList == null) {
            return false;
        }
        House houseToAdd = new House(newHouseAdress, new Local (latitude, longitude), newHouseZipCode);
        return newHouseList.addHouseToHouseList(houseToAdd);
    }
}
