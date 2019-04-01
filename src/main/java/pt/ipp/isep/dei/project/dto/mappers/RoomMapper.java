package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.SensorDTO;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class is responsible for converting Rooms and Room DTOs into one another.
 */

public final class RoomMapper {
    /**
     * Don't let anyone instantiate this class.
     */

    private RoomMapper(){}

    /**
     * This is the method that converts Room DTOs into model objects with the same data.
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static Room dtoToObject(RoomDTO dtoToConvert){
        // Update the name

        String objectName = dtoToConvert.getName();

        // Update the floor

        int objectFloor = dtoToConvert.getFloor();

        // Update the width

        double objectWidth = dtoToConvert.getWidth();

        // Update the length

        double objectLength = dtoToConvert.getLength();

        // Update the height

        double objectHeight = dtoToConvert.getHeight();

        // Update the SensorList

        SensorList objectSensorList = new SensorList();
        for (SensorDTO y : dtoToConvert.getSensorList()){
            Sensor tempSensor = SensorMapper.dtoToObject(y);
            objectSensorList.add(tempSensor);
        }

        // Update the device list

        DeviceList objectDeviceList = dtoToConvert.getDeviceList(); // TODO Implement a solution for polymorphic device DTOs (visitor pattern?)


        // Update the UUID

        UUID objectUUID = dtoToConvert.getId();

        // Create, update and return the converted object.

        Room resultObject = new Room(objectName, objectFloor, objectWidth, objectLength, objectHeight);
        resultObject.setDeviceList(objectDeviceList);
        resultObject.setSensorList(objectSensorList);
        resultObject.setUniqueID(objectUUID);

        return resultObject;
    }

    /**
     * This is the method that converts Room model objects into DTOs with the same data.
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */

    public static RoomDTO objectToDTO(Room objectToConvert){
        // Update the name

        String dtoName = objectToConvert.getName();

        // Update the floor

        int dtoFloor = objectToConvert.getFloor();

        // Update the height

        double dtoHeight = objectToConvert.getHeight();

        // Update the width

        double dtoWidth = objectToConvert.getWidth();

        // Update the length

        double dtoLength = objectToConvert.getLength();

        // Update the SensorList

        List<SensorDTO> dtoSensorList = new ArrayList<>();
        for (Sensor y: objectToConvert.getSensorList().getSensors()){
            SensorDTO tempSensorDTO = SensorMapper.objectToDTO(y);
            if (!(dtoSensorList.contains(tempSensorDTO))){
                dtoSensorList.add(tempSensorDTO);
            }
        }

        // Update the device list

        DeviceList dtoDeviceList = objectToConvert.getDeviceList(); // TODO Implement a solution for polymorphic device DTOs (visitor pattern?)

        // Update the UUID

        UUID dtoID = objectToConvert.getUniqueID();

        // Create, update and return the converted DTO.

        RoomDTO resultDTO = new RoomDTO();
        resultDTO.setName(dtoName);
        resultDTO.setFloor(dtoFloor);
        resultDTO.setHeight(dtoHeight);
        resultDTO.setLength(dtoLength);
        resultDTO.setWidth(dtoWidth);
        resultDTO.setSensorList(dtoSensorList);
        resultDTO.setDeviceList(dtoDeviceList);
        resultDTO.setId(dtoID);

        return resultDTO;
    }

    /**
     * Method that updates a room contained in a given house with the data contained in a given DTO. It matches the
     * DTO to the object through UUID.
     *
     * @param roomDTO is the DTO that contains the data we want to use to update the model object.
     * @param house   is the house that contains the room we want to update.
     * @return is the updated room if the update was successful, is null if it wasn't.
     */
    public static Room updateHouseRoom(RoomDTO roomDTO, House house) {
        Room room = null;
        RoomList roomlist = house.getRoomList();
        for (Room r : roomlist.getRooms()) {
            if (roomDTO.getId().compareTo(r.getUniqueID()) == 0) {
                r = RoomMapper.dtoToObject(roomDTO);
                room = r;
            }
        }
        return room;
    }
}
