package pt.ipp.isep.dei.project.model.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controller.utils.LogUtils;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.RoomSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that groups a number of Rooms in a House.
 */
@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomSensorRepository roomSensorRepository;

    @Autowired
    SensorTypeRepo sensorTypeRepo;

    private static final String STRING_BUILDER = "---------------\n";
    private static final String THE_READING = "The reading ";
    private static final String FROM = " from ";


    /**
     * RoomList() empty constructor that initializes an ArrayList of Rooms.
     */
    public RoomService(RoomRepository roomRepository, RoomSensorRepository roomSensorRepository, SensorTypeRepo sensorTypeRepo) {
        this.roomRepository = roomRepository;
        this.roomSensorRepository = roomSensorRepository;
        this.sensorTypeRepo = sensorTypeRepo;
    }


//TODO UPDATED METHODS

    public List<Room> getAllRooms() {
        List<Room> roomsAux = roomRepository.findAll();
        if (roomsAux != null) {
            return roomsAux;
        }
        return new ArrayList<>();
    }


    /**
     * Method to check if a room with the given ID exists in the repository.
     *
     * @param idToCheck is the id that we want to check for being present.
     * @return is true if a room with the given ID exists, false if it doesn't.
     */
    boolean idExists(String idToCheck) {
        for (Room r : roomRepository.findAll()) {
            if (r.getId().equals(idToCheck)) {
                return true;
            }
        }
        return false;
    }

    public List<Room> getAllByEnergyGridName(String energyGridName) {
        return roomRepository.findAllByEnergyGridId(energyGridName);
    }


    /**
     * String Builder of the RoomList.
     *
     * @return a String of the Rooms in the RoomList.
     */
    public String buildRoomsAsString(List<Room> rooms) {
        StringBuilder result = new StringBuilder(STRING_BUILDER);
        if (rooms.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (Room r : rooms) {
            result.append(r.getId()).append(") Description: ").append(r.getDescription()).append(" | ");
            result.append("House Floor: ").append(r.getFloor()).append(" | ");
            result.append("Width: ").append(r.getWidth()).append(" | ");
            result.append("Length: ").append(r.getLength()).append(" | ");
            result.append("Height: ").append(r.getHeight()).append("\n");

        }
        result.append(STRING_BUILDER);
        return result.toString();
    }

    /**
     * Method for creating a new room with all it's parameters,
     * the method checks the room name to see if it already exists before creating it
     *
     * @param roomDesignation room name
     * @param roomHouseFloor  floor of the house where room is located
     * @param roomDimensions  contains width, length and height, respectively.
     * @return new created room
     */

    public Room createRoom(String roomDesignation, String roomDescription, int roomHouseFloor, List<Double> roomDimensions, String houseID, String energyGridID) {
        for (Room r : getAllRooms()) {
            String designation = r.getId();
            if (roomDesignation.equals(designation)) {
                return r;
            }
        }
        return new Room(roomDesignation, roomDescription, roomHouseFloor, roomDimensions, houseID, energyGridID);
    }

    /**
     * Method that saveSensor a Room to the RoomRepository.
     * <p>
     * It is also adding to the local list while the project is being refactored an lists removed
     *
     * @param room is the room we want to saveSensor.
     * @return true if the room was successfully saved to the repository, false otherwise.
     */
    void updateRoom(Room room) {
        roomRepository.save(room);
    }

    /**
     * Method that removes a Room from the RoomList.
     *
     * @param room is the room we want to removeGeographicArea from the roomList.
     * @return true if room was successfully removed from the roomList, false otherwise.
     */
    public boolean removeRoom(Room room) {
        Optional<Room> aux = roomRepository.findById(room.getId());
        if (aux.isPresent()) {
            roomRepository.delete(room);
            return true;
        }
        return false;
    }

    /**
     * This method checks if room list is empty.
     *
     * @return true if list is empty, false otherwise.
     **/
    public boolean isEmptyRooms() {
        return getAllRooms().isEmpty();
    }

    /**
     * This method receives an index as parameter and gets a room from room list.
     *
     * @param name the name of the room
     * @return returns room that corresponds to index.
     */
    Room getRoomByName(String name) {
        Room room;
        Optional<Room> aux = roomRepository.findById(name);
        if (aux.isPresent()) {
            room = aux.get();
            return room;
        }
        throw new IndexOutOfBoundsException("ERROR: No Room was file with the following name: " + name + " .");
    }

    /**
     * Method that updates a room contained in a given house with the data contained in a given DTO. It matches the
     * DTO to the object through UUID.
     *
     * @param roomDTO is the DTO that contains the data we want to use to update the model object.
     * @return is the updated room if the update was successful, is null if it wasn't.
     */
    public Room updateHouseRoom(RoomDTO roomDTO) {
        List<Room> rooms = this.getAllRooms();
        for (Room r : rooms) {
            if (roomDTO.getName().compareTo(r.getId()) == 0) {
                r = RoomMapper.dtoToObject(roomDTO);
                return r;
            }
        }
        throw new RuntimeException();
    }

//TODO OLD METHODS

    /**
     * String Builder of the RoomList.
     *
     * @return a String of the Rooms in the RoomList.
     */
    public String buildselectRoomsAsString(List<Room> selectedRooms) {
        StringBuilder result = new StringBuilder(STRING_BUILDER);
        if (selectedRooms.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < selectedRooms.size(); i++) {
            Room aux = selectedRooms.get(i);
            result.append(i).append(") Designation: ").append(aux.getId()).append(" | ");
            result.append("Description: ").append(aux.getDescription()).append(" | ");
            result.append("House Floor: ").append(aux.getFloor()).append(" | ");
            result.append("Width: ").append(aux.getWidth()).append(" | ");
            result.append("Length: ").append(aux.getLength()).append(" | ");
            result.append("Height: ").append(aux.getHeight()).append("\n");

        }
        result.append(STRING_BUILDER);
        return result.toString();
    }

    /**
     * Method that returns a DeviceList with all the devices of the RoomList.
     *
     * @return a DeviceList of all the devices in the RoomList.
     */
    public DeviceList getDeviceList() {
        DeviceList finalList = new DeviceList();
        for (Room r : this.getAllRooms()) {
            finalList.appendListNoDuplicates(r.getDeviceList());
        }
        return finalList;
    }

    /**
     * Returns the daily estimate of the consumption of all devices of a given type, in all rooms on this list.
     *
     * @param deviceType the device type
     * @param time       represents a day in minutes
     * @return the sum of all daily estimate consumptions of that type
     */
    public double getDailyConsumptionByDeviceType(String deviceType, int time) {
        double result = 0;
        for (Room r : getAllRooms()) {
            result += r.getEstimateConsumptionOverTimeByDeviceType(deviceType, time);
        }
        return Math.floor(result * 10) / 10;
    }

    /**
     * Method accesses the sum of nominal powers of all devices connected to every room in room list.
     *
     * @return is the sum of nominal powers of all rooms.
     */

    public double getNominalPower() {
        double result = 0;
        for (Room r : getAllRooms()) {
            result += r.getNominalPower();
        }
        return result;
    }

    public boolean saveRoom(Room room) {
        Optional<Room> room1 = roomRepository.findByRoomName(room.getId());
        if (room1.isPresent()) {
            return false;
        }
        roomRepository.save(room);
        return true;
    }

    /**
     * This method receives an index as parameter and gets a room from room list.
     *
     * @param index the index of the room
     * @return returns room that corresponds to index.
     */
    public Room getRoom(int index) {
        if (this.getAllRooms().isEmpty()) {
            throw new IndexOutOfBoundsException("The room list is empty.");
        }
        return this.getAllRooms().get(index);
    }

    /**
     * Method that finds a given object in the repository by its ID
     *
     * @param idToFind is the ID that we want to look for.
     * @return is an Optional that either contains the object if it existed, or a null.
     */

    public Optional<Room> findRoomByID(String idToFind) {
        return roomRepository.findById(idToFind);
    }


    //Methods from RoomSensorService

    public void saveSensor(RoomSensor sensor) {
        Optional<SensorType> sensorType = sensorTypeRepo.findByName(sensor.getSensorType().getName());

        if (sensorType.isPresent()) {
            sensor.setSensorType(sensorType.get());
        } else {
            SensorType newSensorType = sensor.getSensorType();
            sensorTypeRepo.save(newSensorType);
            sensor.setSensorType(newSensorType);
        }
        this.roomSensorRepository.save(sensor);
    }

    public List<RoomSensor> getAllSensor() {
        return roomSensorRepository.findAll();
    }

    /**
     * Method to get a TypeSensor from the Repository through a given id
     *
     * @param name selected name
     * @return Type Sensor corresponding to the given id
     */
    public SensorType getTypeSensorByName(String name) {
        Optional<SensorType> value = sensorTypeRepo.findByName(name);
        if (value.isPresent()) {
            return value.get();
        }
        return null;
    }

    /**
     * Method to check if an instance of this class is equal to another object.
     * Necessary for adding rooms to list.
     *
     * @return is true if the object is a power source list with the same contents.
     */
    public RoomSensor createRoomSensor(String id, String name, SensorType sensorType, Date dateStartedFunctioning, String roomId) {
        sensorType = getTypeSensorByName(sensorType.getName());
        return new RoomSensor(id, name, sensorType, dateStartedFunctioning, roomId);
    }

    /**
     * This method will receive a list of readings, a string of a path to a log file,
     * and a room service and will try to add readings to the given sensors
     * in the given room from the repository.
     *
     * @param readings a list of reading DTOs
     * @param logPath  M  string of a log file path
     * @return the number of readings added
     **/
    public int addReadingsToRoomSensors(List<Reading> readings, String logPath) {
        Logger logger = LogUtils.getLogger("houseReadingsLogger", logPath, Level.FINE);
        int addedReadings = 0;
        List<String> sensorIds = ReadingUtils.getSensorIDs(readings);
        for (String sensorID : sensorIds) {
            List<Reading> subArray = ReadingUtils.getReadingsBySensorID(sensorID, readings);
            addedReadings += addRoomReadings(sensorID, subArray, logger);
        }
        return addedReadings;
    }

    /**
     * This method receives a String of a given sensor ID, a list of Readings and a Logger,
     * and tries to add the readings to the sensor with the given sensor ID. The sensor is in
     * the room fetched from the room repository.
     *
     * @param sensorID a string of the sensor ID
     * @param readings a list of readings to be added to the given sensor
     * @param logger   logger
     * @return the number of readings added
     **/
    public int addRoomReadings(String sensorID, List<Reading> readings, Logger logger) {
        int addedReadings = 0;
        try {
            Room room = getRoomContainingSensorWithGivenId(sensorID);
            RoomSensor roomSensor = room.getRoomSensorByID(sensorID);
            addedReadings = addReadingsToRoomSensor(roomSensor, readings, logger);
            roomRepository.save(room);
        } catch (IllegalArgumentException ill) {
            for (Reading r : readings) {
                logger.fine(THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " wasn't added because a sensor with the ID " + r.getSensorID() + " wasn't found.");
                LogUtils.closeHandlers(logger);
            }
        }
        return addedReadings;
    }

    /**
     * This method receives a string of a sensor ID and will look in the repository
     * for the room that contains the sensor with the given sensor ID.
     *
     * @param sensorID string of the sensor ID
     * @return the room that contains the sensor with the given ID
     **/
    Room getRoomContainingSensorWithGivenId(String sensorID) {
        List<Room> roomList = roomRepository.findAll();
        for (Room room : roomList) {
            List<RoomSensor> roomSensors = room.getRoomSensors();
            for (RoomSensor sensor : roomSensors) {
                String tempSensorID = sensor.getId();
                if (tempSensorID.equals(sensorID)) {
                    return room;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * This method receives an Room Sensor, a list of readings and a logger, tries to add the
     * readings to the given Room Sensor, returning the number of readings that were added.
     * The method will log every reading that wasn't added to the Room Sensor.
     *
     * @param roomSensor given Room Sensor
     * @param readings   list of readings to be added to the given Room Sensor
     * @param logger     logger
     * @return number of readings added to the Room Sensor
     **/
    int addReadingsToRoomSensor(RoomSensor roomSensor, List<Reading> readings, Logger logger) {
        int addedReadings = 0;
        for (Reading r : readings) {
            Date readingDate = r.getDate();
            if (roomSensor.readingWithGivenDateExists(readingDate)) {
                logger.fine(THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + roomSensor.getId() + " wasn't added because it already exists.");
                LogUtils.closeHandlers(logger);
            } else if (!roomSensor.activeDuringDate(readingDate)) {
                logger.fine(THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + roomSensor.getId() + " wasn't added because the reading is from before the sensor's starting date.");
                LogUtils.closeHandlers(logger);
            } else {
                roomSensor.addReading(r);
                addedReadings++;
            }
        }
        return addedReadings;
    }

    public List<Reading> getTemperatureReadingsBetweenDates(Date initialDate, Date finalDate, Room room) {
        List<RoomSensor> temperatureSensors = room.getRoomSensorsOfGivenType("temperature");
        List<Reading> allReadings = new ArrayList<>();
        for (RoomSensor roomSensor : temperatureSensors) {
            allReadings.addAll(roomSensor.getReadings());
        }
        List<Reading> finalList = new ArrayList<>();
        for (Reading r : allReadings) {
            if (ReadingUtils.isReadingDateBetweenTwoDates(r.getDate(), initialDate, finalDate)) {
                finalList.add(r);
            }
        }
        return finalList;
    }
}

