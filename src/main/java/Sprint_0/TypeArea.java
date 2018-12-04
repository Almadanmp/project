package Sprint_0;

public class TypeArea {
    private String typeDesignation;

    public TypeArea(String nameGiven) {
        setTypeOfGeographicArea(nameGiven);
    }

    public void setTypeOfGeographicArea(String nameGiven) {
        this.typeDesignation = nameGiven;
    }

    public String getTypeOfGeographicArea() {
        return this.typeDesignation;
    }

    /**
     * Method to restrain input name so they cant be null or empty
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */
    public boolean isNameValid(String name) {
        if (name != null && !name.isEmpty() && !name.matches(".*\\d+.*")) {
                return true;
        }
        throw new IllegalArgumentException("Please Insert Valid Name");
    }

    @Override
    public boolean equals(Object objectToTest) {
        if (this == objectToTest) {
            return true;
        }
        if (!(objectToTest instanceof TypeArea)) {
            return false;
        }
        TypeArea localVariable = (TypeArea) objectToTest;
        if (localVariable.getTypeOfGeographicArea().equals(typeDesignation)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return 1;
    }


}
