package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfGeographicAreaDTOWrapperList {
    @JsonProperty("geographical_area_list")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<GeographicAreaDTOWrapperList> areaDTOWrapperLists;

    public ListOfGeographicAreaDTOWrapperList(List<GeographicAreaDTOWrapperList> areaDTOS) {
        this.areaDTOWrapperLists = areaDTOS;
    }

    public ListOfGeographicAreaDTOWrapperList() {
        this.areaDTOWrapperLists = new ArrayList<>();
    }

    public List<GeographicAreaDTOWrapperList> getGeoAreaDTOWrapperList() {
        return this.areaDTOWrapperLists;
    }

    public void setGeoAreaDTOWrapperList(List<GeographicAreaDTOWrapperList> areaDTOList) {
        this.areaDTOWrapperLists = areaDTOList;
    }

    /**
     * Getter (array of ReadingDTOWrappers)
     *
     * @return array of ReadingDTOWrappers
     */
    public GeographicAreaDTOWrapperList[] getElementsAsArray() {
        int sizeOfResultArray = areaDTOWrapperLists.size();
        GeographicAreaDTOWrapperList[] result = new GeographicAreaDTOWrapperList[sizeOfResultArray];
        for (int i = 0; i < areaDTOWrapperLists.size(); i++) {
            result[i] = areaDTOWrapperLists.get(i);
        }
        return result;
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
        ListOfGeographicAreaDTOWrapperList list = (ListOfGeographicAreaDTOWrapperList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
