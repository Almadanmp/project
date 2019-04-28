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

    private RoomDTO getRoomDTOByList(RoomService roomService) {
        List<Room> rooms = roomService.getAllRooms();
        Room room = InputHelperUI.getHouseRoomByList(roomService, rooms);
        return RoomMapper.objectToDTO(room);
    }

    public int getCategoryFromList() {
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


    public List<Date> categoryICalculusUS440(List<ReadingDTO> readingDTOList, Double houseTemperature) {
        double minT = 0.33 * houseTemperature + 18.8 - 2;
        List<Date> finalDates = new ArrayList<>();
        for (ReadingDTO r : readingDTOList) {
            if (r.getValue() < minT) {
                finalDates.add(r.getDate());
            }
        }
        return finalDates;
    }

    public List<Date> categoryIICalculusUS440(List<ReadingDTO> readingDTOList, Double houseTemperature) {
        double minT = 0.33 * houseTemperature + 18.8 - 3;
        List<Date> finalDates = new ArrayList<>();
        for (ReadingDTO r : readingDTOList) {
            if (r.getValue() < minT) {
                finalDates.add(r.getDate());
            }
        }
        return finalDates;
    }

    public List<Date> categoryIIICalculusUS440(List<ReadingDTO> readingDTOList, Double houseTemperature) {
        double minT = 0.33 * houseTemperature + 18.8 - 4;
        List<Date> finalDates = new ArrayList<>();
        for (ReadingDTO r : readingDTOList) {
            if (r.getValue() < minT) {
                finalDates.add(r.getDate());
            }
        }
        return finalDates;
    }

    private boolean categoryICalculusUS445(ReadingDTO readingDTO, Double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 2;
        return readingDTO.getValue() > minT;
    }

    private boolean categoryIICalculusUS445(ReadingDTO readingDTO, Double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 3;
        return readingDTO.getValue() > minT;
    }

    private boolean categoryIIICalculusUS445(ReadingDTO readingDTO, Double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 4;
        return readingDTO.getValue() > minT;
    }

    public List<ReadingDTO> getReadingValues(RoomService roomService) {
        System.out.println("Please select a room:");
        RoomDTO roomDTO = getRoomDTOByList(roomService);
        System.out.println("Please enter the starting date.");
        Date startDate = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Please enter the ending date.");
        Date endDate = DateUtils.getInputYearMonthDayHourMin();
        return getRoomTemperatureReadingsBetweenSelectedDates(roomDTO, startDate, endDate);
    }

    public String getDaysWithTemperaturesAboveComfortLevel(RoomService roomService, House house, int category) {
        List<ReadingDTO> readingValues = getReadingValues(roomService);
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
            result = buildReadingDTOListOutput(allReadings, outsideTempInDay);
        }
        if (category == 1) {
            for (ReadingDTO rDTO : readingValues) {
                double temperature = getAreaAverageTemperature(rDTO.getDate(), house.getMotherArea(), house);
                if (categoryIICalculusUS445(rDTO, temperature)) {
                    allReadings.add(rDTO);
                    outsideTempInDay.add(temperature);
                }
            }
            result = buildReadingDTOListOutput(allReadings, outsideTempInDay);
        }
        if (category == 2) {
            for (ReadingDTO rDTO : readingValues) {
                double temperature = getAreaAverageTemperature(rDTO.getDate(), house.getMotherArea(), house);
                if (categoryIIICalculusUS445(rDTO, temperature)) {
                    allReadings.add(rDTO);
                    outsideTempInDay.add(temperature);
                }
            }
            result = buildReadingDTOListOutput(allReadings, outsideTempInDay);
        }
        return result;
    }

    private List<ReadingDTO> getRoomTemperatureReadingsBetweenSelectedDates(RoomDTO roomDTO, Date initialDate, Date finalDate) {
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

    private String buildReadingDTOListOutput(List<ReadingDTO> list, List<Double> outsideTemperature) {
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

}