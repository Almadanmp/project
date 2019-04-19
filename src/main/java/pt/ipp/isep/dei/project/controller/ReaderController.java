package pt.ipp.isep.dei.project.controller;

import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.EnergyGridMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.reader.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderController {


    private AreaSensorService areaSensorService;
    private HouseSensorService houseSensorService;
    private ReadingService readingService;
    private HouseService houseService;


    public ReaderController(AreaSensorService areaSensorService, ReadingService readingService, HouseService houseService, HouseSensorService houseSensorService) {
        this.readingService = readingService;
        this.areaSensorService = areaSensorService;
        this.houseService = houseService;
        this.houseSensorService = houseSensorService;
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
            areasRead = readerJSON.readJSONFileAndAddGeoAreas(filePath, list, areaSensorService);
            return areasRead;
        }
        if (filePath.endsWith(".xml")) {
            ReaderXMLGeoArea readerXML = new ReaderXMLGeoArea();
            areasRead = readerXML.readFileXMLAndAddAreas(filePath, list, areaSensorService, readingService, houseService, houseSensorService);
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
    public boolean readJSONAndDefineHouse(House house, String filePath) {
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
            houseService.saveEnergyGrid(energyGrid);
        }

        //ROOMS
        for (EnergyGridDTO eg : gridDTOS) {
            List<RoomDTO> roomDTOS = eg.getRoomDTOS();
            for (RoomDTO rt : roomDTOS) {
                rt.setEnergyGridName(eg.getName());
                rt.setHouseId(house.getId());
                Room aux = RoomMapper.dtoToObjectUS100(rt);
                houseService.saveRoom(aux);
            }
        }
        return houseService.saveHouse(house);
    }

    /**
     * This method receives a list of Geographic Areas to add the given NodeList correspondent to the Geographic Areas
     * imported from the XML File.
     *
     * @param nListGeoArea - NodeList imported from the XML.
     * @param list         - list to which we want to add and persist the Geographic areas.
     * @return - the number of geographic areas imported.
     */
    public int addGeoAreaNodeListToList(NodeList nListGeoArea, GeographicAreaService list, AreaSensorService areaSensorService) {
        ReaderXMLGeoArea readerXML = new ReaderXMLGeoArea();
        int result = 0;
        for (int i = 0; i < nListGeoArea.getLength(); i++) {
            if (readerXML.readGeographicAreasXML(nListGeoArea.item(i), list, areaSensorService)) {
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
    List<GeographicAreaDTO> readFileJSONGeoAreas(String filePath) {
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
    int addGeoAreasDTOToList(List<GeographicAreaDTO> geographicAreaDTOS, GeographicAreaService list) {
        int counter = 0;
        for (GeographicAreaDTO dto : geographicAreaDTOS) {
            GeographicArea geoArea = GeographicAreaMapper.dtoToObject(dto);
            list.addAndPersistGA(geoArea);
            counter++;
        }
        return counter;
    }


    /**
     * This method will receive a List of AreaReadingDTOs and a log file path and will try to add
     * every reading in the list to its corresponding area sensor.
     * The method returns the number of readings that were correctly added and logs every reading
     * that wasn't added.
     *
     * @param readings List of Area Reading DTOs
     * @param logPath  log file path
     * @return number of Area Readings added to corresponding Area Sensor
     **/
    public int addReadingsToGeographicAreaSensors(List<ReadingDTO> readings, String logPath, GeographicAreaService geographicAreaService) {
        Logger logger = getLogger(logPath);
        int addedReadings = 0;
        for (ReadingDTO r : readings) {
            AreaSensor sensor = areaSensorService.getById(r.getSensorId());
            double value = r.getValue();
            Date date = r.getDate();
            String unit = r.getUnit();
            if (geographicAreaService.addAreaReadingToRepository(sensor, value, date, unit, logger, areaSensorService)) {
                addedReadings++;
            }
        }
        return addedReadings;
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
    public int addReadingsToHouseSensors(List<ReadingDTO> readings, String logPath) {
        Logger logger = getLogger(logPath);
        int addedReadings = 0;
        for (ReadingDTO r : readings) {
            HouseSensor sensor = houseSensorService.getById(r.getSensorId());
            double value = r.getValue();
            Date date = r.getDate();
            String unit = r.getUnit();
            if (readingService.addHouseReadingToRepository(sensor, value, date, unit, logger, houseSensorService)) {
                addedReadings++;
            }
        }
        return addedReadings;
    }

    /**
     * This method creates a Logger.
     *
     * @param logPath log file path.
     * @return object of class Logger.
     **/
    private Logger getLogger(String logPath) {
        Logger logger = Logger.getLogger(ReaderController.class.getName());
        try {
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            logger.setLevel(Level.WARNING);
        } catch (IOException io) {
            io.getMessage();
        }
        return logger;
    }
}
