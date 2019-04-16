package pt.ipp.isep.dei.project.model;

import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Class that groups a number of Geographical Areas.
 */
@Service
public class GeographicAreaService {

    private GeographicAreaRepository geographicAreaRepository;

    private AreaTypeRepository areaTypeRepository;

    public GeographicAreaService(GeographicAreaRepository geographicAreaRepository, AreaTypeRepository areaTypeRepository) {
        this.geographicAreaRepository = geographicAreaRepository;
        this.areaTypeRepository = areaTypeRepository;
    }

    /**
     * Method to return a list with all the Geographical Areas contained on the geographicAreaRepository
     *
     * @return a GeographicAreaList with all the Geographical Areas saved in the repository.
     */
    public List<GeographicArea> getAll() {
        return this.geographicAreaRepository.findAll();
    }

    /**
     * Method that receives a geographic area as a parameter and adds that
     * GA to the list in case it is not contained in that list already.
     *
     * @param geographicAreaToAdd geographic area to be added
     * @return returns true in case the geographic area is added and false if not
     **/
    public boolean addAndPersistGA(GeographicArea geographicAreaToAdd) {
        List<GeographicArea> geographicAreas = getAll();
        if (!(geographicAreas.contains(geographicAreaToAdd))) {
            geographicAreas.add(geographicAreaToAdd);
            geographicAreaRepository.save(geographicAreaToAdd);
            return true;
        }
        return false;
    }

    /**
     * Method to print a Whole Geographic Area List.
     * It will print the attributes needed to check if a GA is different from another GA
     * (name, type of GA and Localization)
     *
     * @return a string with the names of the geographic areas
     */
    public String buildStringRepository(List<GeographicArea> geographicAreas) {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));
        if (geographicAreas.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (GeographicArea ga : geographicAreas) {
            result.append(ga.getId()).append(") Name: ").append(ga.getName()).append(" | ");
            result.append("Type: ").append(ga.getAreaType().getName()).append(" | ");
            result.append("Latitude: ").append(ga.getLocal().getLatitude()).append(" | ");
            result.append("Longitude: ").append(ga.getLocal().getLongitude()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * Method to create a new geographic area before adding it to a GA List.
     *
     * @param newName      input string for geographic area name for the new geographic area
     * @param areaTypeName input string for type area for the new geographic area
     * @param length       input number for length for the new geographic area
     * @param width        input number for width for the new geographic area
     * @param local        input number for latitude, longitude and altitude of the new geographic area
     * @return a new geographic area.
     */
    public GeographicArea createGA(String newName, String areaTypeName, double length, double width, Local local) {
        AreaType areaType = getAreaTypeByName(areaTypeName);
        areaTypeRepository.save(areaType);
        return new GeographicArea(newName, areaType, length, width, local);
    }

    /**
     * Method that returns a GeographicAreaList with a given type.
     *
     * @param typeAreaName is the type of the area we want to get all the geographicAreas.
     * @return a GeographicAreaList with a given type.
     */
    public List<GeographicArea> getGeoAreasByType(List<GeographicArea> geographicAreas, String typeAreaName) {
        List<GeographicArea> finalList = new ArrayList<>();
        AreaType areaTypeToTest = new AreaType(typeAreaName);
        for (GeographicArea ga : geographicAreas) {
            if (ga.equalsTypeArea(areaTypeToTest)) {
                finalList.add(ga);
            }
        }
        return finalList;
    }

    /**
     * Checks the geographic area list size and returns the size as int.\
     *
     * @return GeographicAreaList size as int
     **/
    public int size() {
        return getAll().size();
    }

    /**
     * This method receives an index as parameter and gets a geographic area from geographic
     * area list.
     *
     * @param id the index of the GA.
     * @return returns geographic area that corresponds to index.
     */
    public GeographicArea get(long id) {
        Optional<GeographicArea> value = geographicAreaRepository.findById(id);
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("ERROR: There is no Geographic Area with the selected ID.");
    }

    /**
     * This method checks if a geographic area list is empty
     *
     * @return true if empty, false otherwise
     **/
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    public AreaType getAreaTypeByName(String name) {
        Optional<AreaType> value = areaTypeRepository.findByName(name);
        if (value.isPresent()) {
            return value.get();
        }
        return new AreaType(name);
    }
}
