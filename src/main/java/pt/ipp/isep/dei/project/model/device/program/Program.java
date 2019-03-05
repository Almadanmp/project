package pt.ipp.isep.dei.project.model.device.program;

import java.util.List;

public interface Program {

    /**
     * Represents a program option for devices.
     * A programmable device implements a number of different programs. Types of programs may vary from device to
     * device.
     *
     * @return a built String.
     */
    String buildString();

    void setProgramName(String name);

    String getProgramName();


    List<String> getAttributeNames();

    boolean setAttributeValue(String attributeName, Object attributeValue);

    Object getAttributeUnit(String attributeName);

    Object getAttributeValue(String attributeName);


}
