package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.dto.Mapper;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

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
    String invalidProgramList = "Invalid FixedTimeProgram List - The selected Device does not have any Programs defined.\n" + returningToMainMenu;

    boolean geographicAreaListIsValid(GeographicAreaList geographicAreaList) {
        return geographicAreaList != null && !geographicAreaList.isEmpty();
    }

    boolean houseRoomListIsValid(House house) {
        return !house.isRoomListEmpty();
    }

    boolean roomDTOSensorListIsValid(RoomDTO roomDTO, House house) {
        Mapper mapper = new Mapper();
        Room room = mapper.dtoToRoom(roomDTO, house);
        return room.getSensorList() != null && !room.isSensorListEmpty();
    }
    boolean roomSensorListIsValid(Room room){
        return room.getSensorList() != null && !room.isSensorListEmpty();
    }
    boolean houseGridListIsValid(House house) {
        return house.getGridList() != null && !house.isEnergyGridListEmpty();
    }

    boolean gridDeviceListIsValid(EnergyGrid energyGrid) {
        return energyGrid.getDeviceList() != null && !energyGrid.isDeviceListEmpty();
    }

    boolean gridRoomListIsValid(EnergyGrid energyGrid) {
        return energyGrid.getRoomList() != null && !energyGrid.isRoomListEmpty();
    }

    boolean typeAreaListIsValid(TypeAreaList list) {
        return list != null && !list.isEmpty();
    }

    boolean programListIsValid(ProgramList programList) {
        return programList != null && !programList.isEmpty();
    }

    boolean roomDTODeviceListIsValid(RoomDTO roomDTO, House house) {
        Mapper mapper = new Mapper();
        Room room = mapper.dtoToRoom(roomDTO,house);
        return room.getDeviceList() != null && !room.isDeviceListEmpty();
    }

    boolean roomDeviceListIsValid(Room room) {
        return room.getDeviceList() != null && !room.isDeviceListEmpty();
    }

    boolean deviceLogListIsValid(Device device) {
        return (!device.isLogListEmpty());
    }

    boolean typeSensorListIsValid(TypeSensorList typeSensorList) {
        return typeSensorList != null && !typeSensorList.isEmpty();
    }

    boolean geographicAreaSensorListIsValid(GeographicArea geographicArea) {
        return geographicArea.getSensorList() != null && !geographicArea.isSensorListEmpty();
    }

    boolean houseMotherAreaIsValid(House house) {
        return house.getMotherArea() != null;
    }
}
