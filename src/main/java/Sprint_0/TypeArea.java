package Sprint_0;

public class TypeArea {
    private String name;

    public TypeArea(String name) {
        setType(name);
    }

    public void setType(String name) {
        this.name = name;
    }

    public String getType() {
        String result = this.name;
        return result;
    }

    public boolean equals(Object testType) {
        if (this == testType) {
            return true;
        }
        if (!(testType instanceof TypeArea)) {
            return false;
        }
        TypeArea localVariable = (TypeArea) testType;
        if (localVariable.getType().equals(this.name)) {
            return true;
        }
        return false;
    }
}
