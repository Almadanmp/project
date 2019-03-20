package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Mapper {
    private RoomDTO roomDTO = new RoomDTO();
    private GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();
    private SensorDTO sensorDTO = new SensorDTO();
    private LocalDTO localDTO = new LocalDTO();

    /**
     * Method that transforms a model instantiation of Room into a DTO fit to be handled by the UIs.
     *
     * @param room is the room we want to convert into a DTO.
     * @return is a DTO whose stored data matches the one of the given room.
     */

    public RoomDTO roomToDTO(Room room) {
        roomDTO.setName(room.getRoomName());
        roomDTO.setFloor(room.getHouseFloor());
        roomDTO.setHeight(room.getRoomHeight());
        roomDTO.setLength(room.getRoomLength());
        roomDTO.setWidth(room.getRoomWidth());
        roomDTO.setId(room.getUniqueID());
        roomDTO.setSensorList(room.getSensorList());
        roomDTO.setDeviceList(room.getDeviceList());
        return roomDTO;
    }

    /**
     * Method that updates a room contained in a given house with the data contained in a given DTO. It matches the
     * DTO to the object through UUID.
     *
     * @param roomDTO is the DTO that contains the data we want to use to update the model object.
     * @param house   is the house that contains the room we want to update.
     * @return is the updated room if the update was successful, is null if it wasn't.
     */

    public Room updateHouseRoom(RoomDTO roomDTO, House house) {
        Room room = null;
        RoomList roomlist = house.getRoomList();
        for (Room r : roomlist.getRooms()) {
            if (roomDTO.getId().compareTo(r.getUniqueID()) == 0) {
                r.setRoomName(roomDTO.getName());
                r.setHouseFloor(roomDTO.getFloor());
                r.setRoomWidth(roomDTO.getWidth());
                r.setRoomLength(roomDTO.getLength());
                r.setRoomHeight(roomDTO.getHeight());
                r.setSensorList(roomDTO.getSensorList());
                r.setDeviceList(roomDTO.getDeviceList());
                room = r;
            }
        }
        return room;
    }

    /**
     * Method that converts a given Geographic Area DTO into a model object with the same data.
     *
     * @param geographicAreaDTO is the DTO that has the information we want to use for the conversion.
     * @return returns the geographic area created with the conversion.
     */

    public GeographicArea geographicAreaDTOToObject(GeographicAreaDTO geographicAreaDTO) {
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

    /**
     * Method that converts a given Geographic Area into a DTO object with the same data.
     *
     * @param geographicArea is the area we want to convert into a DTO.
     * @return is the newly created DTO object.
     */

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

    /**
     * Method that converts a given Sensor into a DTO with the same data.
     *
     * @param sensor is the Sensor we want to convert into a DTO.
     * @return is the newly created DTO.
     */

    public SensorDTO sensorToDTO(Sensor sensor) {
        sensorDTO.setName(sensor.getName());
        sensorDTO.setDateStartedFunctioning(sensor.getDateStartedFunctioning().toString());
        sensorDTO.setAltitude(sensor.getLocal().getAltitude());
        sensorDTO.setLongitude(sensor.getLocal().getLongitude());
        sensorDTO.setLatitude(sensor.getLocal().getLatitude());
        sensorDTO.setTypeSensor(sensor.getTypeSensor().getName());
        sensorDTO.setUniqueID(sensor.getUniqueID());
        sensorDTO.setActive(sensor.isActive());
        return sensorDTO;
    }

    /**
     * Method that converts a given Sensor DTO into a model object with the same data.
     *
     * @param sensorDTO is the DTO we want to convert.
     * @return is the newly created Sensor.
     */

    public Sensor sensorDTOToObject(SensorDTO sensorDTO) {
        Sensor sensorObject = new Sensor(sensorDTO.getId(), sensorDTO.getName(), new TypeSensor(sensorDTO.getTypeSensor()
                , sensorDTO.getUnits()), new Local(sensorDTO.getLatitude(), sensorDTO.getLongitude(), sensorDTO.getAltitude())
                , new Date());
        sensorObject.setterActive(sensorDTO.getActive());
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                Date date = pattern.parse(sensorDTO.getDateStartedFunctioning());
                sensorObject.setDateStartedFunctioning(date);
            } catch (ParseException c) {
                c.printStackTrace();
            }
        }
        sensorObject.setUniqueID(sensorDTO.getUniqueID());
        return sensorObject;
    }

    public LocalDTO localToDTO(Local local) {
        localDTO.setLatitude(local.getLatitude());
        localDTO.setLongitude(local.getLongitude());
        localDTO.setAltitude(local.getAltitude());
        localDTO.setId(local.getUniqueId());
        return localDTO;
    }


    public Local dtoToLocal(LocalDTO localDTO) {
        double localAltitude = localDTO.getAltitude();
        double localLatitude = localDTO.getLatitude();
        double localLongitude = localDTO.getLongitude();
        UUID localUUID = localDTO.getId();
        Local local = new Local(localLatitude, localLongitude, localAltitude);
        local.setUniqueId(localUUID);
        return local;
    }
}