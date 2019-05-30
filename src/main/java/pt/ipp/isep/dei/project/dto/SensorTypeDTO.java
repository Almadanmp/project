package pt.ipp.isep.dei.project.dto;

public class SensorTypeDTO {

    private String name;
    private String units;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object objectToTest) {
        if (this == objectToTest) {
            return true;
        }
        if (!(objectToTest instanceof SensorTypeDTO)) {
            return false;
        }
        SensorTypeDTO localVariable = (SensorTypeDTO) objectToTest;
        return localVariable.getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
