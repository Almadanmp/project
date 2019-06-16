package pt.ipp.isep.dei.project.io.ui.commandline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controller.controllercli.RoomMonitoringController;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

@Service
class RoomMonitoringUI {

    @Autowired
    private RoomMonitoringController roomMonitoringController;
    @Autowired
    private RoomRepository roomRepository;

    private final List<String> menuOptions = createMenu();

    private List<String> createMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Get the instants where the temperature fell bellow the comfort level in a given time interval and category (US440).");
        menu.add("Get the instants where the temperature rose above the comfort level in a given time interval and category (US445).");
        menu.add("Get Max Temperature in a room in a specific day (US610).");
        menu.add("Get Current Temperature in a room. (US605).");
        menu.add("(Return to main menu)");
        return menu;
    }

    void run(House house) {
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
                    runUS440(house);
                    activeInput = true;
                    break;
                case 2:
                    runUS445(house);
                    activeInput = true;
                    break;
                case 3:
                    runUS610();
                    activeInput = true;
                    break;
                case 4:
                    runUS605();
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
    private void runUS440(House house) {
        if (!roomMonitoringController.isMotherAreaValid(house)) {
            return;
        }
        updateAndDisplayUS440();
    }

    private void updateAndDisplayUS440() {
        RoomDTO roomDTO = us440and445RoomSetup();
        Date startDate = us440and445StartDateSetup();
        Date endDate = us440and445SEndingDateSetup();
        int category = UtilsUI.selectCategory();
        String result = roomMonitoringController.getInstantsBelowComfortInterval(category, roomDTO, startDate, endDate);
        System.out.println(result);
    }

    /**
     * US445
     * As a Power User or as a Room Owner, I want to have a list of
     * the instants in which the temperature rose above the comfort
     * level in a given time interval and category (annex A.2 of EN 15251).
     */
    private void runUS445(House house) {
        if (!roomMonitoringController.isMotherAreaValid(house)) {
            return;
        }
        RoomDTO roomDTO = us440and445RoomSetup();
        Date startDate = us440and445StartDateSetup();
        Date endDate = us440and445SEndingDateSetup();
        int category = UtilsUI.selectCategory();
        String result = roomMonitoringController.getInstantsAboveComfortInterval(category, roomDTO, startDate, endDate);
        System.out.println(result);
    }


    /**
     * US605 As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     */
    private void runUS605() {
        if (roomRepository.isEmptyRooms()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        List<Room> houseRooms = roomRepository.getAllRooms();
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(roomRepository, houseRooms);

        updateModelDisplayState605(room);

    }

    private void updateModelDisplayState605(RoomDTO room) {
        try {
            double currentTemp = roomMonitoringController.getCurrentRoomTemperature(room);
            out.println("The current temperature in the room " + roomMonitoringController.getRoomName(room) +
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
    private void runUS610() {
        if (roomRepository.isEmptyRooms()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        List<Room> houseRooms = roomRepository.getAllRooms();
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(roomRepository, houseRooms);

        Date date = DateUtils.getInputYearMonthDay();
        updateModel610(room, date);
    }

    private void updateModel610(RoomDTO room, Date date) {
        try {
            double temperature = roomMonitoringController.getDayMaxTemperature(room, date);
            String dateFormatted = DateUtils.formatDateNoTime(date);
            String message = "The maximum temperature in the room " + roomMonitoringController.getRoomName(room) +
                    " on the day " + dateFormatted + " was " + temperature + "°C.";
            System.out.println(message);
        } catch (IllegalArgumentException illegal) {
            System.out.println(illegal.getMessage());
        } catch (RuntimeException ok) {
            System.out.println("The room you are trying to access doesn't exist in the database. Please try again.");
        }
    }

    // Auxiliary methods

    private RoomDTO us440and445RoomSetup() {
        System.out.println("Please select a room:");
        return InputHelperUI.getRoomDTOByList(roomRepository);
    }

    private Date us440and445StartDateSetup() {
        System.out.println("Please enter the starting date.");
        return DateUtils.getInputYearMonthDayHourMin();
    }

    private Date us440and445SEndingDateSetup() {
        System.out.println("Please enter the ending date.");
        return DateUtils.getInputYearMonthDayHourMin();
    }

}
