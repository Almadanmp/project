package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.model.room.Room;

import java.util.ArrayList;
import java.util.List;

public final class RoomMinimalMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    private RoomMinimalMapper() {
    }

    /**
     * This is the method that converts Room Web DTOs into model objects.
     *
     * @param dtoWeb is the DTO we want to convert.
     * @return is the converted object.
     */
    public static Room dtoToObject(RoomDTOMinimal dtoWeb) {
        // Update the name

        String objectName = dtoWeb.getName();

        // Update the description

        String objectDescription = dtoWeb.getDescription();

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
     * This is the method that converts model objects into roomDTOMinimal.
     *
     * @param room is the room we want to convert.
     * @return is the converted object.
     */
    public static RoomDTOMinimal objectToDtoWeb(Room room) {
        //Update the name
        String dtoWebName = room.getId();
        String description = room.getDescription();
        //Update the floor
        int dtoWebFloor = room.getFloor();
        //Update the width
        double dtoWebWidth = room.getWidth();
        //Update the length
        double dtoWebLength = room.getLength();
        //Update the height
        double dtoWebHeight = room.getHeight();
        // Create, update and return the converted object.
        RoomDTOMinimal roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setName(dtoWebName);
        roomDTOMinimal.setDescription(description);
        roomDTOMinimal.setFloor(dtoWebFloor);
        roomDTOMinimal.setWidth(dtoWebWidth);
        roomDTOMinimal.setLength(dtoWebLength);
        roomDTOMinimal.setHeight(dtoWebHeight);
        return roomDTOMinimal;
    }

    /**
     * This method returns a list of Rooms Dto Minimal from a RoomList.
     * @param rooms is the list of rooms we want to convert.
     * @return a list of Rooms Dto Minimal.
     */
    public static List<RoomDTOMinimal> objectsToDtosWeb(List<Room> rooms){
        List<RoomDTOMinimal> roomDTOMinimalList = new ArrayList<>();
        for (Room room : rooms) {
            roomDTOMinimalList.add(RoomMinimalMapper.objectToDtoWeb(room));
        }
        return roomDTOMinimalList;
    }
}