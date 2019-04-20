package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.device.DeviceList;

import java.util.List;

/**
 * This class is responsible for converting Rooms and Room DTOs into one another.
 */

public final class RoomMapper {
    /**
     * Don't let anyone instantiate this class.
     */

    private RoomMapper() {
    }

    /**
     * This is the method that converts Room DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static Room dtoToObject(RoomDTO dtoToConvert) {
        // Update the name

        String objectName = dtoToConvert.getName();

        // Update the description

        String objectDescription = dtoToConvert.getDescription();

        // Update the floor

        int objectFloor = dtoToConvert.getFloor();

        // Update the width

        double objectWidth = dtoToConvert.getWidth();

        // Update the length

        double objectLength = dtoToConvert.getLength();

        // Update the height

        double objectHeight = dtoToConvert.getHeight();

        // Update the Energy Grid ID

        String objectEnergyGridID = dtoToConvert.getEnergyGridID();

        // Update the House ID

        String objectHouseID = dtoToConvert.getHouseId();

        // Update the device list

        DeviceList objectDeviceList = dtoToConvert.getDeviceList();


        // Create, update and return the converted object.

        Room resultObject = new Room(objectName, objectDescription, objectFloor, objectWidth, objectLength, objectHeight, objectHouseID, objectEnergyGridID);
        resultObject.setDeviceList(objectDeviceList);

        return resultObject;
    }

    public static Room dtoToObjectUS100(RoomDTO dtoToConvert) {
        // Update the name

        String objectName = dtoToConvert.getName();

        // Update the description

        String objectDescription = dtoToConvert.getDescription();

        // Update the floor

        int objectFloor = dtoToConvert.getFloor();

        // Update the width

        double objectWidth = dtoToConvert.getWidth();

        // Update the length

        double objectLength = dtoToConvert.getLength();

        // Update the height

        double objectHeight = dtoToConvert.getHeight();

        // Update the houseID

        String objectHouseID = dtoToConvert.getHouseId();

        // Update the energy grid ID

        String objectEnergyGridID = dtoToConvert.getEnergyGridID();

        // Create, update and return the converted object.

        Room resultObject = new Room(objectName, objectDescription, objectFloor, objectWidth, objectLength, objectHeight,
                objectHouseID, objectEnergyGridID);

        return resultObject;
    }

    /**
     * This is the method that converts Room model objects into DTOs with the same data.
     *
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */

    public static RoomDTO objectToDTO(Room objectToConvert) {
        // Update the name

        String dtoName = objectToConvert.getId();

        // Update the floor

        int dtoFloor = objectToConvert.getFloor();

        // Update the height

        double dtoHeight = objectToConvert.getHeight();

        // Update the width

        double dtoWidth = objectToConvert.getWidth();

        // Update the length

        double dtoLength = objectToConvert.getLength();

        // Update the houseID

        String dtoHouseID = objectToConvert.getHouseID();

        // Update the energyGridID

        String dtoEnergyGridID = objectToConvert.getEnergyGridID();

        // Update the AreaSensorList

        // Update the device list

        DeviceList dtoDeviceList = objectToConvert.getDeviceList(); // TODO Implement a solution for polymorphic device DTOs (visitor pattern?)

        // Create, update and return the converted DTO.

        RoomDTO resultDTO = new RoomDTO();
        resultDTO.setName(dtoName);
        resultDTO.setFloor(dtoFloor);
        resultDTO.setHeight(dtoHeight);
        resultDTO.setLength(dtoLength);
        resultDTO.setWidth(dtoWidth);
        resultDTO.setDeviceList(dtoDeviceList);
        resultDTO.setEnergyGridName(dtoEnergyGridID);
        resultDTO.setHouseId(dtoHouseID);

        return resultDTO;
    }

    /**
     * Method that updates a room contained in a given house with the data contained in a given DTO. It matches the
     * DTO to the object through UUID.
     *
     * @param roomDTO is the DTO that contains the data we want to use to update the model object.
     * @return is the updated room if the update was successful, is null if it wasn't.
     */
    public static Room updateHouseRoom(RoomDTO roomDTO, RoomService roomService) {
        Room room = null;
        List<Room> rooms = roomService.getAllRooms();
        for (Room r : rooms) {
            if (roomDTO.getName().compareTo(r.getId()) == 0) {
                r = RoomMapper.dtoToObject(roomDTO);
                room = r;
            }
        }
        return room;
    }
}
