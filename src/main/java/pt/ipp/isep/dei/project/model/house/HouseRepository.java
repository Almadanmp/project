package pt.ipp.isep.dei.project.model.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.HouseDTO;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.repository.HouseCrudeRepo;

import java.util.List;

@Service
public class HouseRepository {

    @Autowired
    HouseCrudeRepo houseCrudeRepo;

    public HouseRepository(HouseCrudeRepo houseCrudeRepo){
        this.houseCrudeRepo = houseCrudeRepo;
    }

    /**
     * This method goes to the house crud repository finds the house
     * and returns the house ID.
     * @return house ID
     * **/
    public String getHouseId() {
        List<House> houseList = houseCrudeRepo.findAll();
        House house = houseList.get(0);
        return house.getId();
    }

    /**
     * This method goes through the House database and gets all houses on the db
     * @return a list with all the houses found on the db
     */
    //TODO unit test - Daniela
    public List<House> getHouses(){
        return houseCrudeRepo.findAll();
    }

    /**
     * This method goes through the House database and gets the house of the application (there is only one).
     * @return the House of the application as a DTO
     */
    //TODO unit test - Daniela
    public HouseDTO getApplicationHouse(){
        House house =  houseCrudeRepo.findAll().get(0);
        return HouseMapper.objectToDTO(house);
    }

}
