package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllerCLI.HouseMonitoringController;
import pt.ipp.isep.dei.project.controllerCLI.RoomMonitoringController;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;


class RoomMonitoringUI {
    private final RoomMonitoringController roomMonitoringController;
    private final List<String> menuOptions;

    RoomMonitoringUI() {
        this.roomMonitoringController = new RoomMonitoringController();
        menuOptions = new ArrayList<>();
        menuOptions.add("Get the instants where the temperature fell bellow the comfort level in a given time interval and category (US440).");
        menuOptions.add("Get the instants where the temperature rose above the comfort level in a given time interval and category (US445).");
        menuOptions.add("Get Max Temperature in a room in a specific day (US610).");
        menuOptions.add("Get Current Temperature in a room. (US605).");
        menuOptions.add("(Return to main menu)");
    }

    void run(House house, RoomRepository roomRepository, GeographicAreaHouseService geographicAreaService) {
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
                    runUS440(roomRepository, house, geographicAreaService);
                    activeInput = true;
                    break;
                case 2:
                    runUS445(roomRepository, house, geographicAreaService);
                    activeInput = true;
                    break;
                case 3:
                    runUS610(roomRepository);
                    activeInput = true;
                    break;
                case 4:
                    runUS605(roomRepository);
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
    private void runUS440(RoomRepository roomRepository, House house, GeographicAreaHouseService geographicAreaService) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        if (!ctrl.isMotherAreaValid(house)) {
            return;
        }
        updateAndDisplayUS440(roomRepository, house, geographicAreaService);
    }

    private void updateAndDisplayUS440(RoomRepository roomRepository, House house, GeographicAreaHouseService geographicAreaHouseService) {
        System.out.println("Please select a room:");
        RoomDTO roomDTO = InputHelperUI.getRoomDTOByList(roomRepository);
        System.out.println("Please enter the starting date.");
        Date startDate = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Please enter the ending date.");
        Date endDate = DateUtils.getInputYearMonthDayHourMin();
        int category = UtilsUI.selectCategory();
        String result = roomMonitoringController.getInstantsBelowComfortInterval(house, category, roomDTO, startDate, endDate, roomRepository, geographicAreaHouseService);
        System.out.println(result);
    }

    /**
     * US445
     * As a Power User or as a Room Owner, I want to have a list of
     * the instants in which the temperature rose above the comfort
     * level in a given time interval and category (annex A.2 of EN 15251).
     */
    private void runUS445(RoomRepository roomRepository, House house, GeographicAreaHouseService geographicAreaService) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        if (!ctrl.isMotherAreaValid(house)) {
            return;
        }
        System.out.println("Please select a room:");
        RoomDTO roomDTO = InputHelperUI.getRoomDTOByList(roomRepository);
        System.out.println("Please enter the starting date.");
        Date startDate = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Please enter the ending date.");
        Date endDate = DateUtils.getInputYearMonthDayHourMin();
        int category = UtilsUI.selectCategory();
        String result = roomMonitoringController.getInstantsAboveComfortInterval(house, category, roomDTO, startDate, endDate, roomRepository, geographicAreaService);
        System.out.println(result);
    }


    /**
     * US605 As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     */
    private void runUS605(RoomRepository roomRepository) {
        if (roomRepository.isEmptyRooms()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        List<Room> houseRooms = roomRepository.getAllRooms();
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(roomRepository, houseRooms);

        updateModelDisplayState605(room, roomRepository);

    }

    private void updateModelDisplayState605(RoomDTO room, RoomRepository roomRepository) {
        try {
            double currentTemp = roomMonitoringController.getCurrentRoomTemperature(room, roomRepository);
            out.println("The current temperature in the room " + roomMonitoringController.getRoomName(room, roomRepository) +
                    " is " + currentTemp + "°C.");
        } catch (IllegalArgumentException illegal) {
            System.out.println(illegal.getMessage());
        } catch (RuntimeException ok) {
            System.out.println("The room you are trying to access doesn't exist in the database. Please try again.");
        }
    }

    /**
     * US610 - Get Max Temperature in a room in a specific day - CARINA ALAS
     */
    private void runUS610(RoomRepository roomRepository) {
        if (roomRepository.isEmptyRooms()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        List<Room> houseRooms = roomRepository.getAllRooms();
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(roomRepository, houseRooms);

        Date date = DateUtils.getInputYearMonthDay();
        updateModel610(room, date, roomRepository);
    }

    private void updateModel610(RoomDTO room, Date date, RoomRepository roomRepository) {
        try {
            double temperature = roomMonitoringController.getDayMaxTemperature(room, date, roomRepository);
            String dateFormatted = DateUtils.formatDateNoTime(date);
            String message = "The maximum temperature in the room " + roomMonitoringController.getRoomName(room, roomRepository) +
                    " on the day " + dateFormatted + " was " + temperature + "°C.";
            System.out.println(message);
        } catch (IllegalArgumentException illegal) {
            System.out.println(illegal.getMessage());
        } catch (RuntimeException ok) {
            System.out.println("The room you are trying to access doesn't exist in the database. Please try again.");
        }
    }

    // Auxiliary methods
}
