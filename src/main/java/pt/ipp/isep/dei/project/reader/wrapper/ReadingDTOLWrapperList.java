package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

public class ReadingDTOLWrapperList {
    @JsonProperty("reading")
    @JsonAlias({"readings"})
    @JacksonXmlElementWrapper(useWrapping = false)
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
