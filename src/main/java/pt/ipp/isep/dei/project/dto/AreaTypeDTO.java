package pt.ipp.isep.dei.project.dto;

public class AreaTypeDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Method 'equals' is required so that each 'Area Type' can be added to a 'Geographic Area'.
     */
    @Override
    public boolean equals(Object objectToTest) {
        if (this == objectToTest) {
            return true;
        }
        if (!(objectToTest instanceof AreaTypeDTO)) {
            return false;
        }
        AreaTypeDTO localVariable = (AreaTypeDTO) objectToTest;
        return localVariable.getName().equals(this.name);
    }

    @Override
    public int hashCode() {

        return 1;
    }
}
