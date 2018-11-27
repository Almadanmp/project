package Sprint_0;

public class TypeArea {
    private String typeDesignation;

    public TypeArea(String nameGiven) {
        setTypeOfGeographicArea(nameGiven);
    }

    public void setTypeOfGeographicArea(String nameGiven) {
        if( nameGiven != null) {
            this.typeDesignation = nameGiven;
        }
        throw new IllegalArgumentException("Please Insert Valid Designation.");
    }

    public String getTypeOfGeographicArea() {
        return this.typeDesignation;
    }

    public boolean equals(Object objectToTest) {
        if (this == objectToTest) {
            return true;
        }
        if (!(objectToTest instanceof TypeArea)) {
            return false;
        }
        TypeArea localVariable = (TypeArea) objectToTest;
        if (localVariable.getTypeOfGeographicArea().equals(this.typeDesignation)) {
            return true;
        }
        return false;
    }
}
