package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AreaSensorDTOWrapperList {
    @JsonProperty("area_sensor")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AreaSensorDTOWrapper> areaDTOWrappers;


    public List<AreaSensorDTOWrapper> getGeoAreaDTOWrapperList() {
        return this.areaDTOWrappers;
    }


    /**
     * Getter (array of GeographicAreaDTOWrappers)
     *
     * @return array of GeographicAreaDTOWrappers
     */
    public AreaSensorDTOWrapper[] getElementsAsArray() {
        int sizeOfResultArray = areaDTOWrappers.size();
        AreaSensorDTOWrapper[] result = new AreaSensorDTOWrapper[sizeOfResultArray];
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
        if (!(testObject instanceof AreaSensorDTOWrapperList)) {
            return false;
        }
        AreaSensorDTOWrapperList list = (AreaSensorDTOWrapperList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
