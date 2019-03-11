package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseMonitoringController;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Reading;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.out;


public class HouseMonitoringUI {
    private HouseMonitoringController houseMonitoringcontroller;
    private String was = " was ";

    public HouseMonitoringUI() {
        this.houseMonitoringcontroller = new HouseMonitoringController();
    }

    void run(House programHouse) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        boolean activeInput = false;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Monitoring\n");
        System.out.println("--------------\n");
        while (!activeInput) {
            printOptionMessage();
            option = inputUtils.getInputAsInt();
            switch (option) {
                case 1:
                    runUS610(programHouse);
                    activeInput = true;
                    break;
                case 2:
                    runUS605(programHouse);
                    activeInput = true;
                    break;
                case 3:
                    runUS600(programHouse);
                    activeInput = true;
                    break;
                case 4:
                    runUS620(programHouse);
                    activeInput = true;
                    break;
                case 5:
                    runUS623(programHouse);
                    activeInput = true;
                    break;
                case 6:
                    runUS630(programHouse);
                    activeInput = true;
                    break;
                case 7:
                    runUS633(programHouse);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utilsUI.invalidOption);
                    break;
            }
        }
    }

    /**
     * US600
     * As a Regular User, I want to get the current temperature in the house area. If, in the
     * first element with temperature sensors of the hierarchy of geographical areas that
     * includes the house, there is more than one temperature sensor, the nearest one
     * should be used.
     */
    private void runUS600(House house) {
        UtilsUI utilsUI = new UtilsUI();
        GeographicArea motherArea = house.getMotherArea();
        if (!utilsUI.geographicAreaSensorListIsValid(motherArea)) {
            System.out.println(utilsUI.invalidSensorList);
            return;
        }
        updateModel600(house);
    }

    private void updateModel600(House house) {
        try {
            double currentTemp = houseMonitoringcontroller.getHouseAreaTemperature(house);
            System.out.println("The current temperature in the house area is: " + currentTemp + "°C.");
        } catch (IllegalArgumentException illegal) {
            System.out.println(illegal.getMessage());
        }

    }

    /**
     * US605 As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     */
    private void runUS605(House house) {
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.houseRoomListIsValid(house)) {
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        InputUtils inputUtils = new InputUtils();
        RoomDTO room = inputUtils.getHouseRoomDTOByList(house);
        if (!utilsUI.roomDTOSensorListIsValid(room, house)) {
            System.out.println(utilsUI.invalidSensorList);
            return;
        }
        updateModelDisplayState605(room, house);

    }

    private void updateModelDisplayState605(RoomDTO room, House house) {
        try {
            double currentTemp = houseMonitoringcontroller.getCurrentRoomTemperature(room, house);
            out.println("The current temperature in the room " + houseMonitoringcontroller.getRoomName(room, house) +
                    " is " + currentTemp + "°C.");
        } catch (IllegalArgumentException illegal) {
            System.out.println(illegal.getMessage());
        }

    }


    /**
     * US610 - Get Max Temperature in a room in a specific day - CARINA ALAS
     */
    private void runUS610(House house) {
        UtilsUI utilsUI = new UtilsUI();
        if (!(utilsUI.houseRoomListIsValid(house))) {
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        InputUtils inputUtils = new InputUtils();
        RoomDTO room = inputUtils.getHouseRoomDTOByList(house);
        if (!(utilsUI.roomDTOSensorListIsValid(room, house))) {
            System.out.println(utilsUI.invalidSensorList);
            return;
        }
        Date date = inputUtils.getInputYearMonthDay();
        updateModel610(room, date, house);
    }

    private void updateModel610(RoomDTO room, Date date, House house) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        try {
            double temperature = ctrl.getDayMaxTemperature(room, date, house);
            String message = "The maximum temperature in the room " + ctrl.getRoomName(room, house) +
                    " on the day " + date + was + temperature + "°C.";
            System.out.println(message);
        } catch (IllegalArgumentException illegal) {
            System.out.println(illegal.getMessage());
        }
    }


    /**
     * US620UI: As a Regular User, I want to get the total rainfall in the house area for a given day.
     */
    private void runUS620(House house) {
        UtilsUI utils = new UtilsUI();
        if (!utils.houseMotherAreaIsValid(house)) {
            System.out.println(utils.invalidMotherArea);
            return;
        }
        if (!utils.geographicAreaSensorListIsValid(house.getMotherArea())) {
            System.out.println(utils.invalidSensorList);
            return;
        }
        InputUtils inputUtils = new InputUtils();
        System.out.println("Please enter the desired date.");
        Date date = inputUtils.getInputYearMonthDay();
        updateAndDisplayModelUS620(house, date);
    }

    private void updateAndDisplayModelUS620(House house, Date date) {
        double result;
        try {
            result = houseMonitoringcontroller.getTotalRainfallOnGivenDay(house, date);
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        printResultMessageUS620(date, result);
    }

    private void printResultMessageUS620(Date date, double result) {
        System.out.println("The average rainfall on " + date + was + result + "%.");
    }


     /* US623: As a Regular User, I want to get the average daily rainfall in the house area for a
      given period (days), as it is needed to assess the garden’s watering needs.*/

    private void runUS623(House house) {
        UtilsUI utils = new UtilsUI();
        if (!utils.geographicAreaSensorListIsValid(house.getMotherArea())) {
            System.out.println(utils.invalidSensorList);
            return;
        }

        InputUtils inputUtils = new InputUtils();
        System.out.println("Please enter the start date.");
        Date startDate = inputUtils.getInputYearMonthDay();
        Date endDate = inputUtils.getInputYearMonthDay();
        System.out.println("Please enter the end date.");
        updateAndDisplayUS623(house, startDate, endDate);
    }

    private Date getInputStartDate() {
        InputUtils inputUtils = new InputUtils();
        System.out.println("Please enter the start date.");
        return inputUtils.getInputYearMonthDay();
    }

    private Date getInputEndDate() {
        InputUtils inputUtils = new InputUtils();
        System.out.println("Please enter the end date.");
        return inputUtils.getInputYearMonthDay();
    }

    private void updateAndDisplayUS623(House house, Date startDate, Date endDate) {
        double result623;
        try {
            result623 = houseMonitoringcontroller.getAverageRainfallInterval(house, startDate, endDate);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("The average rainfall between " + startDate + " and " + endDate + was
                + result623 + "%.");
    }

    /**
     * US630 : As a Regular User, I want to get the last coldest day (lower maximum temperature)
     * in the house area in a given period.
     */

    private void runUS630(House house) {
        UtilsUI utils = new UtilsUI();
        if (!utils.geographicAreaSensorListIsValid(house.getMotherArea())) {
            System.out.println(utils.invalidSensorList);
            return;
        }
        Date startDate = getInputStartDate();
        Date endDate = getInputEndDate();
        updateAndDisplayUS630(house, startDate, endDate);
    }

    private void updateAndDisplayUS630(House house, Date startDate, Date endDate) {
        Date dateResult630;
        double valueResult630;
        try {
            Reading reading = houseMonitoringcontroller.getLastColdestDayInInterval(house, startDate, endDate);
            dateResult630 = houseMonitoringcontroller.getLastColdestDayInIntervalDate(reading);
            valueResult630 = houseMonitoringcontroller.getLastColdestDayInIntervalValue(reading);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("The last coldest day between " + startDate + " and " + endDate + was
                + dateResult630 + " and it's maximum temperature" + was + valueResult630 + "ºC.");
    }

    /* US633:  As Regular User, I want to get the day with the highest temperature amplitude in the house area in a
    given period. */
    private void runUS633(House house) {
        UtilsUI utils = new UtilsUI();
        if (!utils.geographicAreaSensorListIsValid(house.getMotherArea())) {
            System.out.println(utils.invalidSensorList);
            return;
        }
        Date startDate = getInputStartDate();
        Date endDate = getInputEndDate();
        updateAndDisplayUS633(house, startDate, endDate);
    }

    private void updateAndDisplayUS633(House house, Date startDate, Date endDate) {
        Date resultDate633;
        double resultValue633;

        try {
            resultDate633 = houseMonitoringcontroller.getHighestTempAmplitudeDate(house, startDate, endDate);
            resultValue633 = houseMonitoringcontroller.getHighestTempAmplitudeValue(house, resultDate633);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateResultFormatted = formatter.format(resultDate633);
        System.out.println("The day with the highest temperature amplitude was " + dateResultFormatted + ", with a" +
                " temperature amplitude of " + resultValue633 + "ºC.");
    }

    private void printOptionMessage() {
        System.out.println("House Monitoring Options:\n");
        System.out.println("1) Get Max Temperature in a room in a specific day (US610).");
        System.out.println("2) Get Current Temperature in a room. (US605).");
        System.out.println("3) Get Current Temperature in a House Area. (US600)");
        System.out.println("4) Get The Total Rainfall on a specific day in a House Area. (US620)");
        System.out.println("5) Get The Average Rainfall on a day interval in a House Area. (US623)");
        System.out.println("6) Get the Last Coldest Day (lower maximum temperature) in the House" +
                " Area in a given period. (US630)");
        System.out.println("7) Get the day with the highest temperature amplitude in the House Area in a given period."
                + "(US633)");
        System.out.println("0) (Return to main menu)\n");
    }
}
