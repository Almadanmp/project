package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
public class ImportFilesWebController {
    private static final String IMPORT_TIME = "Import time: ";
    private static final String MILLISECONDS = " millisecond(s).";
    private static final String SUCCESS = "Successfully imported - ";
    private static String UPLOADED_FOLDER = "src/test/resources/temp/";

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

    @PostMapping("/importGA")
    public ResponseEntity<?> importGAFile(
            @RequestPart("file") MultipartFile file) {
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


    @PostMapping("/importAreaReadings")
    public ResponseEntity<?> importGAReadings(
            @RequestPart("file") MultipartFile file) {
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


    private Path saveUploadedFiles(MultipartFile file) throws IOException {
        Path path;
        byte[] bytes = file.getBytes();
        path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);

        return path;
    }

    @PostMapping("/importHouse")
    public ResponseEntity<?> importHouseFile(
            @RequestPart("file") MultipartFile houseFile) {
        String result;
        String filename;
        House house = houseRepository.getHouses().get(0);
        try {
            Path path = saveUploadedFiles(houseFile);
            String pathToFile = path.toString();
            filename = houseFile.getOriginalFilename();
            long startTime = System.currentTimeMillis();
             readerController.readJSONAndDefineHouse(house,pathToFile);

                long stopTime = System.currentTimeMillis();
                result = " \n" + IMPORT_TIME + (stopTime - startTime) + MILLISECONDS;

            Files.delete(path);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS +
                filename + ".\n" + result, new HttpHeaders(), HttpStatus.OK);
    }


}



