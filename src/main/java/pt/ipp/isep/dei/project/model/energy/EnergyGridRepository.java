package pt.ipp.isep.dei.project.model.energy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.EnergyGridMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.repository.EnergyGridCrudeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Class that groups a number of energy Grids of a House.
 */
@Service
public class EnergyGridRepository {

    @Autowired
    EnergyGridCrudeRepo energyGridCrudeRepository;

    public List<EnergyGrid> getAllGrids() {
        List<EnergyGrid> grids = energyGridCrudeRepository.findAll();
        if (grids != null) {
            return grids;
        }
        return new ArrayList<>();
    }

    public EnergyGrid addGrid(EnergyGrid energyGrid) {
        return energyGridCrudeRepository.save(energyGrid);
    }

    public void createEnergyGridDTO(EnergyGridDTO energyGridDTO) {
        EnergyGrid energyGrid = EnergyGridMapper.dtoToObjectEmptyLists(energyGridDTO);
        energyGridCrudeRepository.save(energyGrid);
    }

    /**
     * Method for US 147 - As an Administrator, I want to attach a room to a house grid, so that the room’s power and
     * energy consumption is included in that grid.
     * This method adds a room to the grid and persists in the database.
     *
     * @param roomDTO  is the roomDTO we want to add to the grid
     * @param gridName is the name of the grid we want to add the room to
     * @return true if the room was successfully added, false otherwise
     */
    public boolean attachRoomToGrid(RoomDTO roomDTO, String gridName) {
        EnergyGrid energyGrid = getById(gridName);
        Room room = RoomMapper.dtoToObject(roomDTO);
        if (energyGrid.addRoom(room)) {
            energyGridCrudeRepository.save(energyGrid);
            return true;
        }
        return false;
    }


    /**
     * This method creates a new EnergyGrid using its constructor.
     *
     * @param designation - designation of the to be created EnergyGrid.
     * @param maxPower    - maximum power of the to be created EnergyGrid.
     * @return a new EnergyGrid or an existing one if the designation is the same.
     */
    public EnergyGrid createEnergyGrid(String designation, double maxPower, String houseID) {
        return new EnergyGrid(designation, maxPower, houseID);
    }

    /**
     * Method that builds a string of every grid contained in the grid list, using their name and maximum contracted power,
     * and assigning an index to each one of them.
     *
     * @return a string that is the list of all grids present in the grid list.
     */
    public String buildString() {
        String mStringEnhancer = "---------------\n";
        StringBuilder result = new StringBuilder(mStringEnhancer);
        if (isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (EnergyGrid eg : getAllGrids()) {
            result.append("Designation: ").append(eg.getName()).append(" | ");
            result.append("Max Power: ").append(eg.getMaxContractedPower()).append("\n");
        }
        result.append(mStringEnhancer);
        return result.toString();
    }

    /**
     * This method checks if the energy grid list is empty.
     *
     * @return returns true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return getAllGrids().isEmpty();
    }


    /**
     * Method to get the EnergyGrid Repository Size
     *
     * @return repository size
     */
    public int size() {
        return energyGridCrudeRepository.findAll().size();
    }

    /**
     * Method to get a EnergyGrid from the Repository through a given id
     *
     * @param id selected id
     * @return Energy Grid corresponding to the given id
     */
    public EnergyGrid getById(String id) {
        Optional<EnergyGrid> value = energyGridCrudeRepository.findById(id);
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("ERROR: There is no Energy Grid with the selected ID.");
    }

    //POWER SOURCE METHODS

    /**
     * This method creates a power source
     *
     * @param name             the name of the power source to be created
     * @param maxEnergyStorage the maximum storable energy for the power source
     * @param maxPowerOutput   the maximum power for the power source
     * @return creates a new power source.
     **/
    public PowerSource createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage) {
        return new PowerSource(name, maxPowerOutput, maxEnergyStorage);
    }
}
