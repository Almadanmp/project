package pt.ipp.isep.dei.project.model.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.HouseDTO;
import pt.ipp.isep.dei.project.dto.HouseWithoutGridsDTO;
import pt.ipp.isep.dei.project.dto.mappers.AddressMapper;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.model.repository.HouseCrudRepo;

import java.util.List;

@Service
public class HouseRepository {

    @Autowired
    HouseCrudRepo houseCrudRepo;

    /**
     * This method goes to the house crud repository finds the house
     * and returns the house ID.
     *
     * @return house ID
     **/
    public String getHouseId() {
        List<House> houseList = houseCrudRepo.findAll();
        House house = houseList.get(0);
        return house.getId();
    }

    /**
     * This method goes through the House database and gets all houses on the db
     *
     * @return a list with all the houses found on the db
     */
    public List<House> getHouses() {
        return houseCrudRepo.findAll();
    }

    /**
     * This method goes through the House database and gets the house of the application (there is only one).
     *
     * @return the House of the application as a DTO
     */
    public HouseDTO getApplicationHouse() {
        House house = houseCrudRepo.findAll().get(0);
        return HouseMapper.objectToDTO(house);
    }

    public HouseWithoutGridsDTO getHouseWithoutGridsDTO() {
        List<House> houseList = houseCrudRepo.findAll();
        House house = houseList.get(0);
        return HouseMapper.objectToWithoutGridsDTO(house);
    }

    public boolean updateHouseDTOWithoutGrids(HouseWithoutGridsDTO houseWithoutGridsDTO) {
        House house = HouseMapper.dtoWithoutGridsToObject(houseWithoutGridsDTO);
        return houseCrudRepo.save(house) != null;
    }

    /**
     * This method receives a house DTO and updates the House that is saved
     * in the database.
     **/
    public void updateHouse(HouseDTO houseDTO) {
        Address address = AddressMapper.dtoToObject(houseDTO.getAddress());
        House house = houseCrudRepo.findAll().get(0);
        house.setAddress(address);
        houseCrudRepo.save(house);
    }

}
