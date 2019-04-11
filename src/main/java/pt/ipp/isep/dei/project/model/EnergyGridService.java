package pt.ipp.isep.dei.project.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.EnergyGridRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Class that groups a number of energy Grids of a House.
 */
@Service
public class EnergyGridService {

    @Autowired
    EnergyGridRepository energyGridRepository;

    private List<EnergyGrid> energyGrids;

    /**
     * Empty constructor to use on UIs.
     */
    public EnergyGridService(EnergyGridRepository energyGridRepository) {
        this.energyGridRepository = energyGridRepository;
        this.energyGrids = new ArrayList<>();
    }

    public EnergyGridService() {
        energyGrids = new ArrayList<>();
    }

    public List<EnergyGrid> getAllGrids() {
        return energyGridRepository.findAll();
    }

    /**
     * Method adds an energy grid to the a energy grid list if the input grid isn't already contained in said list.
     *
     * @param energyGridToAdd the energy grid we want to addWithoutPersisting to the list.
     * @return returns true if the addition to the list is successful.
     */
    public boolean addGrid(EnergyGrid energyGridToAdd) {
        if (!(energyGrids.contains(energyGridToAdd))) {
            energyGrids.add(energyGridToAdd);
            return true;
        }
        return false;
    }

    public boolean addPersistenceGrid(EnergyGrid energyGrid) {
        EnergyGrid grid = energyGridRepository.findByName(energyGrid.getName());
        if (grid != null) {
            energyGridRepository.delete(grid);
        }
        energyGridRepository.save(energyGrid);
        return true;
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
            result.append(eg.getId()).append(") Designation: ").append(eg.getName()).append(" | ");
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
        return energyGridRepository.findAll().size();
    }

    public int gridsSize() {
        return energyGrids.size();
    }


    /**
     * This method receives an index as parameter and gets energy grid from energy grid list.
     *
     * @param index the index of the energy grid.
     * @return returns Energy grid that corresponds to index.
     */
    public EnergyGrid get(int index) {
        if (this.energyGrids.isEmpty()) {
            throw new IndexOutOfBoundsException("The energy grid list is empty.");
        }
        return this.energyGrids.get(index);
    }

    /**
     * Method to get a EnergyGrid from the Repository through a given id
     *
     * @param id selected id
     * @return Energy Grid corresponding to the given id
     */
    public EnergyGrid getById(long id) {
        Optional<EnergyGrid> value = energyGridRepository.findById(id);
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("ERROR: There is no Energy Grid with the selected ID.");
    }


    @Override
    public int hashCode() {
        return 1;
    }

}
