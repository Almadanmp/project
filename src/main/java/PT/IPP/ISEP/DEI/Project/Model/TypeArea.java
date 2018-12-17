package PT.IPP.ISEP.DEI.Project.Model;

public class TypeArea {
    private String mTypeDesignation;

    public TypeArea(String nameGiven) {
        setTypeOfGeographicArea(nameGiven);
    }

    public void setTypeOfGeographicArea(String nameGiven) {
        this.mTypeDesignation = nameGiven;
    }

    public String getTypeOfGeographicArea() {
        return this.mTypeDesignation;
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
        return localVariable.getTypeOfGeographicArea().equals(this.mTypeDesignation);
    }

    @Override
    public int hashCode() {

        return 1;
    }


}
