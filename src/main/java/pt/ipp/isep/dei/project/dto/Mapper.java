package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Mapper {
    private RoomDTO roomDTO = new RoomDTO();
    private GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();
    private SensorDTO sensorDTO = new SensorDTO();

    public RoomDTO roomToDTO(Room room) {
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setHouseFloor(room.getHouseFloor());
        roomDTO.setRoomHeight(room.getRoomHeight());
        roomDTO.setRoomLength(room.getRoomLength());
        roomDTO.setRoomWidth(room.getRoomWidth());
        roomDTO.setId(room.getUniqueID());
        roomDTO.setRoomSensorList(room.getSensorList());
        roomDTO.setDeviceList(room.getDeviceList());
        return roomDTO;
    }

    public Room dtoToRoom(RoomDTO roomDTO, House house) {
        Room room = null;
        RoomList roomlist = house.getRoomList();
        for (Room r : roomlist.getRooms()) {
            if (roomDTO.getId().compareTo(r.getUniqueID()) == 0) {
                r.setRoomName(roomDTO.getRoomName());
                r.setHouseFloor(roomDTO.getHouseFloor());
                r.setRoomWidth(roomDTO.getRoomWidth());
                r.setRoomLength(roomDTO.getRoomLength());
                r.setRoomHeight(roomDTO.getRoomHeight());
                r.setSensorList(roomDTO.getRoomSensorList());
                r.setDeviceList(roomDTO.getDeviceList());
                room = r;
            }
        }
        return room;
    }

    public GeographicArea createGeographicAreaFromDTO(GeographicAreaDTO geographicAreaDTO) {
        GeographicArea geographicArea = new GeographicArea(geographicAreaDTO.getId(), new TypeArea(geographicAreaDTO.getTypeArea()), geographicAreaDTO.getLength(),
                geographicAreaDTO.getWidth(), new Local(geographicAreaDTO.getLatitude(), geographicAreaDTO.getLongitude(),
                geographicAreaDTO.getAltitude()));
        SensorList sensorList = new SensorList();
        for (SensorDTO sensorsDTO : geographicAreaDTO.getSensorDTOList()) {
            Sensor sensor = sensorDTOToObject(sensorsDTO);
            sensorList.add(sensor);
        }
        geographicArea.setAreaSensors(sensorList);
        geographicArea.setDescription(geographicAreaDTO.getDescription());
        geographicArea.setUniqueId(geographicAreaDTO.getUniqueId());
        return geographicArea;
    }

    public GeographicAreaDTO geographicAreaToDTO(GeographicArea geographicArea) {
        List<SensorDTO> listSensorDTO = new ArrayList<>();
        geographicAreaDTO.setId(geographicArea.getId());
        geographicAreaDTO.setTypeArea(geographicArea.getTypeArea().getName());
        geographicAreaDTO.setLength(geographicArea.getLength());
        geographicAreaDTO.setWidth(geographicArea.getWidth());
        geographicAreaDTO.setLatitude(geographicArea.getLocation().getLatitude());
        geographicAreaDTO.setLongitude(geographicArea.getLocation().getLongitude());
        geographicAreaDTO.setAltitude(geographicArea.getLocation().getAltitude());
        for (Sensor s : geographicArea.getAreaSensors().getSensors()) {
            SensorDTO sensorsDTO = sensorToDTO(s);
            listSensorDTO.add(sensorsDTO);
        }
        geographicAreaDTO.setSensorDTOList(listSensorDTO);
        geographicAreaDTO.setDescription(geographicArea.getDescription());
        geographicAreaDTO.setUniqueId(geographicArea.getUniqueID());
        return geographicAreaDTO;
    }

    public SensorDTO sensorToDTO(Sensor sensor) {
        sensorDTO.setName(sensor.getName());
        sensorDTO.setDateStartedFunctioning(sensor.getDateStartedFunctioning().toString());
        sensorDTO.setAltitude(sensor.getLocal().getAltitude());
        sensorDTO.setLongitude(sensor.getLocal().getLongitude());
        sensorDTO.setLatitude(sensor.getLocal().getLatitude());
        sensorDTO.setTypeSensor(sensor.getTypeSensor().getName());
        sensorDTO.setUniqueID(sensor.getUniqueID());
        return sensorDTO;
    }

    private Sensor sensorDTOToObject(SensorDTO sensorDTO) {
        Sensor sensorObject = new Sensor(sensorDTO.getId(), sensorDTO.getName(), new TypeSensor(sensorDTO.getTypeSensor()
                , sensorDTO.getUnits()), new Local(sensorDTO.getLatitude(), sensorDTO.getLongitude(), sensorDTO.getAltitude())
                , new Date());
        SimpleDateFormat validDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = validDateFormat.parse(sensorDTO.getDateStartedFunctioning());
            sensorObject.setDateStartedFunctioning(date);
        } catch (ParseException c) {
            c.printStackTrace();
        }
        sensorObject.setUniqueID(sensorDTO.getUniqueID());
        return sensorObject;
    }
}