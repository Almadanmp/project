package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeographicAreaDTOWrapperList {
    @JsonProperty("geographical_area_list")
    @JsonAlias({"geographical_area"})
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<GeographicAreaDTOWrapper> readingDTOList;

    public GeographicAreaDTOWrapperList(List<GeographicAreaDTOWrapper> readingDTOS) {
        this.readingDTOList = readingDTOS;
    }

    public GeographicAreaDTOWrapperList() {
        this.readingDTOList = new ArrayList<>();
    }

    public List<GeographicAreaDTOWrapper> getGeoAreaDTOWrapperList() {
        return this.readingDTOList;
    }

    public void setGeoAreaDTOWrapperList(List<GeographicAreaDTOWrapper> readingDTOList) {
        this.readingDTOList = readingDTOList;
    }

    /**
     * Getter (array of ReadingDTOWrappers)
     *
     * @return array of ReadingDTOWrappers
     */
    public GeographicAreaDTOWrapper[] getElementsAsArray() {
        int sizeOfResultArray = readingDTOList.size();
        GeographicAreaDTOWrapper[] result = new GeographicAreaDTOWrapper[sizeOfResultArray];
        for (int i = 0; i < readingDTOList.size(); i++) {
            result[i] = readingDTOList.get(i);
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
        GeographicAreaDTOWrapperList list = (GeographicAreaDTOWrapperList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
