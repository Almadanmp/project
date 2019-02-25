package pt.ipp.isep.dei.project.model;

/**
 * Class that represents the type of Geographical Area.
 */

public class TypeArea {
    private String name;

    /**
     * Main and only Area Type Constructor
     * @param nameGiven The name of the type of area
     */
    public TypeArea(String nameGiven) {
        this.name = nameGiven;
    }

    /**
     * Sets the type of a Geographical Area
     */
    void setTypeOfGeographicArea(String nameGiven) {
        this.name = nameGiven;
    }

    /**
     * Gets the type of a Geographical Area
     *
     * @return string with the name of the type
     */

    public String getTypeOfGeographicArea() {
        return this.name;
    }


    /**
     * Method to restrain input name so they cant be null or empty
     *
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */
    boolean isNameValid(String name) {
        if (name != null && !name.isEmpty() && !name.matches(".*\\d+.*")) {
            return true;
        }
        throw new IllegalArgumentException("Please Insert Valid Name");
    }

    /**
     * Method 'equals' is required so that each 'Area Type' can be added to a 'Geographic Area'.
     */

    @Override
    public boolean equals(Object objectToTest) {
        if (this == objectToTest) {
            return true;
        }
        if (!(objectToTest instanceof TypeArea)) {
            return false;
        }
        TypeArea localVariable = (TypeArea) objectToTest;
        return localVariable.getTypeOfGeographicArea().equals(this.name);
    }

    @Override
    public int hashCode() {

        return 1;
    }


}
