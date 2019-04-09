package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.HouseSensorDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomService;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.sensor.HouseSensor;
import pt.ipp.isep.dei.project.model.sensor.HouseSensorService;

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

        // Update the AreaSensorList

        HouseSensorService objectSensorList = new HouseSensorService();
        for (HouseSensorDTO y : dtoToConvert.getSensorList()) {
            HouseSensor tempAreaSensor = HouseSensorMapper.dtoToObject(y);
            objectSensorList.add(tempAreaSensor);
        }

        // Update the device list

        DeviceList objectDeviceList = dtoToConvert.getDeviceList(); // TODO Implement a solution for polymorphic device DTOs (visitor pattern?)



        // Create, update and return the converted object.

        Room resultObject = new Room(objectName, objectDescription, objectFloor, objectWidth, objectLength, objectHeight);
        resultObject.setDeviceList(objectDeviceList);
        resultObject.setSensorList(objectSensorList);


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


        // Create, update and return the converted object.

        Room resultObject = new Room(objectName, objectDescription, objectFloor, objectWidth, objectLength, objectHeight);


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

        String dtoName = objectToConvert.getName();

        // Update the floor

        int dtoFloor = objectToConvert.getFloor();

        // Update the height

        double dtoHeight = objectToConvert.getHeight();

        // Update the width

        double dtoWidth = objectToConvert.getWidth();

        // Update the length

        double dtoLength = objectToConvert.getLength();

        // Update the AreaSensorList

        List<HouseSensorDTO> dtoSensorList = new ArrayList<>();
        for (HouseSensor y : objectToConvert.getSensorList().getSensors()) {
            HouseSensorDTO tempAreaSensorDTO = HouseSensorMapper.objectToDTO(y);
            if (!(dtoSensorList.contains(tempAreaSensorDTO))) {
                dtoSensorList.add(tempAreaSensorDTO);
            }
        }

        // Update the device list

        DeviceList dtoDeviceList = objectToConvert.getDeviceList(); // TODO Implement a solution for polymorphic device DTOs (visitor pattern?)

        // Create, update and return the converted DTO.

        RoomDTO resultDTO = new RoomDTO();
        resultDTO.setName(dtoName);
        resultDTO.setFloor(dtoFloor);
        resultDTO.setHeight(dtoHeight);
        resultDTO.setLength(dtoLength);
        resultDTO.setWidth(dtoWidth);
        resultDTO.setSensorList(dtoSensorList);
        resultDTO.setDeviceList(dtoDeviceList);

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
        RoomService roomlist = house.getRoomService();
        for (Room r : roomlist.getRooms()) {
            if (roomDTO.getName().compareTo(r.getName()) == 0) {
                r = RoomMapper.dtoToObject(roomDTO);
                room = r;
            }
        }
        return room;
    }
}
