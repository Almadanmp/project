package pt.ipp.isep.dei.project.model.areatype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.AreaTypeDTO;
import pt.ipp.isep.dei.project.dto.mappers.AreaTypeMapper;
import pt.ipp.isep.dei.project.model.repository.AreaTypeCrudRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that groups a number of Types of Geographical Areas.
 */
@Service
public class AreaTypeRepository {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AreaTypeRepository.class);

    @Autowired
    AreaTypeCrudRepo repository;

    List<AreaType> getAreaTypes() {
        return repository.findAll();
    }

    /**
     * This method creates a new Type of Geographic Area and adds it to a list.
     *
     * @param name String of the new Area Type that one wishes to create and addWithoutPersisting to a list.
     * @return true or false depending on if it adds the type to the list or not.
     */
    public AreaType create(String name) {
        return new AreaType(name);
    }

    /**
     * This method adds a previously stated Area Type to the repository.
     *
     * @param type Type of Geographic Area one wishes to saveSensor.
     * @return true or false if the saveSensor was successful
     */
    public boolean add(AreaType type) {
        Optional<AreaType> value = repository.findByName(type.getName());
        if (value.isPresent()) {
            return false;
        }
        repository.save(type);
        return true;
    }


    /**
     * This method builds a string of all the individual members of Area Type Repository
     *
     * @return builds a string of all the individual members of the Area Type Repository.
     */
    public String getAllAsString() {
        StringBuilder result = new StringBuilder("---------------\n");
        List<AreaType> typeAreas = getAreaTypes();
        if (isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (AreaType ta : typeAreas) {
            result.append("Name: ").append(ta.getName()).append(" \n");
        }
        result.append("---------------\n");
        return result.toString();
    }


    /**
     * This method checks if type area list is empty.*
     *
     * @return true if list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }


    /**
     * Method to get the TypeArea Repository Size
     *
     * @return repository size
     */
    public int size() {
        return repository.findAll().size();
    }


    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param id selected id
     * @return Type Area corresponding to the given id
     */
    public AreaType getById(String id) {
        Optional<AreaType> value = repository.findById(id);
        return value.orElse(null);
    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    public AreaType getAreaTypeByName(String name) {
        Optional<AreaType> value = repository.findByName(name);
        if (!(value.isPresent())) {
            String message = "The area Type " + name + " does not yet exist in the Data Base. Please create the Area" +
                    "Type first.";
            logger.debug(message);
            return null;
        } else {
            return value.get();
        }
    }

    //WEB CONTROLLER//

    public List<AreaTypeDTO> getAllTypesDTO() {
        List<AreaType> list = repository.findAll();
        List<AreaTypeDTO> finalList = new ArrayList<>();
        for (AreaType aT : list) {
            AreaTypeDTO aTDTO = AreaTypeMapper.objectToDTO(aT);
            finalList.add(aTDTO);
        }
        return finalList;
    }
}
