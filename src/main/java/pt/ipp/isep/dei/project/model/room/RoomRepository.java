package pt.ipp.isep.dei.project.model.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.dto.mappers.ReadingMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMinimalMapper;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.repository.RoomCrudRepo;

import java.util.*;

/**
 * Class that groups a number of Rooms in a House.
 */
@Service
public class RoomRepository {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RoomRepository.class);
    private static final String STRING_BUILDER = "---------------\n";
    private static final String THE_READING = "The reading ";
    private static final String FROM = " from ";

    @Autowired
    private RoomCrudRepo roomCrudRepo;

    /**
     * This method receives a Room DTO and tries to edit the corresponding
     * room in repository with the given parameters.
     *
     * @param roomDTOMinimal that provides the parameters and room ID
     * @return true in case the room is edited, false in case a room with the given
     * ID does not exist in repository
     **/
    public boolean configureRoom(RoomDTOMinimal roomDTOMinimal) {
        String roomName = roomDTOMinimal.getName();
        int roomFloor = roomDTOMinimal.getFloor();
        double roomWidth = roomDTOMinimal.getWidth();
        double roomLength = roomDTOMinimal.getLength();
        double roomHeight = roomDTOMinimal.getHeight();

        Optional<Room> optionalRoom = roomCrudRepo.findByRoomName(roomName);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setHouseFloor(roomFloor);
            room.setRoomWidth(roomWidth);
            room.setRoomLength(roomLength);
            room.setRoomHeight(roomHeight);
            roomCrudRepo.save(room);
            return true;
        }
        return false;
    }

    /**
     * This method gets every room from the Room Crud Repository
     * and returns as an ArrayList.
     *
     * @return a list containing all rooms contained in repository
     **/
    public List<Room> getAllRooms() {
        List<Room> roomsAux = roomCrudRepo.findAll();
        if (roomsAux != null) {
            return roomsAux;
        }
        return new ArrayList<>();
    }

    /**
     * This method receives a roomDTOWeb and tries to the corresponding room
     * from repository (by using its ID).
     *
     * @param roomDTO to be deleted
     * @return true in case the corresponding room was deleted, false otherwise.
     **/
    public boolean deleteRoom(RoomDTOMinimal roomDTO) {
        String roomDTOName = roomDTO.getName();
        Optional<Room> room = roomCrudRepo.findByRoomName(roomDTOName);
        if (room.isPresent()) {
            // Manually reset the sensor list first, so the database doesn't have to delete all sensors.
            // Having the database be responsible for deleting sensors is extremely costly for performance.

            List<RoomSensor> emptyList = new ArrayList<>();
            room.get().setRoomSensors(emptyList);
            roomCrudRepo.delete(room.get());

            return true;
        }
        return false;
    }

    /**
     * This method gets every room from the Room Crud Repository
     * and returns as an ArrayList.
     *
     * @return a list containing all rooms contained in repository
     **/
    public List<RoomDTOMinimal> getAllRoomDTOMinimal() {
        List<RoomDTOMinimal> finalList = new ArrayList<>();
        List<Room> roomList = roomCrudRepo.findAll();
        if (roomList != null) {
            for (Room room : roomList) {
                RoomDTOMinimal roomDTOMinimal = RoomMinimalMapper.objectToDtoWeb(room);
                finalList.add(roomDTOMinimal);
            }
        }
        return finalList;
    }


    /**
     * Method to check if a room with the given ID exists in the repository.
     *
     * @param idToCheck is the id that we want to check for being present.
     * @return is true if a room with the given ID exists, false if it doesn't.
     */
    boolean idExists(String idToCheck) {
        for (Room r : roomCrudRepo.findAll()) {
            if (r.getId().equals(idToCheck)) {
                return true;
            }
        }
        return false;
    }


    public List<RoomDTOMinimal> getAllDTOWebInformation() {
        List<Room> list = roomCrudRepo.findAll();
        List<RoomDTOMinimal> finalList = new ArrayList<>();
        for (Room room : list) {
            RoomDTOMinimal gaDTO = RoomMinimalMapper.objectToDtoWeb(room);
            finalList.add(gaDTO);
        }
        return finalList;
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

    public Room createRoom(String roomDesignation, String roomDescription, int roomHouseFloor, List<Double> roomDimensions, String houseID) {
        for (Room r : getAllRooms()) {
            String designation = r.getId();
            if (roomDesignation.equals(designation)) {
                return r;
            }
        }
        return new Room(roomDesignation, roomDescription, roomHouseFloor, roomDimensions, houseID);
    }

    /**
     * Method that saveSensor a Room to the RoomRepository.
     * <p>
     * It is also adding to the local list while the project is being refactored an lists removed
     *
     * @param room is the room we want to saveSensor.
     */
    public boolean updateRoom(Room room) {
        roomCrudRepo.save(room);
        return true;
    }

    public boolean updateDTORoom(RoomDTO roomDTO) {
        roomCrudRepo.save(RoomMapper.dtoToObject(roomDTO));
        return true;
    }

    public boolean removeSensorDTO(RoomDTO roomDTO, String roomSensorID) {
        return roomDTO.removeSensor(roomSensorID);
    }

    /**
     * Method that removes a Room from the RoomList.
     *
     * @param room is the room we want to removeGeographicArea from the roomList.
     * @return true if room was successfully removed from the roomList, false otherwise.
     */
    public boolean removeRoom(Room room) {
        Optional<Room> aux = roomCrudRepo.findById(room.getId());
        if (aux.isPresent()) {
            roomCrudRepo.delete(aux.get());
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
        Optional<Room> aux = roomCrudRepo.findById(name);
        if (aux.isPresent()) {
            room = aux.get();
            return room;
        }
        throw new IndexOutOfBoundsException("ERROR: No Room was file with the following name: " + name + " .");
    }

    public RoomDTO getRoomDTOByName(String name) {
        Optional<Room> aux = roomCrudRepo.findById(name);
        if (!aux.isPresent()) {
            throw new IllegalArgumentException("ERROR: No Room was file with the following name: " + name + " .");
        }
        return RoomMapper.objectToDTO(aux.get());
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
        throw new NoSuchElementException("ERROR: There is no Room with that ID.");
    }

    /**
     * This method will receive a room and try to add the room
     * to the Room Crud repository in case it does not already exist.
     *
     * @param room to add to repository
     * @return true in case the room is added to repository, false otherwise.
     **/
    public boolean addRoomToCrudRepository(Room room) {
        List<Room> rooms = this.getAllRooms();
        String roomID = room.getId();
        for (Room r : rooms) {
            if (roomID.equals(r.getId())) {
                return false;
            }
        }
        this.roomCrudRepo.save(room);
        return true;
    }

    /**
     * This method will receive a room DTO Without any sensors or devices and try to add the
     * corresponding room to the repository in case it does not already exist.
     *
     * @param roomDTO to add to repository
     * @return true in case the room is added to repository, false otherwise.
     **/
    public boolean addRoomDTOWithoutSensorsAndDevicesToCrudRepository(RoomDTO roomDTO) {
        Room room = RoomMapper.dtoToObject(roomDTO);
        String roomID = room.getId();
        List<Room> rooms = this.getAllRooms();
        for (Room r : rooms) {
            if (roomID.equals(r.getId())) {
                return false;
            }
        }
        this.roomCrudRepo.save(room);
        return true;
    }

    /**
     * String Builder of the RoomList.
     *
     * @return a String of the Rooms in the RoomList.
     */
    public String buildSelectRoomsAsString(List<Room> selectedRooms) {
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
        Optional<Room> room1 = roomCrudRepo.findByRoomName(room.getId());
        if (room1.isPresent()) {
            return false;
        }
        roomCrudRepo.save(room);
        return true;
    }

    /**
     * This method receives an index as parameter and gets a room from room list.
     *
     * @param index the index of the room
     * @return returns room that corresponds to index.
     */
    Room getRoom(int index) {
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
        return roomCrudRepo.findById(idToFind);
    }

    /**
     * This method will receive a list of readings, a string of a path to a log file,
     * and a room service and will try to add readings to the given sensors
     * in the given room from the repository.
     *
     * @param readingDTOS a list of reading DTOs
     * @param logPath     M  string of a log file path
     * @return the number of readings added
     **/
    public int addReadingsToRoomSensors(List<ReadingDTO> readingDTOS, String logPath) {
        List<Reading> readings = ReadingMapper.readingDTOsToReadings(readingDTOS);
        int addedReadings = 0;
        List<String> sensorIds = ReadingUtils.getSensorIDs(readings);
        for (String sensorID : sensorIds) {
            List<Reading> subArray = ReadingUtils.getReadingsBySensorID(sensorID, readings);
            addedReadings += addRoomReadings(sensorID, subArray);
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
     * @return the number of readings added
     **/
    int addRoomReadings(String sensorID, List<Reading> readings) {
        int addedReadings = 0;
        try {
            Room room = getRoomContainingSensorWithGivenId(sensorID);
            RoomSensor roomSensor = room.getRoomSensorByID(sensorID);
            addedReadings = addReadingsToRoomSensor(roomSensor, readings);
            roomCrudRepo.save(room);
        } catch (IllegalArgumentException ill) {
            for (Reading r : readings) {
                logger.debug(THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " wasn't added because a sensor with the ID " + r.getSensorID() + " wasn't found.");
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
        List<Room> roomList = roomCrudRepo.findAll();
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
     * @return number of readings added to the Room Sensor
     **/
    int addReadingsToRoomSensor(RoomSensor roomSensor, List<Reading> readings) {
        int addedReadings = 0;
        for (Reading r : readings) {
            Date readingDate = r.getDate();
            if (roomSensor.readingWithGivenDateExists(readingDate)) {
                logger.debug(THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + roomSensor.getId() + " wasn't added because it already exists.");
            } else if (!roomSensor.activeDuringDate(readingDate)) {
                logger.debug(THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + roomSensor.getId() + " wasn't added because the reading is from before the sensor's starting date.");
            } else {
                roomSensor.addReading(r);
                addedReadings++;
            }
        }
        return addedReadings;
    }

    public List<Reading> getTemperatureReadingsBetweenDates(Date initialDate, Date finalDate, RoomDTO roomDTO) {
        Room room = RoomMapper.dtoToObject(roomDTO);
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

    public double getRoomMaxTempById(String roomId, Date date) {
        Room validRoom;
        Optional<Room> room = roomCrudRepo.findByRoomName(roomId);
        if (room.isPresent()) {
            validRoom = room.get();
            return validRoom.getMaxTemperatureOnGivenDay(date);
        } else {
            throw new IllegalArgumentException("There is no room with the given Id");
        }
    }

    /**
     * Method that gets current room temperature for US605
     *
     * @param roomId for identifying room.
     * @return the current room temperature as a double.
     */

    public double getCurrentRoomTempByRoomId(String roomId) {
        Room validRoom;
        Optional<Room> room = roomCrudRepo.findByRoomName(roomId);
        if (room.isPresent()) {
            validRoom = room.get();
            return validRoom.getCurrentRoomTemperature();
        } else {
            throw new IllegalArgumentException("There is no room with the given Id");
        }
    }

    public boolean addSensorDTO(RoomDTO roomDTO, RoomSensorDTO roomSensorDTO) {
        return roomDTO.addSensor(roomSensorDTO);
    }

    public boolean isRoomSensorDTOValid(RoomSensorDTO roomSensorDTO) {
        return (roomSensorDTO.getName() != null && roomSensorDTO.getSensorId() != null && roomSensorDTO.getType() != null && roomSensorDTO.getDateStartedFunctioning() != null);
    }
}


