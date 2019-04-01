package pt.ipp.isep.dei.project.dto;

import java.util.List;

public class EnergyGridDTO {

    private List<RoomDTO> roomDTOS;
    private String name;
    private List<PowerSourceDTO> powerSourceDTOS;
    private double maxContractedPower;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMaxContractedPower(double maxContractedPower) {
        this.maxContractedPower = maxContractedPower;
    }

    public double getMaxContractedPower() {
        return maxContractedPower;
    }

    public void setPowerSourceDTOS(List<PowerSourceDTO> powerSourceDTOS) {
        this.powerSourceDTOS = powerSourceDTOS;
    }

    public List<PowerSourceDTO> getPowerSourceDTOS() {
        return powerSourceDTOS;
    }

    public void setRoomDTOS(List<RoomDTO> roomDTOS) {
        this.roomDTOS = roomDTOS;
    }

    public List<RoomDTO> getRoomDTOS() {
        return roomDTOS;
    }

    /**
     * Method that determines if an instance of this object is equal to another instance. The rules for determining this
     * should match the rules for determining if two instances of the original model object are equal.
     * @param testDTO is the instance of this object we want to test for equality.
     * @return is true if the instances are equal, false if they aren't.
     */

    @Override
    public boolean equals(Object testDTO) {
        if (this == testDTO) {
            return true;
        }
        if (!(testDTO instanceof EnergyGridDTO)) {
            return false;
        }
        EnergyGridDTO localVariable = (EnergyGridDTO) testDTO;
        return (localVariable.getName().equals(this.name) && Double.compare(localVariable.getMaxContractedPower(),
                this.maxContractedPower) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

