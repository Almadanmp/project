package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.ArrayList;
import java.util.List;

public final class HouseMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    private HouseMapper() {
    }

    /**
     * This is the method that converts House DTO into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static House dtoToObject(HouseDTO dtoToConvert) {
        // Update parameters

        String objectId = dtoToConvert.getId();

        Address objectAddress = AddressMapper.dtoToObject(dtoToConvert.getAddress());

        Local objectLocal = LocalMapper.dtoToObject(dtoToConvert.getLocation());

        EnergyGridService objectGridList = new EnergyGridService();
        for (EnergyGridDTO y : dtoToConvert.getEnergyGridList()) {
            EnergyGrid grid = EnergyGridMapper.dtoToObject(y);
            objectGridList.addGrid(grid);
        }

        RoomService objectRoomService = new RoomService();
        for (RoomDTO y : dtoToConvert.getRoomList()) {
            Room room = RoomMapper.dtoToObject(y);
            objectRoomService.add(room);
        }

        GeographicArea objectGeoArea = GeographicAreaMapper.dtoToObject(dtoToConvert.getMotherArea());

        int objectGridMeteringPeriod = dtoToConvert.getGridMeteringPeriod();

        int objectDeviceMeteringPeriod = dtoToConvert.getDeviceMeteringPeriod();

        //empty list
        List<String> objectDeviceTypeConfigList = new ArrayList<>();


        // Create, update and return new object

        House resultObject = new House(objectId, objectAddress, objectLocal, objectGridMeteringPeriod, objectDeviceMeteringPeriod, objectDeviceTypeConfigList);
        resultObject.setMotherArea(objectGeoArea);

        return resultObject;
    }

    public static House dtoToObjectUS100(HouseDTO dtoToConvert) {
        // Update parameters

        Address objectAddress = AddressMapper.dtoToObject(dtoToConvert.getAddress());

        EnergyGridService objectGridList = new EnergyGridService();
        for (EnergyGridDTO y : dtoToConvert.getEnergyGridList()) {
            EnergyGrid grid = EnergyGridMapper.dtoToObjectUS100(y);
            objectGridList.addGrid(grid);
        }

        RoomService objectRoomService = new RoomService();
        for (RoomDTO y : dtoToConvert.getRoomList()) {
            Room room = RoomMapper.dtoToObjectUS100(y);
            objectRoomService.add(room);
        }


        // Create, update and return new object

        House resultObject = new House();
        resultObject.setAddress(objectAddress);
        resultObject.setRoomService(objectRoomService);
        resultObject.setGridList(objectGridList);

        return resultObject;
    }


    /**
     * This is the method that converts House model objects into DTOs with the same data.
     *
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */
    public static HouseDTO objectToDTO(House objectToConvert) {
        String dtoName = objectToConvert.getId();

        AddressDTO dtoAddress = AddressMapper.objectToDTO(objectToConvert.getAddress());

        LocalDTO dtoLocal = LocalMapper.objectToDTO(objectToConvert.getLocation());

        List<EnergyGridDTO> dtoGridList = new ArrayList<>();
        for (int y = 0; y < objectToConvert.getGridList().gridsSize(); y++) {
            EnergyGrid energyGrid = objectToConvert.getGridList().get(y);
            dtoGridList.add(EnergyGridMapper.objectToDTO(energyGrid));
        }

        List<RoomDTO> dtoRoomList = new ArrayList<>();
        for(Room r: objectToConvert.getRoomService().getRooms()){
            dtoRoomList.add(RoomMapper.objectToDTO(r));
        }

        GeographicAreaDTO dtoGeoArea = GeographicAreaMapper.objectToDTO(objectToConvert.getMotherArea());

        int dtoGridMeteringPeriod = objectToConvert.getGridMeteringPeriod();

        int dtoDeviceMeteringPeriod = objectToConvert.getDeviceMeteringPeriod();

        //empty list
        List<DeviceType> objectDeviceTypeConfigList = new ArrayList<>();


        // Create, update and return new object

        HouseDTO resultObject = new HouseDTO();
        resultObject.setId(dtoName);
        resultObject.setAddress(dtoAddress);
        resultObject.setLocation(dtoLocal);
        resultObject.setMotherArea(dtoGeoArea);
        resultObject.setGridMeteringPeriod(dtoGridMeteringPeriod);
        resultObject.setDeviceMeteringPeriod(dtoDeviceMeteringPeriod);
        resultObject.setDeviceTypeList(objectDeviceTypeConfigList);

        return resultObject;
    }
}
