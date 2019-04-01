package pt.ipp.isep.dei.project.model;

import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of Geographical Areas.
 */
@Component
public class GeographicAreaList {

    private List<GeographicArea> geographicAreas;

    private GeographicAreaRepository geographicAreaRepository;

    public GeographicAreaList(GeographicAreaRepository geographicAreaRepository) {
        geographicAreas = new ArrayList<>();
        this.geographicAreaRepository = geographicAreaRepository;
    }

    /**
     * GeographicAreaList constructor that receives without parameters
     */
    public GeographicAreaList() {
        geographicAreas = new ArrayList<>();
    }

    /**
     * GeographicAreaList constructor that receives a Geographic Area as a parameter and
     * adds the GA to the attribute geographicAreas
     *
     * @param geographicAreaToAdd geographic area to add Without Persisting to the attribute
     */
    public GeographicAreaList(GeographicArea geographicAreaToAdd) {
        geographicAreas = new ArrayList<>();
        geographicAreas.add(geographicAreaToAdd);
    }

    /**
     * Method to return a list with all the Geographical Areas contained on the geographicAreaRepository
     *
     * @return a GeographicAreaList with all the Geographical Areas saved in the repository.
     */
    public GeographicAreaList getAll() {
        Iterable<GeographicArea> geoAreas = this.geographicAreaRepository.findAll();
        GeographicAreaList result = new GeographicAreaList(this.geographicAreaRepository);
        for (GeographicArea g : geoAreas) {
            result.addGeographicArea(g);
        }
        return result;
    }

    /**
     * Method to set a geographicAreaRepository
     *
     * @param geographicAreaRepository is a repository of Geographical Areas to be set to the object.
     */
    public void setGeographicAreaRepository(GeographicAreaRepository geographicAreaRepository) {
        this.geographicAreaRepository = geographicAreaRepository;
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


    /**
     * Method to print a Whole Geographic Area List.
     * It will print the attributes needed to check if a GA is different from another GA
     * (name, type of GA and Localization)
     *
     * @return a string with the names of the geographic areas
     */
    public String buildString() {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));

        if (this.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (int i = 0; i < this.size(); i++) {
            GeographicArea aux = this.get(i);
            result.append(i).append(") Name: ").append(aux.getName()).append(" | ");
            result.append("Type: ").append(aux.getTypeArea().getName()).append(" | ");
            result.append("Latitude: ").append(aux.getLocal().getLatitude()).append(" | ");
            result.append("Longitude: ").append(aux.getLocal().getLongitude()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * Method to check if a GA not exists and can be Created (if it has at least a different attribute from the following (name,
     * typearea or local)
     *
     * @param newName  the name of the GA
     * @param typeArea the type of the GA
     * @param local    the latitude, longitude and altitude of the GA
     * @return will return true if a Geographic Area matching given parameters already
     * exists, false if it doesn't.
     */
    public boolean containsObjectMatchesParameters(String newName, TypeArea typeArea, Local local) {
        for (GeographicArea ga : geographicAreas) {
            if (ga.equalsParameters(newName, typeArea, local)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to create a new geographic area before adding it to a GA List.
     *
     * @param newName  input string for geographic area name for the new geographic area
     * @param typeArea input string for type area for the new geographic area
     * @param length   input number for length for the new geographic area
     * @param width    input number for width for the new geographic area
     * @param local    input number for latitude, longitude and altitude of the new geographic area
     * @return a new geographic area.
     */
    public GeographicArea createGA(String newName, TypeArea typeArea, double length, double width, Local local) {
        return new GeographicArea(newName, typeArea, length, width, local);
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
    public GeographicAreaList getGeoAreasByType(String typeAreaName) {
        GeographicAreaList finalList = new GeographicAreaList();
        TypeArea typeAreaToTest = new TypeArea(typeAreaName);
        for (GeographicArea ga : geographicAreas) {
            if (ga.equalsTypeArea(typeAreaToTest)) {
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
            if (gA.equalsParameters(geoArea.getName(), geoArea.getTypeArea(), geoArea.getLocal())) {
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
        return this.geographicAreas.size();
    }

    /**
     * This method receives an index as parameter and gets a geographic area from geographic
     * area list.
     *
     * @param index the index of the GA.
     * @return returns geographic area that corresponds to index.
     */
    public GeographicArea get(int index) {
        if (this.geographicAreas.isEmpty()) {
            throw new IndexOutOfBoundsException("The geographic area list is empty.");
        }
        return this.geographicAreas.get(index);
    }

    /**
     * Gets the list of sensors that exist in a Geographic Area List.
     *
     * @return returns a SensorList of the geographical areas of the geographical area list.
     * @author Andre
     */
    public SensorList getAreaListSensors() {
        SensorList fullSensorList = new SensorList();
        if (this.geographicAreas.isEmpty()) {
            return fullSensorList;
        }
        for (GeographicArea ga : this.geographicAreas) {
            if (ga.getSensorList().isEmpty()) {
                continue;
            }
            for (Sensor sensor : ga.getSensorList().getElementsAsArray()) {
                fullSensorList.add(sensor);
            }
        }
        return fullSensorList;
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
        return this.geographicAreas.isEmpty();
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
        if (!(testObject instanceof GeographicAreaList)) {
            return false;
        }
        GeographicAreaList list = (GeographicAreaList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
