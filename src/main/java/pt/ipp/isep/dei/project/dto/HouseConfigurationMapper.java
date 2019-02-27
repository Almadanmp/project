package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.model.House;

public class HouseConfigurationMapper {
    private HouseDTO houseDTO = new HouseDTO();
    private RoomDTO roomDTO = new RoomDTO();

    HouseDTO getHousetoDTO(House house) {
        this.houseDTO.setId(house.getHouseId());
        this.houseDTO.setLocation(house.getLocation());
        this.houseDTO.setRoomList(house.getRoomList());
        this.houseDTO.setMotherArea(house.getMotherArea());
        return houseDTO;
    }

    void setDTOHouseToHouse(House house, HouseDTO houseDTO) {
        house.setId(houseDTO.getId());
        house.setLocation(houseDTO.getLocation().getLatitude(), houseDTO.getLocation().getLongitude(), houseDTO.getLocation().getAltitude());
        house.setRoomList(houseDTO.getRoomList());
        house.setMotherArea(houseDTO.getMotherArea());
    }


}


