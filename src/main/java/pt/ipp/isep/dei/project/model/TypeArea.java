package pt.ipp.isep.dei.project.model;

/**
 * The TypeArea class.
 * A TypeArea is has a name (designation).
 * We cannot create two TypeAreas with the same name.
 */

public class TypeArea {
    private String name;

    /**
     * Main and only Area Type Constructor
     *
     * @param nameGiven The name of the type of area
     */
    public TypeArea(String nameGiven) {
        this.name = nameGiven;
    }

    /**
     * Gets the type of a Geographical Area
     *
     * @return string with the name of the type
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets de Name of the Type Area. Will Validate if input characters for the name are valid
     *
     * @param name input name to be set as the type area name
     */
    void setName(String name) {
        if (isNameValid(name)) {
            this.name = name;
            return;
        }
        throw new IllegalArgumentException("Please Insert Valid Name");
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
        return localVariable.getName().equals(this.name);
    }

    @Override
    public int hashCode() {

        return 1;
    }


}
