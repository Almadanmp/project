package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@JacksonXmlRootElement
public class GeographicAreaDTOWrapperList {
    @JsonProperty("geographical_area")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<GeographicAreaDTOWrapper> areaDTOWrappers;

    public GeographicAreaDTOWrapperList(List<GeographicAreaDTOWrapper> geographicAreaDTOWrappers) {
        this.areaDTOWrappers = geographicAreaDTOWrappers;
    }

    public GeographicAreaDTOWrapperList() {
        this.areaDTOWrappers = new ArrayList<>();
    }

    public List<GeographicAreaDTOWrapper> getGeoAreaDTOWrapperList() {
        return this.areaDTOWrappers;
    }

    void setAreaDTOWrappers(List<GeographicAreaDTOWrapper> areaDTOWrappers) {
        this.areaDTOWrappers = areaDTOWrappers;
    }

    /**
     * Getter (array of GeographicAreaDTOWrappers)
     *
     * @return array of GeographicAreaDTOWrappers
     */
    public GeographicAreaDTOWrapper[] getElementsAsArray() {
        int sizeOfResultArray = areaDTOWrappers.size();
        GeographicAreaDTOWrapper[] result = new GeographicAreaDTOWrapper[sizeOfResultArray];
        for (int i = 0; i < areaDTOWrappers.size(); i++) {
            result[i] = areaDTOWrappers.get(i);
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
        if (!(testObject instanceof GeographicAreaService)) {
            return false;
        }
        GeographicAreaDTOWrapperList list = (GeographicAreaDTOWrapperList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
