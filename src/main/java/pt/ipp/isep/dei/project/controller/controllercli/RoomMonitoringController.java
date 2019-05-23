package pt.ipp.isep.dei.project.controller.controllercli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller class for Room Monitoring UI
 */

@Service
public class RoomMonitoringController {
    private static final String COMFORT_BELOW_LEVEL = "Instants in which the readings are below the comfort temperature:\n";
    private static final String COMFORT_ABOVE_LEVEL = "Instants in which the readings are above comfort temperature:\n";
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    GeographicAreaHouseService geographicAreaHouseService;

    /**
     * Returns the current temperature in a given Room.
     *
     * @param roomDTO is the roomDTO we want to get the room from, so that we can get the temperature.
     * @return is the most recent temperature recorded in a room.
     */
    public double getCurrentRoomTemperature(RoomDTO roomDTO) {
        Room room = roomRepository.updateHouseRoom(roomDTO);
        return room.getCurrentRoomTemperature();
    }

    /**
     * This method is used to get the maximum temperature in a day in a particular Room.
     *
     * @param day     is the day we want to check the temperature in.
     * @param roomDTO is the room we want to check the temperature in.
     * @return is the max temperature recorded in a room
     */
    public double getDayMaxTemperature(RoomDTO roomDTO, Date day) {
        Room room = roomRepository.updateHouseRoom(roomDTO);
        return room.getMaxTemperatureOnGivenDay(day);
    }

    /**
     * This method receives a room and return the room's name
     *
     * @param roomDTO the DTO of the chosen Room.
     * @return room's name as a string
     **/

    public String getRoomName(RoomDTO roomDTO) {
        Room room = roomRepository.updateHouseRoom(roomDTO);
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
        List<Reading> readingValues = roomRepository.getTemperatureReadingsBetweenDates(startDate, endDate, room);
        List<Reading> allReadings;
        String result = "For the given category, in the given interval, there were no temperature readings above the max comfort temperature.";
        if (category == 0) {
            allReadings = geographicAreaHouseService.getReadingsAboveCategoryILimit(readingValues, house);
            if (!allReadings.isEmpty()) result = buildReadingsOutput(allReadings, COMFORT_ABOVE_LEVEL);
        }
        if (category == 1) {
            allReadings = geographicAreaHouseService.getReadingsAboveCategoryIILimit(readingValues, house);
            if (!allReadings.isEmpty()) result = buildReadingsOutput(allReadings, COMFORT_ABOVE_LEVEL);
        }
        if (category == 2) {
            allReadings = geographicAreaHouseService.getReadingsAboveCategoryIIILimit(readingValues, house);
            if (!allReadings.isEmpty()) result = buildReadingsOutput(allReadings, COMFORT_ABOVE_LEVEL);
        }
        return result;
    }

    /**
     * This method creates a string which displays the instant where the temperature is not contained
     * within the comfort interval, the temperature for that instant and the difference with the
     * outside area average temperature for that day.
     *
     * @param list contains the readings which raised above the comfort level.
     * @return a String to be presented to the user.
     */
    String buildReadingsOutput(List<Reading> list, String header) {
        StringBuilder result = new StringBuilder(header);
        for (int i = 0; i < list.size(); i++) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
            Reading reading = list.get(i);
            result.append(i).append(") Instant: ").append(formatter.format(reading.getDate()));
            result.append("   Temperature value: ").append(reading.getValue()).append("\n");
        }
        result.append("--------------------------------------\n");
        return result.toString();
    }


    //US 440

    public String getInstantsBelowComfortInterval(House house, int category, RoomDTO roomDTO, Date startDate, Date endDate) {
        Room room = RoomMapper.dtoToObject(roomDTO);
        List<Reading> readingValues = roomRepository.getTemperatureReadingsBetweenDates(startDate, endDate, room);
        List<Reading> allReadings;
        String result = "For the given category, in the given interval, there were no temperature readings below the min comfort temperature.";
        if (category == 0) {
            allReadings = geographicAreaHouseService.getReadingsBelowCategoryILimit(readingValues, house);

            if (!allReadings.isEmpty()) result = buildReadingsOutput(allReadings, COMFORT_BELOW_LEVEL);
        }
        if (category == 1) {
            allReadings = geographicAreaHouseService.getReadingsBelowCategoryIILimit(readingValues, house);

            if (!allReadings.isEmpty()) result = buildReadingsOutput(allReadings, COMFORT_BELOW_LEVEL);
        }
        if (category == 2) {
            allReadings = geographicAreaHouseService.getReadingsBelowCategoryIIILimit(readingValues, house);
            if (!allReadings.isEmpty()) result = buildReadingsOutput(allReadings, COMFORT_BELOW_LEVEL);
        }
        return result;
    }

    /**
     * This is a shared methods between many User stories and it checks if the House has its Mother Area defined and
     * if that Mother Area has a valid AreaSensorList
     *
     * @param house - house to get Mother Area from
     * @return true in case both conditions are met
     */
    public boolean isMotherAreaValid(House house) {
        if (house.isMotherAreaNull()) {
            UtilsUI.printMessage(UtilsUI.INVALID_MOTHER_AREA);
            return false;
        }
        return true;
    }
}
