package pt.ipp.isep.dei.project.dto;

import java.util.UUID;

public class TypeAreaDTO {

    private String name;
    private UUID uniqueID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(UUID uniqueID) {
        this.uniqueID = uniqueID;
    }

    /**
     * Method 'equals' is required so that each 'Area Type' can be added to a 'Geographic Area'.
     */
    @Override
    public boolean equals(Object objectToTest) {
        if (this == objectToTest) {
            return true;
        }
        if (!(objectToTest instanceof TypeAreaDTO)) {
            return false;
        }
        TypeAreaDTO localVariable = (TypeAreaDTO) objectToTest;
        return localVariable.getName().equals(this.name);
    }

    @Override
    public int hashCode() {

        return 1;
    }


}
