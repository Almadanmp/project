package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;

import java.util.List;
import java.util.Scanner;

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

    public void categoryICalculus(){
    }
    public void categoryIICalculus(){
    }
    public void categoryIIICalculus(){
    }
}