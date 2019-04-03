package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithSensorID;

import java.util.ArrayList;
import java.util.List;

public class ReadingDTOWrapper {
    @JsonProperty("readings")
    private List<ReadingDTOWithSensorID> readingDTOList;

    public ReadingDTOWrapper(List<ReadingDTOWithSensorID> readingDTOS){
        this.readingDTOList = readingDTOS;
    }

    public ReadingDTOWrapper(){
        this.readingDTOList = new ArrayList<>();
    }

    public List<ReadingDTOWithSensorID> getReadingDTOList(){
        return this.readingDTOList;
    }

    public void setReadingDTOList(List<ReadingDTOWithSensorID> readingDTOList){
        this.readingDTOList = readingDTOList;
    }
}
