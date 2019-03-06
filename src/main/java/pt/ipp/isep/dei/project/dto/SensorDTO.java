package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.ReadingList;
import pt.ipp.isep.dei.project.model.TypeSensor;

import java.util.Date;
import java.util.UUID;

public class SensorDTO {

    private String name;
    private TypeSensor typeSensor;
    private Local local;
    private Date dateStartedFunctioning;
    private ReadingList readingList;
    private UUID uniqueID;

    public UUID getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(UUID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeSensor getTypeSensor() {
        return typeSensor;
    }

    public void setTypeSensor(TypeSensor typeSensor) {
        this.typeSensor = typeSensor;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Date getDateStartedFunctioning() {
        return dateStartedFunctioning;
    }

    public void setDateStartedFunctioning(Date dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }

    public ReadingList getReadingList() {
        return readingList;
    }

    public void setReadingList(ReadingList readingList) {
        this.readingList = readingList;
    }
}
