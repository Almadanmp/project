package pt.ipp.isep.dei.project.model;

import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import java.util.*;

/**
 * Class that groups a number of Geographical Areas.
 */
@Service
public class GeographicAreaService {

    private List<GeographicArea> geographicAreas;

    private GeographicAreaRepository geographicAreaRepository;

    public GeographicAreaService(GeographicAreaRepository geographicAreaRepository) {
        geographicAreas = new ArrayList<>();
        this.geographicAreaRepository = geographicAreaRepository;
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
        if (!(geographicAreas.contains(geographicAreaToAdd))) {
            geographicAreas.add(geographicAreaToAdd);
            geographicAreaRepository.save(geographicAreaToAdd);
            return true;
        }
        return false;
    }

    /**
     * Method that receives a geographic area as a parameter and adds that
     * GA to the list in case it is not contained in that list already.
     *
     * @param geographicAreaToAdd geographic area to be added
     * @return returns true in case the geographic area is added and false if not
     **/
    public boolean addGeographicArea(GeographicArea geographicAreaToAdd) {
        if (!(geographicAreas.contains(geographicAreaToAdd))) {
            geographicAreas.add(geographicAreaToAdd);
            return true;
        }
        return false;
    }

//TODO need to receive list

    /**
     * Method to print a Whole Geographic Area List.
     * It will print the attributes needed to check if a GA is different from another GA
     * (name, type of GA and Localization)
     *
     * @return a string with the names of the geographic areas
     */
    public String buildStringRepository() {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));
        List<GeographicArea> geographicAreas = getAll();
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
     * Method to check if a GA not exists and can be Created (if it has at least a different attribute from the following (name,
     * typearea or local)
     *
     * @param newName  the name of the GA
     * @param areaType the type of the GA
     * @param local    the latitude, longitude and altitude of the GA
     * @return will return true if a Geographic Area matching given parameters already
     * exists, false if it doesn't.
     */
    public boolean containsObjectMatchesParameters(String newName, AreaType areaType, Local local) {
        for (GeographicArea ga : geographicAreas) {
            if (ga.equalsParameters(newName, areaType, local)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to create a new geographic area before adding it to a GA List.
     *
     * @param newName  input string for geographic area name for the new geographic area
     * @param areaType input string for type area for the new geographic area
     * @param length   input number for length for the new geographic area
     * @param width    input number for width for the new geographic area
     * @param local    input number for latitude, longitude and altitude of the new geographic area
     * @return a new geographic area.
     */
    public GeographicArea createGA(String newName, AreaType areaType, double length, double width, Local local) {
        return new GeographicArea(newName, areaType, length, width, local);
    }

    /**
     * Checks if a the Geographic Area given as a parameter is inside the Geographic Area List
     *
     * @param geoArea geographic area to test
     * @return returns true in case the GA is contained in the list and false otherwise
     */
    boolean contains(GeographicArea geoArea) {
        return geographicAreas.contains(geoArea);
    }

    /**
     * Method that returns a GeographicAreaList with a given type.
     *
     * @param typeAreaName is the type of the area we want to get all the geographicAreas.
     * @return a GeographicAreaList with a given type.
     */
    public GeographicAreaService getGeoAreasByType(String typeAreaName) {
        GeographicAreaService finalList = new GeographicAreaService(geographicAreaRepository);
        AreaType areaTypeToTest = new AreaType(typeAreaName);
        for (GeographicArea ga : geographicAreas) {
            if (ga.equalsTypeArea(areaTypeToTest)) {
                finalList.addGeographicArea(ga);
            }
        }
        return finalList;
    }

    /**
     * Method to removeGeographicArea a geographic area if it is equal to another area (same id, type area and localization)
     *
     * @param geoArea geo area we want to removeGeographicArea
     * @return true if removed, false if failed
     */
    public boolean removeGeographicArea(GeographicArea geoArea) {
        for (GeographicArea gA : this.geographicAreas) {
            if (gA.equalsParameters(geoArea.getName(), geoArea.getAreaType(), geoArea.getLocal())) {
                this.geographicAreas.remove(gA);
                return true;
            }
        }
        return false;
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
     * Gets the list of sensors that exist in a Geographic Area List.
     *
     * @return returns a AreaSensorList of the geographical areas of the geographical area list.
     * @author Andre
     */
    public List<AreaSensor> getAreaListSensors(List<GeographicArea> geographicAreas) {
        List<AreaSensor> fullAreaSensorService = new ArrayList<>();
        if (geographicAreas.isEmpty()) {
            return fullAreaSensorService;
        }
        for (GeographicArea ga : geographicAreas) {
            if (ga.getSensorList().isEmpty()) {
                continue;
            }
            for (AreaSensor areaSensor : ga.getSensorList().getElementsAsArray()) {
                fullAreaSensorService.add(areaSensor);
            }
        }
        return fullAreaSensorService;
    }

    /**
     * Getter (array of geographic area)
     *
     * @return array of geographic area
     */
    public GeographicArea[] getElementsAsArray() {
        int sizeOfResultArray = geographicAreas.size();
        GeographicArea[] result = new GeographicArea[sizeOfResultArray];
        for (int i = 0; i < geographicAreas.size(); i++) {
            result[i] = geographicAreas.get(i);
        }
        return result;
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
     * Method to check if an instance of this class is equal to another object.
     *
     * @param testObject is the object we want to check for equality.
     * @return is true if the object is a power source list with the same contents.
     */

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof GeographicAreaService)) {
            return false;
        }
        GeographicAreaService list = (GeographicAreaService) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
