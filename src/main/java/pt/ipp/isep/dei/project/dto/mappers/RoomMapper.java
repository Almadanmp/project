package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomSensor;

import java.util.ArrayList;
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

        // Update the House ID

        String objectHouseID = dtoToConvert.getHouseId();

        // Update the device list

        DeviceList objectDeviceList = dtoToConvert.getDeviceList();

        // Update the sensors

        List<RoomSensor> objectSensorList = new ArrayList<>();
        for (RoomSensorDTO d : dtoToConvert.getSensorList()) {
            RoomSensor tempObject = RoomSensorMapper.dtoToObject(d);
            objectSensorList.add(tempObject);
        }

        // Create, update and return the converted object.

        Room resultObject = new Room(objectName, objectDescription, objectFloor, objectWidth, objectLength, objectHeight, objectHouseID);
        resultObject.setDeviceList(objectDeviceList);
        resultObject.setRoomSensors(objectSensorList);

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

        // Update the description

        String dtoDescription = objectToConvert.getDescription();

        // Update the sensorList

        List<RoomSensorDTO> dtoSensorList = new ArrayList<>();
        for (RoomSensor r : objectToConvert.getRoomSensors()) {
            RoomSensorDTO tempDTO = RoomSensorMapper.objectToDTO(r);
            dtoSensorList.add(tempDTO);
        }

        // Update the device list

        DeviceList dtoDeviceList = objectToConvert.getDeviceList();

        // Create, update and return the converted DTO.

        RoomDTO resultDTO = new RoomDTO();
        resultDTO.setName(dtoName);
        resultDTO.setFloor(dtoFloor);
        resultDTO.setHeight(dtoHeight);
        resultDTO.setLength(dtoLength);
        resultDTO.setWidth(dtoWidth);
        resultDTO.setDeviceList(dtoDeviceList);
        resultDTO.setHouseId(dtoHouseID);
        resultDTO.setDescription(dtoDescription);
        resultDTO.setSensorList(dtoSensorList);

        return resultDTO;
    }
}
