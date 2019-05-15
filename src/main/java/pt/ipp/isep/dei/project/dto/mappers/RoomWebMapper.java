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

        // Update the Energy Grid ID

        String objectEnergyGridID = null;

        // Update the House ID

        String objectHouseID = null;

        // Create, update and return the converted object.

        Room resultObject = new Room(objectName, objectDescription, objectFloor, objectWidth, objectLength, objectHeight, objectHouseID, objectEnergyGridID);

        return resultObject;
    }
}
