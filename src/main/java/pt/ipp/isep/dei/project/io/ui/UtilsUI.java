package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.devices.Device;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

/**
 * Utility class that aggregates common methods used by the UI classes.
 */
public class UtilsUI {

    String invalidOption = "Please enter a valid option.";
    private String returningToMainMenu = "-- Returning to main menu -- \n";
    String invalidRoomList = "Invalid Room List - List is empty.\n" + returningToMainMenu;
    String invalidGridList = "Invalid Grid List - List is empty.\n" + returningToMainMenu;
    String invalidDeviceList = "Invalid Device List - List is empty.\n" + returningToMainMenu;
    String invalidGAList = "Invalid Geographic Area List - List is empty.\n" + returningToMainMenu;
    String invalidGATypeList = "Invalid Geographic Area Type List - List is empty.\n" + returningToMainMenu;
    String invalidSensorList = "Invalid Sensor List - List is empty.\n" + returningToMainMenu;
    String invalidTypeSensorList = "Invalid Type Sensor List - List is empty.\n" + returningToMainMenu;
    String invalidMotherArea = "The selected House does not have a Geographical Area defined.\n" + returningToMainMenu;

    boolean geographicAreaListIsValid(GeographicAreaList geographicAreaList) {
        return geographicAreaList != null && !geographicAreaList.getGeographicAreaList().isEmpty();
    }

    boolean houseRoomListIsValid(House house) {
        return house.getRoomList() != null && !house.getRoomList().isEmpty();
    }

    boolean roomSensorListIsValid(Room room) {
        return room.getSensorList() != null && !room.getSensorList().getSensorList().isEmpty();
    }

    boolean houseDeviceListValid(House house) {
        return house.getDeviceList() != null && !house.getRoomList().isEmpty();
    }


    boolean houseGridListIsValid(House house) {
        return house.getEGListObject() != null && !house.getEGListObject().getEnergyGridList().isEmpty();
    }

    boolean gridDeviceListIsValid(EnergyGrid energyGrid) {
        return energyGrid.getDeviceList() != null && !energyGrid.getDeviceList().isEmpty();
    }

    boolean gridRoomListIsValid(EnergyGrid energyGrid) {
        return energyGrid.getListOfRooms() != null && !energyGrid.getListOfRooms().getList().isEmpty();
    }

    boolean typeAreaListIsValid(TypeAreaList list) {
        return list != null && !list.getTypeAreaList().isEmpty();
    }

    boolean programListIsValid(ProgramList programList) {
        return programList != null && !programList.getProgramList().isEmpty();
    }

    boolean roomDeviceListIsValid(Room room) {
        return room.getDeviceList() != null && !room.getDeviceList().isEmpty();
    }

    boolean deviceLogListIsValid(Device device) {
        return (!device.getLogList().isEmpty());
    }

    boolean typeSensorListIsValid(TypeSensorList typeSensorList) {
        return typeSensorList != null && !typeSensorList.getTypeSensorList().isEmpty();
    }

    boolean geographicAreaSensorListIsValid(GeographicArea geographicArea) {
        return geographicArea.getSensorList() != null && !geographicArea.getSensorList().getSensorList().isEmpty();
    }

    boolean houseMotherAreaIsValid(House house) {
        return house.getMotherArea() != null;
    }
}
