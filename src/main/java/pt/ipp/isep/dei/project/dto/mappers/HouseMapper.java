package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.AddressDTO;
import pt.ipp.isep.dei.project.dto.HouseDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;

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

        List<Room> objectRoomService = new ArrayList<>();
        for (RoomDTO y : dtoToConvert.getRoomList()) {
            Room room = RoomMapper.dtoToObject(y);
            objectRoomService.add(room);
        }

        Long objectGeoArea = dtoToConvert.getMotherAreaID();

        int objectGridMeteringPeriod = dtoToConvert.getGridMeteringPeriod();

        int objectDeviceMeteringPeriod = dtoToConvert.getDeviceMeteringPeriod();

        //empty list
        List<String> objectDeviceTypeConfigList = new ArrayList<>();


        // Create, update and return new object

        House resultObject = new House(objectId, objectAddress, objectLocal, objectGridMeteringPeriod, objectDeviceMeteringPeriod, objectDeviceTypeConfigList);
        resultObject.setMotherAreaID(objectGeoArea);

        return resultObject;
    }

    public static House dtoToObjectUS100(HouseDTO dtoToConvert) {
        // Update parameters

        Address objectAddress = AddressMapper.dtoToObject(dtoToConvert.getAddress());

        // Create, update and return new object

        House resultObject = new House();
        resultObject.setAddress(objectAddress);

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

        Long dtoGeoArea = objectToConvert.getMotherAreaID();

        int dtoGridMeteringPeriod = objectToConvert.getGridMeteringPeriod();

        int dtoDeviceMeteringPeriod = objectToConvert.getDeviceMeteringPeriod();

        //empty list
        List<DeviceType> objectDeviceTypeConfigList = new ArrayList<>();


        // Create, update and return new object

        HouseDTO resultObject = new HouseDTO();
        resultObject.setId(dtoName);
        resultObject.setAddress(dtoAddress);
        resultObject.setLocation(dtoLocal);
        resultObject.setMotherAreaID(dtoGeoArea);
        resultObject.setGridMeteringPeriod(dtoGridMeteringPeriod);
        resultObject.setDeviceMeteringPeriod(dtoDeviceMeteringPeriod);
        resultObject.setDeviceTypeList(objectDeviceTypeConfigList);

        return resultObject;
    }
}
