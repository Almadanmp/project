package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pt.ipp.isep.dei.project.controller.controllercli.HouseConfigurationController;
import pt.ipp.isep.dei.project.controller.controllercli.ReaderController;
import pt.ipp.isep.dei.project.io.ui.commandline.GASettingsUI;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/import")
public class ImportFilesWebController {
    private static final String IMPORT_TIME = "Import time: ";
    private static final String EMPTY_FILE = "ERROR: Imported file is empty.";
    private static final String MILLISECONDS = " millisecond(s).";
    private static final String SUCCESS = "Successfully imported - ";

    @Autowired
    GeographicAreaRepository geographicAreaRepository;
    @Autowired
    SensorTypeRepository sensorTypeRepository;
    @Autowired
    AreaTypeRepository areaTypeRepository;
    @Autowired
    InputHelperUI inputHelperUI;
    @Autowired
    GASettingsUI gaSettingsUI;
    @Autowired
    private ReaderController readerController;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private HouseConfigurationController houseConfigurationController;

    /**
     * Method to import files with Geographic Area and Area Sensors
     *
     * @param file file to import
     * @return response: string with information regarding success or failure
     */
    @PostMapping("/importGA")
    public ResponseEntity<Object> importGAFile(
            @RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(EMPTY_FILE, HttpStatus.OK);
        }

        String result;
        String filename;
        try {
            Path path = saveUploadedFiles(file);
            String pathToFile = path.toString();
            filename = file.getOriginalFilename();
            long startTime = System.currentTimeMillis();
            int areas = inputHelperUI.acceptPathJSONorXMLAndReadFile(pathToFile, geographicAreaRepository, sensorTypeRepository, areaTypeRepository);
            if (areas > 0) {
                long stopTime = System.currentTimeMillis();
                result = areas + " Geographic Areas have been successfully imported. \n" + IMPORT_TIME + (stopTime - startTime) + MILLISECONDS;
            } else {
                result = "\nNo Geographic Areas were imported.";
            }
            Files.delete(path);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS +
                filename + ".\n" + result, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Method to import Area Sensor Readings
     *
     * @param file file to import
     * @return response: string with information regarding success or failure
     */
    @PostMapping("/importAreaReadings")
    public ResponseEntity<Object> importGAReadings(
            @RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(EMPTY_FILE, HttpStatus.OK);
        }

        String result;
        String filename;
        try {
            Path path = saveUploadedFiles(file);
            String pathToFile = path.toString();
            filename = file.getOriginalFilename();
            result = gaSettingsUI.selectImportGAReadingsMethod(pathToFile);
            Files.delete(path);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS +
                filename + ".\n" + result, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Method to import files with House, rooms and Energy Grid Data
     *
     * @param houseFile file to import
     * @return response: string with information regarding success or failure
     */
    @PostMapping("/importHouse")
    public ResponseEntity<Object> importHouseFile(@RequestPart("file") MultipartFile houseFile) {
        if (houseFile.isEmpty()) {
            return new ResponseEntity<>(EMPTY_FILE, HttpStatus.OK);
        }

        String result;
        String filename;
        House house = houseRepository.getHouses().get(0);
        try {
            Path path = saveUploadedFiles(houseFile);
            String pathToFile = path.toString();
            filename = houseFile.getOriginalFilename();
            long startTime = System.currentTimeMillis();
            readerController.readJSONAndDefineHouse(house, pathToFile);

            long stopTime = System.currentTimeMillis();
            result = " \n" + IMPORT_TIME + (stopTime - startTime) + MILLISECONDS;

            Files.delete(path);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS +
                filename + ".\n" + result, new HttpHeaders(), HttpStatus.OK);
    }


    /**
     * Method to import a file with House Sensors data
     *
     * @param houseSensorsFile file to import
     * @return response: string with information regarding success or failure
     */
    @PostMapping("/importHouseSensors")
    public ResponseEntity<Object> importHouseSensorsFile(@RequestPart("file") MultipartFile houseSensorsFile) {

        if (houseSensorsFile.isEmpty()) {
            return new ResponseEntity<>(EMPTY_FILE, HttpStatus.OK);
        }
        String result;
        String filename;

        try {
            Path path = saveUploadedFiles(houseSensorsFile);
            String pathToFile = path.toString();
            filename = houseSensorsFile.getOriginalFilename();
            long startTime = System.currentTimeMillis();
            int[] importedSensors = houseConfigurationController.readSensors(pathToFile);
            long stopTime = System.currentTimeMillis();
            result = importedSensors[0] + " Sensors successfully imported and " + importedSensors[1] + " rejected.\n" +
                    IMPORT_TIME + (stopTime - startTime) + MILLISECONDS;
            Files.delete(path);
        } catch (IOException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS +
                filename + ".\n" + result, new HttpHeaders(), HttpStatus.OK);
    }


    /**
     * Method to save an imported file
     *
     * @param file imported file to save
     * @return path to the saved file
     */
    private Path saveUploadedFiles(MultipartFile file) throws IOException {
        Path path;
        byte[] bytes = file.getBytes();
        String uploadFolder = "src/test/resources/temp/";
        path = Paths.get(uploadFolder + file.getOriginalFilename());
        Files.write(path, bytes);

        return path;
    }

}



