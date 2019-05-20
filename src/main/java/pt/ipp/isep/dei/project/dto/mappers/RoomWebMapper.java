package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.model.room.Room;

public class RoomWebMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    private RoomWebMapper() {
    }

    /**
     * This is the method that converts Room Web DTOs into model objects.
     *
     * @param dtoWeb is the DTO we want to convert.
     * @return is the converted object.
     */
    public static Room dtoToObject(RoomDTOWeb dtoWeb) {
        // Update the name

        String objectName = dtoWeb.getName();

        // Update the description

        String objectDescription = "";

        // Update the floor

        int objectFloor = dtoWeb.getFloor();

        // Update the width

        double objectWidth = dtoWeb.getWidth();

        // Update the length

        double objectLength = dtoWeb.getLength();

        // Update the height

        double objectHeight = dtoWeb.getHeight();

        // Update the House ID

        String objectHouseID = null;

        // Create, update and return the converted object.

        return new Room(objectName, objectDescription, objectFloor, objectWidth, objectLength, objectHeight, objectHouseID);
    }

    /**
     * This is the method that converts model objects into RoomDtoWeb.
     *
     * @param room is the room we want to convert.
     * @return is the converted object.
     */
    public static RoomDTOWeb objectToDtoWeb(Room room) {
        //Update the name
        String dtoWebName = room.getId();
        //Update the floor
        int dtoWebFloor = room.getFloor();
        //Update the width
        double dtoWebWidth = room.getWidth();
        //Update the length
        double dtoWebLength = room.getLength();
        //Update the height
        double dtoWebHeight = room.getHeight();
        // Create, update and return the converted object.
        RoomDTOWeb roomDTOWeb = new RoomDTOWeb();
        roomDTOWeb.setName(dtoWebName);
        roomDTOWeb.setFloor(dtoWebFloor);
        roomDTOWeb.setWidth(dtoWebWidth);
        roomDTOWeb.setLength(dtoWebLength);
        roomDTOWeb.setHeight(dtoWebHeight);
        return roomDTOWeb;
    }
}