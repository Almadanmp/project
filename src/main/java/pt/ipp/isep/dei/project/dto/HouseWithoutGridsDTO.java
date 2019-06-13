package pt.ipp.isep.dei.project.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

public class HouseWithoutGridsDTO extends ResourceSupport {
    private String id;
    private Long motherAreaId;
    private AddressDTO address;
    private LocalDTO location;
    private int gridMeteringPeriod;
    private int deviceMeteringPeriod;

    public void setId(String id) {
        this.id = id;
    }

    public String getHouseId() {
        return id;
    }

    public void setMotherAreaId(Long motherAreaId) {
        this.motherAreaId = motherAreaId;
    }

    public Long getMotherAreaId() {
        return motherAreaId;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public LocalDTO getLocation() {
        return location;
    }

    public void setLocation(LocalDTO location) {
        this.location = location;
    }

    public int getGridMeteringPeriod() {
        return gridMeteringPeriod;
    }

    public void setGridMeteringPeriod(int gridMeteringPeriod) {
        this.gridMeteringPeriod = gridMeteringPeriod;
    }

    public void setAddressAndLocalToDTOWithoutGrids(AddressLocalGeographicAreaIdDTO dto) {
        this.address.setStreet(dto.getStreet());
        this.address.setNumber(dto.getNumber());
        this.address.setZip(dto.getZip());
        this.address.setTown(dto.getTown());
        this.address.setCountry(dto.getCountry());
        this.location.setLatitude(dto.getLatitude());
        this.location.setLongitude(dto.getLongitude());
        this.location.setAltitude(dto.getAltitude());
        setMotherAreaId(dto.getGeographicAreaId());
    }

    public int getDeviceMeteringPeriod() {
        return deviceMeteringPeriod;
    }

    public void setDeviceMeteringPeriod(int deviceMeteringPeriod) {
        this.deviceMeteringPeriod = deviceMeteringPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HouseWithoutGridsDTO house = (HouseWithoutGridsDTO) o;
        return Objects.equals(this.address, house.address);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
