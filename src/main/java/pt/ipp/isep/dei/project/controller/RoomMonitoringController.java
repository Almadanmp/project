package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.ReadingMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
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

    RoomDTO getRoomDTOByList(RoomService roomService) {
        List<Room> rooms = roomService.getAllRooms();
        Room room = InputHelperUI.getHouseRoomByList(roomService, rooms);
        return RoomMapper.objectToDTO(room);
    }

    public boolean calculusByCategory440(ReadingDTO readingDTO, int category, House house) {
        Date actualDate = readingDTO.getDate();
        GeographicArea geographicArea = house.getMotherArea();
        getAreaAverageTemperature(actualDate, geographicArea, house);
        if (category == 0) {
            double minT = 0.33 * getAreaAverageTemperature(actualDate, geographicArea, house) + 18.8 - 2;
            return readingDTO.getValue() < minT;
        }
        if (category == 1) {
            double minT = 0.33 * getAreaAverageTemperature(actualDate, geographicArea, house) + 18.8 - 3;
            return readingDTO.getValue() < minT;
        }
        if (category == 2) {
            double minT = 0.33 * getAreaAverageTemperature(actualDate, geographicArea, house) + 18.8 - 4;
            return readingDTO.getValue() < minT;
        }
        return false;
    }

    public void displayTemperaturesBelowComfortLevel(List<ReadingDTO> list, House house, int category) {
        String result = "For the given category, in the given interval, there were no temperature readings above the max comfort temperature.";
        List<Double> outsideTempInDay = new ArrayList<>();
        List<ReadingDTO> finalList = new ArrayList<>();
        for (ReadingDTO readingDTO : list) {
            if (calculusByCategory440(readingDTO, category, house)) {
                finalList.add(readingDTO);
                outsideTempInDay.add(readingDTO.getValue());
            }
        }
        buildReadingDTOListOutputUS440(finalList, outsideTempInDay);
    }

    /**
     * This method creates a string which displays the instant where the temperature is not contained
     * within the comfort interval, the temperature for that instant and the difference with the
     * outside area average temperature for that day.
     *
     * @param list               contains the readings which raised above the comfort level.
     * @param outsideTemperature refers to the area average temperature for that day.
     * @return a String to be presented to the user.
     */
    private String buildReadingDTOListOutputUS440(List<ReadingDTO> list, List<Double> outsideTemperature) {
        StringBuilder result = new StringBuilder("Instants in which the readings are below comfort temperature:\n");
        for (int i = 0; i < list.size(); i++) {
            ReadingDTO readingDTO = list.get(i);
            result.append(i).append(") Instant: ").append(readingDTO.getDate()).append("\n");
            result.append(", Temperature value: ").append(readingDTO.getValue()).append("\n");
            result.append("Difference: + ").append(outsideTemperature.get(i) - readingDTO.getValue()).append(" Cº\n");
        }
        result.append("---\n");
        return result.toString();
    }

    /**
     * Method to check id a given reading is above the comfort temperature for category I.
     *
     * @param readingDTO      - Reading as DTO.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    private boolean categoryICalculusUS445(ReadingDTO readingDTO, Double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 2;
        return readingDTO.getValue() > minT;
    }

    /**
     * Method to check id a given reading is above the comfort temperature for category II.
     *
     * @param readingDTO      - Reading as DTO.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    private boolean categoryIICalculusUS445(ReadingDTO readingDTO, Double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 3;
        return readingDTO.getValue() > minT;
    }

    /**
     * Method to check id a given reading is above the comfort temperature for category III.
     *
     * @param readingDTO      - Reading as DTO.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    private boolean categoryIIICalculusUS445(ReadingDTO readingDTO, Double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 4;
        return readingDTO.getValue() > minT;
    }

    /**
     * This method is used to separate the readings in which the temperature raised
     * above the maximum comfort temperature for the respective day and category.
     *
     * @param roomService is used to gather the readings from the database
     * @param house       is used to fetch the mother area from it and to calculate the outside average
     *                    temperature.
     * @param category    is selected by the user.
     * @return a String with the requested information for the User Story 445
     */
    public String getInstantsAboveComfortInterval(RoomService roomService, House house, int category) {
        List<ReadingDTO> readingValues = getAllReadingsInInterval(roomService);
        List<ReadingDTO> allReadings = new ArrayList<>();
        List<Double> outsideTempInDay = new ArrayList<>();
        String result = "For the given category, in the given interval, there were no temperature readings above the max comfort temperature.";
        if (category == 0) {

            for (ReadingDTO rDTO : readingValues) {
                double temperature = getAreaAverageTemperature(rDTO.getDate(), house.getMotherArea(), house);
                if (categoryICalculusUS445(rDTO, temperature)) {
                    allReadings.add(rDTO);
                    outsideTempInDay.add(temperature);
                }
            }
            result = buildReadingDTOListOutputUS445(allReadings, outsideTempInDay);
        }
        if (category == 1) {
            for (ReadingDTO rDTO : readingValues) {
                double temperature = getAreaAverageTemperature(rDTO.getDate(), house.getMotherArea(), house);
                if (categoryIICalculusUS445(rDTO, temperature)) {
                    allReadings.add(rDTO);
                    outsideTempInDay.add(temperature);
                }
            }
            result = buildReadingDTOListOutputUS445(allReadings, outsideTempInDay);
        }
        if (category == 2) {
            for (ReadingDTO rDTO : readingValues) {
                double temperature = getAreaAverageTemperature(rDTO.getDate(), house.getMotherArea(), house);
                if (categoryIIICalculusUS445(rDTO, temperature)) {
                    allReadings.add(rDTO);
                    outsideTempInDay.add(temperature);
                }
            }
            result = buildReadingDTOListOutputUS445(allReadings, outsideTempInDay);
        }
        return result;
    }

    /**
     * This method creates a string which displays the instant where the temperature is not contained
     * within the comfort interval, the temperature for that instant and the difference with the
     * outside area average temperature for that day.
     *
     * @param list               contains the readings which raised above the comfort level.
     * @param outsideTemperature refers to the area average temperature for that day.
     * @return a String to be presented to the user.
     */
    private String buildReadingDTOListOutputUS445(List<ReadingDTO> list, List<Double> outsideTemperature) {
        StringBuilder result = new StringBuilder("Instants in which the readings are above comfort temperature:\n");
        for (int i = 0; i < list.size(); i++) {
            ReadingDTO readingDTO = list.get(i);
            result.append(i).append(") Instant: ").append(readingDTO.getDate()).append("\n");
            result.append(", Temperature value: ").append(readingDTO.getValue()).append("\n");
            result.append("Difference: + ").append(readingDTO.getValue() - outsideTemperature.get(i)).append(" Cº\n");
        }
        result.append("---\n");
        return result.toString();
    }

    // Auxiliary methods

    /**
     * This method asks the user to select the category wanted to calculate the comfort temperature interval.
     *
     * @return an int, respective to the category selected.
     */
    public int selectCategory() {
        String category = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select one of the following user comfort categories: ");
        System.out.println(0 + ") Category I");
        System.out.println(1 + ") Category II");
        System.out.println(2 + ") Category III");
        while (!("0".equalsIgnoreCase(category)) && !("1".equalsIgnoreCase(category)) && !("2".equalsIgnoreCase(category))) {
            System.out.println("Select a valid option");
            category = scanner.nextLine();
        }
        if ("0".equalsIgnoreCase(category)) {
            return 0;
        }
        if ("1".equalsIgnoreCase(category)) {
            return 1;
        }
        if ("2".equalsIgnoreCase(category)) {
            return 2;
        }
        return 0;
    }

    /**
     * This methods retrieves a List containing the readings (as DTOs) for a
     * determined room and time interval.
     *
     * @param roomService is used to gather the information from the database.
     * @return a list with the readingDTOs for the selected time interval.
     */
    public List<ReadingDTO> getAllReadingsInInterval(RoomService roomService) {
        System.out.println("Please select a room:");
        RoomDTO roomDTO = getRoomDTOByList(roomService);
        System.out.println("Please enter the starting date.");
        Date startDate = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Please enter the ending date.");
        Date endDate = DateUtils.getInputYearMonthDayHourMin();
        return getRoomTemperatureReadingsBetweenSelectedDates(roomDTO, startDate, endDate);
    }

    /**
     * This method calculates the average temperature in the house area in a given date.
     *
     * @param date           is used to determine the day in which we want to calculate the average.
     * @param geographicArea is used to determine the closest sensor to the house.
     * @param house          is used to determine the closest sensor within the geographical area
     * @return the average temperature value for the 24 hours of the given date.
     */
    private double getAreaAverageTemperature(Date date, GeographicArea geographicArea, House house) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date d1 = calendar.getTime(); // gets date at 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date d2 = calendar.getTime(); // gets date at 23:59:59

        // gets and returns average readings on the closest AreaSensor to the house
        AreaSensor houseClosestSensor = geographicArea.getClosestAreaSensorOfGivenType("temperature", house);
        return houseClosestSensor.getAverageReadingsBetweenDates(d1, d2);
    }

    /**
     * This method retrieves a list of readingDTOs respective to a given room and within
     * a given time interval.
     *
     * @param roomDTO     refers to the room which contains the readings.
     * @param initialDate is the beginning of the interval.
     * @param finalDate   is the ending of the interval.
     * @return a list containing the readings in that room for that time interval.
     */
    public List<ReadingDTO> getRoomTemperatureReadingsBetweenSelectedDates(RoomDTO roomDTO, Date initialDate, Date
            finalDate) {
        Room room = RoomMapper.dtoToObject(roomDTO);
        List<RoomSensor> temperatureSensors = room.getRoomSensorsOfGivenType("temperature");
        List<Reading> allReadings = new ArrayList<>();
        for (RoomSensor roomSensor : temperatureSensors) {
            allReadings.addAll(roomSensor.getReadings());
        }
        List<ReadingDTO> finalList = new ArrayList<>();
        for (Reading r : allReadings) {
            if (ReadingUtils.isReadingDateBetweenTwoDates(r.getDate(), initialDate, finalDate)) {
                finalList.add(ReadingMapper.objectToDTO(r));
            }
        }
        return finalList;
    }
}