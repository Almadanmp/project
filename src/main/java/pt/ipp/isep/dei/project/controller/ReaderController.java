package pt.ipp.isep.dei.project.controller;

import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.controller.utils.LogUtils;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.*;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.dto.mappers.EnergyGridMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridService;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.reader.GeographicAreaReaderJSON;
import pt.ipp.isep.dei.project.reader.ReaderJSONGeographicAreas;
import pt.ipp.isep.dei.project.reader.ReaderJSONHouse;
import pt.ipp.isep.dei.project.reader.ReaderXMLGeoArea;
import pt.ipp.isep.dei.project.repository.HouseRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderController {

    public ReaderController() {

    }

    //
    // USER STORY 15v2 - As an Administrator, I want to import geographical areas and sensors from a JSON or XML file.

    /**
     * This method only accepts a path that ends with .json or .xml
     *
     * @param filePath - the path to the file if it exists
     * @param list     - the geographic area list
     * @return - number of geoareas imported
     */
    public int acceptPath(String filePath, GeographicAreaService list) {
        int areasRead;
        if (filePath.endsWith(".json")) {
            ReaderJSONGeographicAreas readerJSON = new ReaderJSONGeographicAreas();
            areasRead = readerJSON.readJSONFileAndAddGeoAreas(filePath, list);
            return areasRead;
        }
        if (filePath.endsWith(".xml")) {
            ReaderXMLGeoArea readerXML = new ReaderXMLGeoArea();
            areasRead = readerXML.readFileXMLAndAddAreas(filePath, list);
            return areasRead;
        }
        return -1;
    }

    /**
     * This method reads a JSON file that represents the class House() and sets House attributes(US100 Attributes)
     * from the file and saves it into the repository.
     *
     * @param filePath is the file path.
     * @param house    is the House that this method receives from the MainUI(), with houseRepository,
     *                 gridMeteringPeriod, deviceMeteringPeriod and deviceTypeConfig.
     * @return true if the House was successfully saved in the repository, false otherwise.
     */
    public boolean readJSONAndDefineHouse(House house, String filePath, EnergyGridService energyGridService, HouseRepository houseRepository, RoomService roomService) {
        //House
        ReaderJSONHouse readerJSONHouse = new ReaderJSONHouse();
        HouseDTO houseDTO;
        try {
            houseDTO = readerJSONHouse.readFile(filePath);
            House house2 = HouseMapper.dtoToObjectUS100(houseDTO);
            house.setAddress(house2.getAddress());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException();
        }

        //EnergyGrid

        List<EnergyGridDTO> gridDTOS = readerJSONHouse.readGridsJSON();
        for (EnergyGridDTO eg : gridDTOS) {
            EnergyGrid energyGrid = EnergyGridMapper.dtoToObjectUS100(eg);
            energyGrid.setHouseId(house.getId());
            energyGridService.addGrid(energyGrid);
        }

        //ROOMS
        for (EnergyGridDTO eg : gridDTOS) {
            List<RoomDTO> roomDTOS = eg.getRoomDTOS();
            for (RoomDTO rt : roomDTOS) {
                rt.setEnergyGridName(eg.getName());
                rt.setHouseId(house.getId());
                Room aux = RoomMapper.dtoToObjectUS100(rt);
                roomService.saveRoom(aux);
            }
        }
        houseRepository.save(house);
        return true;
    }

    /**
     * This method receives a list of Geographic Areas to add the given NodeList correspondent to the Geographic Areas
     * imported from the XML File.
     *
     * @param nListGeoArea          - NodeList imported from the XML.
     * @param geographicAreaService - list to which we want to add and persist the Geographic areas.
     * @return - the number of geographic areas imported.
     */
    public int addGeoAreaNodeListToList(NodeList nListGeoArea, GeographicAreaService geographicAreaService) {
        ReaderXMLGeoArea readerXML = new ReaderXMLGeoArea();
        int result = 0;
        for (int i = 0; i < nListGeoArea.getLength(); i++) {
            if (readerXML.readGeographicAreasXML(nListGeoArea.item(i), geographicAreaService)) {
                result++;
            }
        }
        return result;
    }

    /**
     * This is the method that reads geographic areas fromJSON files and return a list of
     * geographic areas DTO
     *
     * @param filePath - the path to the file
     * @return - a list of geo areas dto
     */
    public List<GeographicAreaDTO> readFileJSONGeoAreas(String filePath) {
        GeographicAreaReaderJSON readerJSON = new GeographicAreaReaderJSON();
        return readerJSON.readFile(filePath);
    }


    /**
     * This method adds the dtos to the geographic area service
     *
     * @param geographicAreaDTOS - the dtos imported from the file
     * @param list               - the service we want to add the dtos to
     * @return - number of areas added
     */
    public int addGeoAreasDTOToList(List<GeographicAreaDTO> geographicAreaDTOS, GeographicAreaService list) {
        int counter = 0;
        Logger logger = LogUtils.getLogger("areaTypeLogger", "resources/logs/areaTypeLogHtml.html", Level.FINE);
        for (GeographicAreaDTO dto : geographicAreaDTOS) {
            GeographicArea geoArea = GeographicAreaMapper.dtoToObject(dto);
            String type = geoArea.getAreaType().getName();
            AreaType areaType = list.getAreaTypeByName(type, logger);
            geoArea.setAreaType(areaType);
            list.addAndPersistGA(geoArea);
            counter++;
        }
        LogUtils.closeHandlers(logger);
        return counter;
    }

    /**
     * This is the method that reads geographic area sensors from JSON files and return a list of
     * area sensor DTO
     *
     * @param filePath - the path to the file
     * @return - a list of geo areas dto
     */
    public List<AreaSensorDTO> readFileJSONAreaSensors(String filePath) {
        GeographicAreaReaderJSON readerJSON = new GeographicAreaReaderJSON();
        return readerJSON.readAreaSensorDTOS(filePath);
    }

    /**
     * This method will receive a list of reading DTOs, a string of a path to a log file,
     * and a geographic area service and will try to add readings to the given sensors
     * in the given geographic area from the repository.
     *
     * @param readingDTOS           a list of reading DTOs
     * @param logPath               string of a log file path
     * @param geographicAreaService service
     * @return the number of readings added
     **/
    public int addReadingsToGeographicAreaSensors(List<ReadingDTO> readingDTOS, String logPath, GeographicAreaService geographicAreaService) {
        Logger logger = LogUtils.getLogger("areaReadingsLogger", logPath, Level.FINE);
        List<Reading> readings = readingDTOsToReadings(readingDTOS);
        int addedReadings = 0;
        List<String> sensorIds = getSensorIDs(readings);
        for (String sensorID : sensorIds) {
            List<Reading> subArray = getReadingsBySensorID(sensorID, readings);
            addedReadings += geographicAreaService.addAreaReadings(sensorID, subArray, logger);
        }
        LogUtils.closeHandlers(logger);
        return addedReadings;
    }

    /**
     * This method receives a list of reading DTOs and converts them into Readings,
     * returning a list of Readings.
     *
     * @param readingDTOS a list of reading DTOs
     * @return a list of Readings converted from the given Reading DTO list.
     ***/
    private List<Reading> readingDTOsToReadings(List<ReadingDTO> readingDTOS) {
        List<Reading> readingList = new ArrayList<>();
        for (ReadingDTO r : readingDTOS) {
            Reading reading = ReadingMapper.dtoToObject(r);
            readingList.add(reading);
        }
        return readingList;
    }

    /**
     * This method receives a list of readings, checks for every sensor ID
     * in every Reading contained in the list and returns a list of strings
     * of all sensor IDs.
     *
     * @param readings a list of readings
     * @return a list of strings of all sensor IDs from the list of readings
     **/
    private List<String> getSensorIDs(List<Reading> readings) {
        List<String> sensorIDs = new ArrayList<>();
        for (Reading r : readings) {
            String sensorID = r.getSensorID();
            if (!sensorIDs.contains(sensorID)) {
                sensorIDs.add(sensorID);
            }
        }
        return sensorIDs;
    }

    /**
     * This mehtod receives a list of readings and a string of a sensor ID,
     * checks for every reading within the list with the same sensorID, and
     * returns a list of readings with the given sensor ID.
     *
     * @param readings list of readings
     * @param sensorID a string of a sensor ID
     * @return a list of readings that have the same sensor ID as the one given
     * as parameter.
     **/
    private List<Reading> getReadingsBySensorID(String sensorID, List<Reading> readings) {
        List<Reading> subArray = new ArrayList<>();
        for (Reading r : readings) {
            String readingSensorID = r.getSensorID();
            if (sensorID.equals(readingSensorID)) {
                subArray.add(r);
            }
        }
        return subArray;
    }

    /**
     * This method will receive a List of AreaReadingDTOs and a log file path and will try to add
     * every reading in the list to its corresponding house sensor.
     * The method returns the number of readings that were correctly added and logs every reading
     * that wasn't added.
     *
     * @param readings List of Area Reading DTOs
     * @param logPath  log file path
     * @return number of Area Readings added to corresponding House Sensor
     **/
    public int addReadingsToHouseSensors(List<ReadingDTO> readings, String logPath, RoomService roomService) {
        Logger logger = LogUtils.getLogger("houseReadingsLogger", logPath, Level.FINE);
        int addedReadings = 0;
        for (ReadingDTO r : readings) {
            RoomSensor sensor = roomService.getById(r.getSensorId());
            double value = r.getValue();
            Date date = r.getDate();
            String unit = r.getUnit();
            if (roomService.addHouseReadingToRepository(sensor, value, date, unit, logger)) {
                addedReadings++;
            }
        }
        LogUtils.closeHandlers(logger);
        return addedReadings;
    }
}
