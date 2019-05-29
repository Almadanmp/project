package pt.ipp.isep.dei.project.model.energy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pt.ipp.isep.dei.project.dddplaceholders.Root;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents an Energy Grid present in a House.
 */
@Entity
public class EnergyGrid implements Root {

    @Id
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "energyGridId")
    private List<PowerSource> powerSourceList;
    private Double maxContractedPower;
    private String houseId;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "energyGridId")
    private List<String> rooms;

    /**
     * Empty Constructor to use when importing Energy Grids from XML files.
     */
    public EnergyGrid() {
        this.rooms = new ArrayList<>();
        this.powerSourceList = new ArrayList<>();
    }

    /**
     * Standard Energy Grid constructor, used for creating energy grids.
     *
     * @param name               is the name of the grid.
     * @param maxContractedPower is the value of the maximum power contracted.
     */
    public EnergyGrid(String name, Double maxContractedPower, String houseId) {
        this.rooms = new ArrayList<>();
        this.powerSourceList = new ArrayList<>();
        this.name = name;
        this.maxContractedPower = maxContractedPower;
        this.houseId = houseId;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    /**
     * Getter for name of the energy grid.
     *
     * @return returns attribute name of the energy grid.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for list of rooms that are contained in the energy grid.
     *
     * @return returns a list with all the rooms contained in this energy grid.
     */
    public List<String> getRoomIdList() {
        return rooms;
    }

    /**
     * Standard getter method, to return the Maximum contracted power.
     *
     * @return the number of the max power contracted.
     */
    public Double getMaxContractedPower() {
        return maxContractedPower;
    }

    /**
     * Getter for the list of power sources connected to the energy grid.
     *
     * @return the list of power sources connected to the energy grid.
     */
    @JsonIgnore
    public List<PowerSource> getPowerSourceList() {
        return new ArrayList<>(powerSourceList);
    }

    /**
     * Standard setter method, to define the list of power sources.
     *
     * @param powerSourceList is the PowerSourceList we want to add to the Energy Grid.
     */
    public void setPowerSourceList(List<PowerSource> powerSourceList) {
        this.powerSourceList = new ArrayList<>(powerSourceList);
    }

    /**
     * Standard setter method, to define the value of the max contracted power.
     *
     * @param power is the value of the max contracted power.
     */
    boolean setMaxContractedPower(double power) {
        if (power < 0) {
            return false;
        }
        this.maxContractedPower = power;
        return true;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseId() {
        return this.houseId;
    }

    /**
     * Setter for a power source to the energy grid.
     *
     * @param powerSource power source to be added to the energy grid.
     * @return returns true case the power source to be added is already associated to the grid.
     */
    public boolean addPowerSource(PowerSource powerSource) {
        if (powerSourceList.contains(powerSource)) {
            return false;
        }
        return powerSourceList.add(powerSource);
    }

    /**
     * Creates a string displaying the name of the grid and respective max nominal power.
     *
     * @return returns a string displaying the name of the grid and respective max nominal power.
     */
    public String buildString() {
        String result;
        result = "Energy Grid: " + this.name + ", Max Power: " + this.getMaxContractedPower();
        return result;
    }

    /**
     * This method checks if energy grid's Room List is empty.
     *
     * @return true if energy grid's RoomList is empty, false otherwise.
     **/
    public boolean isRoomListEmpty() {
        return rooms.isEmpty();
    }

    /**
     * This method checks the Room List's size.
     *
     * @return energy grid's RoomList size as int
     **/
    public int roomListSize() {
        return rooms.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyGrid eg = (EnergyGrid) o;
        return Objects.equals(name, eg.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
