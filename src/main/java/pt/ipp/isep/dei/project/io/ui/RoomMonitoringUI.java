package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseMonitoringController;
import pt.ipp.isep.dei.project.controller.RoomMonitoringController;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;


class RoomMonitoringUI {
    private RoomMonitoringController roomMonitoringController;
    private List<String> menuOptions;

    RoomMonitoringUI() {
        this.roomMonitoringController = new RoomMonitoringController();
        menuOptions = new ArrayList<>();
        menuOptions.add("Get the instants where the temperature fell bellow the comfort level in a given time interval and category (US440).");
        menuOptions.add("Get the instants where the temperature rose above the comfort level in a given time interval and category (US445).");
        menuOptions.add("Get Max Temperature in a room in a specific day (US610).");
        menuOptions.add("Get Current Temperature in a room. (US605).");
        menuOptions.add("(Return to main menu)");
    }

    void run(House house, RoomService roomService) {
        boolean activeInput = false;
        int option;
        System.out.println("--------------\n");
        System.out.println("Room Monitoring\n");
        System.out.println("--------------\n");
        while (!activeInput) {
            MenuFormatter.showMenu("Room Monitoring", menuOptions);
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    runUS440(roomService, house);
                    activeInput = true;
                    break;
                case 2:
                    runUS445(roomService, house);
                    activeInput = true;
                    break;
                case 3:
                    runUS610(roomService);
                    activeInput = true;
                    break;
                case 4:
                    runUS605(roomService);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(UtilsUI.INVALID_OPTION);
                    break;
            }
        }
    }

    /**
     * US440
     * As a Power User or as a Room Owner, I want to have a list of the instants in which the temperature fell below the
     * comfort level in a given time interval and category (annex A.2 of EN 15251).
     */
    private void runUS440(RoomService roomService, House house) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        if (!ctrl.isMotherAreaValid(house)) {
            return;
        }
        updateAndDisplayUS440(roomService, house);
    }

    private void updateAndDisplayUS440(RoomService roomService, House house) {
        System.out.println("Please select a room:");
        RoomDTO roomDTO = InputHelperUI.getRoomDTOByList(roomService);
        System.out.println("Please enter the starting date.");
        Date startDate = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Please enter the ending date.");
        Date endDate = DateUtils.getInputYearMonthDayHourMin();
        int category = UtilsUI.selectCategory();
        //List<ReadingDTO> readingValues = roomMonitoringController.getAllReadingsInInterval(roomDTO, startDate, endDate); //TODO André getAllReadingsInInterval is within Room Class
        //roomMonitoringController.displayTemperaturesBelowComfortLevel(readingValues, house, category);
    }

    /**
     * US445
     * As a Power User or as a Room Owner, I want to have a list of
     * the instants in which the temperature rose above the comfort
     * level in a given time interval and category (annex A.2 of EN 15251).
     */
    private void runUS445(RoomService roomService, House house) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        if (!ctrl.isMotherAreaValid(house)) {
            return;
        }
        System.out.println("Please select a room:");
        RoomDTO roomDTO = InputHelperUI.getRoomDTOByList(roomService);
        System.out.println("Please enter the starting date.");
        Date startDate = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Please enter the ending date.");
        Date endDate = DateUtils.getInputYearMonthDayHourMin();
        int category = UtilsUI.selectCategory();
        String result = roomMonitoringController.getInstantsAboveComfortInterval(house, category, roomDTO, startDate, endDate);
        System.out.println(result);
    }


    /**
     * US605 As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     */
    private void runUS605(RoomService roomService) {
        if (roomService.isEmptyRooms()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        List<Room> houseRooms = roomService.getAllRooms();
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(roomService, houseRooms);

        updateModelDisplayState605(room, roomService);

    }

    private void updateModelDisplayState605(RoomDTO room, RoomService roomService) {
        try {
            double currentTemp = roomMonitoringController.getCurrentRoomTemperature(room, roomService);
            out.println("The current temperature in the room " + roomMonitoringController.getRoomName(room, roomService) +
                    " is " + currentTemp + "°C.");
        } catch (IllegalArgumentException illegal) {
            System.out.println(illegal.getMessage());
        }

    }

    /**
     * US610 - Get Max Temperature in a room in a specific day - CARINA ALAS
     */
    private void runUS610(RoomService roomService) {
        if (roomService.isEmptyRooms()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        List<Room> houseRooms = roomService.getAllRooms();
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(roomService, houseRooms);

        Date date = DateUtils.getInputYearMonthDay();
        updateModel610(room, date, roomService);
    }

    private void updateModel610(RoomDTO room, Date date, RoomService roomService) {
        try {
            double temperature = roomMonitoringController.getDayMaxTemperature(room, date, roomService);
            String dateFormatted = DateUtils.formatDateNoTime(date);
            String message = "The maximum temperature in the room " + roomMonitoringController.getRoomName(room, roomService) +
                    " on the day " + dateFormatted + " was " + temperature + "°C.";
            System.out.println(message);
        } catch (IllegalArgumentException illegal) {
            System.out.println(illegal.getMessage());
        }
    }

    // Auxiliary methods
}
