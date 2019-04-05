package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ReadingDTOLWrapperList {
    @JsonProperty("readings")
    private List<ReadingDTOWrapper> readingDTOList;

    public ReadingDTOLWrapperList(List<ReadingDTOWrapper> readingDTOS){
        this.readingDTOList = readingDTOS;
    }

    public ReadingDTOLWrapperList(){
        this.readingDTOList = new ArrayList<>();
    }

    public List<ReadingDTOWrapper> getReadingDTOWrapperList(){
        return this.readingDTOList;
    }

    public void setReadingDTOWrapperList(List<ReadingDTOWrapper> readingDTOList){
        this.readingDTOList = readingDTOList;
    }
}
