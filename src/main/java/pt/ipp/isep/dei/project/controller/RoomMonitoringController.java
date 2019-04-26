package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.HouseDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;

import java.util.*;

/**
 * Controller class for Room Monitoring UI
 */


public class RoomMonitoringController {

    public RoomDTO getRoomDTOByList(RoomService roomService) {
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

    public void categoryICalculus() {
    }

    public void categoryIICalculus() {
    }

    public void categoryIIICalculus() {
    }

    public GeographicArea getGeographicAreaFromDTO(GeographicAreaDTO geographicAreaDTO) {
        return GeographicAreaMapper.dtoToObject(geographicAreaDTO);
    }

    public House getHouseFromDTO(HouseDTO houseDTO) {
        return HouseMapper.dtoToObject(houseDTO);
    }

    public double getAreaAverageTemperature(Date date, GeographicAreaDTO geographicAreaDTO, HouseDTO houseDTO) {
        // converts DTOs to objects
        GeographicArea geographicArea = getGeographicAreaFromDTO(geographicAreaDTO);
        House house = getHouseFromDTO(houseDTO);

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