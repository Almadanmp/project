package pt.ipp.isep.dei.project.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import pt.ipp.isep.dei.project.dto.HouseSensorDTO;
import pt.ipp.isep.dei.project.dto.mappers.HouseSensorMapper;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.HouseSensor;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.reader.JSONSensorsReader;
import pt.ipp.isep.dei.project.reader.ReaderJSONReadings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller class for House Configuration UI
 */


public class HouseConfigurationController {


    /* USER STORY 101 - As an Administrator, I want to configure the location of the house */

    /**
     * Sets the address of a given House.
     *
     * @param street  the house's street.
     * @param number  the house's number.
     * @param town    The house's town.
     * @param zip     The house's zip code.
     * @param country The houses's country.
     * @param house   is the house we're going to set the address of.
     */

    void setHouseAddress(String street, String number, String zip, String town, String country, House house) {
        Address address = new Address(street, number, zip, town, country);
        house.setAddress(address);
    }

    /**
     * @param latitude  is the latitude we're going to set.
     * @param longitude is the longitude we're going to set.
     * @param altitude  is the altitude we're going to set.
     * @param house     is the house we're going to change the zip code to.
     */

    public void setHouseLocal(double latitude, double longitude, double altitude, House house) {
        house.setLocation(latitude, longitude, altitude);
    }

    /**
     * This method receives a house and a geographic area and sets this as
     * the house mother area.
     **/
    public void setHouseMotherArea(House house, GeographicArea geographicArea) {
        house.setMotherArea(geographicArea);
    }

    /**
     * @param house is the house we want to get the name from.
     * @return is the name of the given house.
     */

    String getHouseId(House house) {
        return house.getId();
    }


    /* USER STORY 105 - As an Administrator, I want to addWithoutPersisting a new room to the house, in order to configure it (name,
     house floor and dimensions) */

    /**
     * This method receives the house and room parameters and creates a new roomDTO.
     *
     * @param house           house that will create the room
     * @param roomDesignation is the name of the room we're going to create.
     * @param roomHouseFloor  is the floor of the room we're going to create.
     * @param width           is the width of the room we're going to create.
     * @param length          is the length of the room we're going to create.
     * @param height          is the height of the room we're going to create.
     * @return a new Room
     */
    public Room createNewRoom(House house, String roomDesignation, String roomDescription, int roomHouseFloor, double width, double length, double height, String houseID, String energyGridID) {
        return house.createRoom(roomDesignation, roomDescription, roomHouseFloor, width, length, height, houseID, energyGridID);
    }

    /**
     * The method receives a house and a roomDTO, transforms it into a room, and tries to addWithoutPersisting it to house.
     *
     * @param house the project House.
     * @param room  the DTO of a Room.
     * @return true if room was added, false otherwise.
     **/
    public boolean addRoomToHouse(House house, Room room) {
        return house.addRoom(room);
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it. */

    /**
     * @param house is the house.
     * @return builds a string of all the individual members in the given list.
     */

    public String buildRoomsString(House house) {
        return house.buildRoomListString();
    }

    /* USER STORY 260 -  As an Administrator, I want to import a list of sensors for the house rooms.
       Sensors without a valid room shouldn’t be imported but registered in the application log. */


    public int readSensors(String filepath, RoomService roomRepository, HouseSensorService sensorRepository) {
        // Initialize needed variables.
        JSONSensorsReader reader = new JSONSensorsReader();
        int addedSensors = 0;
        if (roomRepository.isEmpty()) { // If there are no rooms to add sensors to, no sensors will be added.
            return addedSensors;
        }
        List<HouseSensorDTO> importedSensors = reader.importSensors(filepath);
        return addSensorsToModelRooms(importedSensors, roomRepository, sensorRepository);
    }

    private int addSensorsToModelRooms(List<HouseSensorDTO> importedSensors, RoomService roomRepository, HouseSensorService
            sensorRepository) {
        int addedSensors = 0;
        for (HouseSensorDTO importedSensor : importedSensors) {
            Optional<Room> roomToAddTo = roomRepository.findByID(importedSensor.getRoomID()); // Attempts to get a room in the repository with an ID that matches the sensor.
            if (roomToAddTo.isPresent()) { // If the room with the proper id exists, the sensor is saved.
                sensorRepository.save(HouseSensorMapper.dtoToObject(importedSensor));
                addedSensors++;
            }
        }
        return addedSensors;
    }

        /*
        US265 As an Administrator, I want to import a list of sensor readings of the house sensors.
        Data from non-existing sensors or outside the valid sensor operation period shouldn’t be imported but
        registered in the application log.
     */

    public int readReadingListFromFile(ReadingService readingService, String filePath, HouseService house, String logPath) {
        //  AreaSensorService aSService = new AreaSensorService();
        House programHouse = house.getHouse();
        ReaderJSONReadings reader = new ReaderJSONReadings();
        int addedReadings = 0;
        RoomService houseRooms = programHouse.getRoomService();
        // if (roomList.isEmpty()) {
        //   return addedReadings;
        //}
        JSONArray importedReadingList = reader.readFile(filePath);
        //  ReaderController rCtrl = new ReaderController(aSService);
    /*    try {
            Logger logger = Logger.getLogger(ReaderController.class.getName());
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            addedReadings = rCtrl.parseAndLogJSONReadings(importedReadingList, logger);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        } */
        //  String sensorID;
        for (int i = 0; i < importedReadingList.length(); i++) {
            JSONObject readingToImport = importedReadingList.getJSONObject(i);
    /*        try {
                sensorID = readingToImport.getString("id");
            } catch (NullPointerException ok) {
                continue;
            }*/
            try {
                String readingSensorID = readingToImport.getString("id");
                String readingDate = readingToImport.getString("timestamp/date");
                String readingValue = readingToImport.getString("value");
                double doubleReadingValue = Double.parseDouble(readingValue);
                String readingUnit = readingToImport.getString("unit");
                Date objectDate = null;
                List<SimpleDateFormat> simpleDateArray = knownDatePatterns();
                for (SimpleDateFormat pattern : simpleDateArray) {
                    try {
                        objectDate = pattern.parse(readingDate);
                    } catch (ParseException c) {
                        c.getErrorOffset();
                    }
                }
                Reading importedReading = new Reading(doubleReadingValue, objectDate, readingUnit, readingSensorID);
                HouseSensor[] sensors;
                for (Room r : houseRooms.getRooms()) {
                    sensors = r.getSensorList().getElementsAsArray();
                    for (HouseSensor sensor : sensors) {
                        String sensorID = importedReading.getId();
                        if (sensor.getId().equals(sensorID)) {
                            readingService.addReadingToMatchingSensor(readingSensorID, doubleReadingValue, objectDate, readingUnit);
                            addedReadings++;
                        }
                    }
                }
            } catch (NullPointerException ok) {

            }
        }
        return addedReadings;
    }

    public List<SimpleDateFormat> knownDatePatterns() {
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat("dd-MM-yyyy", new Locale("en", "US")));
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "US")));
        knownPatterns.add(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", new Locale("en", "US")));
        return knownPatterns;
    }
}