package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;

import java.util.*;

/**
 * Controller class for Room Monitoring UI
 */


public class RoomMonitoringController {

    /**
     * Returns the current temperature in a given Room.
     *
     * @param roomDTO is the roomDTO we want to get the room from, so that we can get the temperature.
     * @return is the most recent temperature recorded in a room.
     */
    public double getCurrentRoomTemperature(RoomDTO roomDTO, RoomService roomService) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, roomService);
        return room.getCurrentRoomTemperature();
    }

    /**
     * This method is used to get the maximum temperature in a day in a particular Room.
     *
     * @param day     is the day we want to check the temperature in.
     * @param roomDTO is the room we want to check the temperature in.
     * @return is the max temperature recorded in a room
     */
    public double getDayMaxTemperature(RoomDTO roomDTO, Date day, RoomService roomService) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, roomService);
        return room.getMaxTemperatureOnGivenDay(day);
    }

    /**
     * This method receives a room and return the room's name
     *
     * @param roomDTO the DTO of the chosen Room.
     * @return room's name as a string
     **/

    public String getRoomName(RoomDTO roomDTO, RoomService roomService) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, roomService);
        return room.getId();
    }

    /**
     * This method is used to separate the readings in which the temperature raised
     * above the maximum comfort temperature for the respective day and category.
     *
     * @param roomDTO  is the room which the user selected.
     * @param house    is used to fetch the mother area from it and to calculate the outside average
     *                 temperature.
     * @param category is selected by the user.
     * @return a String with the requested information for the User Story 445
     */
    public String getInstantsAboveComfortInterval(House house, int category, RoomDTO roomDTO, Date startDate, Date endDate) {
        Room room = RoomMapper.dtoToObject(roomDTO);
        List<Reading> readingValues = room.getAllReadingsInInterval(startDate, endDate);
        List<Reading> allReadings;
        String result = "For the given category, in the given interval, there were no temperature readings above the max comfort temperature.";
        if (category == 0) {
            allReadings = room.getReadingsAboveCategoryILimit(readingValues, house);
            result = buildReadingDTOListOutputUS445(allReadings, house);
        }
        if (category == 1) {
            allReadings = room.getReadingsAboveCategoryIILimit(readingValues, house);
            result = buildReadingDTOListOutputUS445(allReadings, house);
        }
        if (category == 2) {
            allReadings = room.getReadingsAboveCategoryIIILimit(readingValues, house);
            result = buildReadingDTOListOutputUS445(allReadings, house);
        }
        return result;
    }

    /**
     * This method creates a string which displays the instant where the temperature is not contained
     * within the comfort interval, the temperature for that instant and the difference with the
     * outside area average temperature for that day.
     *
     * @param list  contains the readings which raised above the comfort level.
     * @param house is used to find the day outside average temperature.
     * @return a String to be presented to the user.
     */
    private String buildReadingDTOListOutputUS445(List<Reading> list, House house) {
        StringBuilder result = new StringBuilder("Instants in which the readings are above comfort temperature:\n");
        for (int i = 0; i < list.size(); i++) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            Reading reading = list.get(i);
            gregorianCalendar.setTime(reading.getDate());
            double temperature = house.getHouseAreaAverageTemperature(reading.getDate());
            result.append(i).append(") Instant: ").append(Calendar.DAY_OF_MONTH + "/" + Calendar.MONTH + "/"
                    + (Calendar.YEAR + 2017) + " " + Calendar.HOUR + ":" + Calendar.MINUTE + ":"
                    + Calendar.SECOND).append("\n");
            result.append("   Temperature value: ").append(reading.getValue()).append("\n");
            result.append("   Difference from outside day average: + ").append(reading.getValue() - temperature).append(" Cº\n");
        }
        result.append("---\n");
        return result.toString();
    }


    //US 440

    public String getInstantsBelowComfortInterval(House house, int category, RoomDTO roomDTO, Date startDate, Date endDate, RoomService roomService, GeographicAreaService geographicAreaService) {
        Room room = RoomMapper.dtoToObject(roomDTO);
        List<Reading> readingValues = roomService.getReadingsInInterval(startDate, endDate, room);
        List<Reading> allReadings;
        String result = "For the given category, in the given interval, there were no temperature readings below the min comfort temperature.";

        if (category == 0) {
            allReadings = roomService.getReadingsBelowCategoryILimit(readingValues, house, geographicAreaService);
            if (!allReadings.isEmpty()) result = buildReadingDTOListOutputUS440(allReadings);
        }
        if (category == 1) {
            allReadings = roomService.getReadingsBelowCategoryIILimit(readingValues, house, geographicAreaService);
            if (!allReadings.isEmpty()) result = buildReadingDTOListOutputUS440(allReadings);
        }
        if (category == 2) {
            allReadings = roomService.getReadingsBelowCategoryIIILimit(readingValues, house, geographicAreaService);
            if (!allReadings.isEmpty()) result = buildReadingDTOListOutputUS440(allReadings);
        }
        return result;
    }

    private String buildReadingDTOListOutputUS440(List<Reading> list) {
        StringBuilder result = new StringBuilder("Instants in which the readings are below the comfort temperature:\n");
        for (int i = 0; i < list.size(); i++) {
//            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            Reading reading = list.get(i);
//            gregorianCalendar.setTime(reading.getDate());
//            double temperature = house.getHouseAreaAverageTemperature(reading.getDate());
//            result.append(i).append(") Instant: ").append(Calendar.DAY_OF_MONTH + "/" + Calendar.MONTH + "/"
//                    + (Calendar.YEAR + 2017) + " " + Calendar.HOUR + ":" + Calendar.MINUTE + ":"
//                    + Calendar.SECOND).append("\n");
//            result.append("   Temperature value: ").append(reading.getValue()).append("\n");
//            result.append("   Difference from outside day average: ").append(Math.round(reading.getValue() - temperature) / 100.0).append(" Cº\n");
            result.append(i).append(") Instant: ").append(reading.getDate()).append("\n");
        }
        result.append("--------------------------------------\n");
        return result.toString();
    }
}