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

    @Override
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

    @Override
    public int hashCode(){
        return 1;
    }
}
