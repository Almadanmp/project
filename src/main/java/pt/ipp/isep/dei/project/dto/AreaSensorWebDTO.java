package pt.ipp.isep.dei.project.dto;

public class AreaSensorWebDTO {

    private String id;
    private String name;
    private String typeSensor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return typeSensor;
    }

    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
    }

    @Override
    public boolean equals(Object testDTO) {
        if (this == testDTO) {
            return true;
        }
        if (!(testDTO instanceof AreaSensorDTO)) {
            return false;
        }
        AreaSensorWebDTO localVariable = (AreaSensorWebDTO) testDTO;
        return (localVariable.getId().equals(this.id) && localVariable.getName().equals(this.name));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
