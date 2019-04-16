package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadingDTOLWrapperList {
    @JsonProperty("reading")
    @JsonAlias({"readings"})
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ReadingDTOWrapper> readingDTOList;

    public ReadingDTOLWrapperList(List<ReadingDTOWrapper> list) {
        this.readingDTOList = list;
    }

    public ReadingDTOLWrapperList() {
        this.readingDTOList = new ArrayList<>();
    }

    public List<ReadingDTOWrapper> getReadingDTOWrapperList() {
        return this.readingDTOList;
    }

    /**
     * Getter (array of ReadingDTOWrappers)
     *
     * @return array of ReadingDTOWrappers
     */
    public ReadingDTOWrapper[] getElementsAsArray() {
        int sizeOfResultArray = readingDTOList.size();
        ReadingDTOWrapper[] result = new ReadingDTOWrapper[sizeOfResultArray];
        for (int i = 0; i < readingDTOList.size(); i++) {
            result[i] = readingDTOList.get(i);
        }
        return result;
    }

    /**
     * * Method to check if an instance of this class is equal to another object.
     *
     * @param testObject is the object we want to check for equality.
     * @return is true if the object is a power source list with the same contents.
     */
    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof ReadingDTOLWrapperList)) {
            return false;
        }
        ReadingDTOLWrapperList list = (ReadingDTOLWrapperList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
