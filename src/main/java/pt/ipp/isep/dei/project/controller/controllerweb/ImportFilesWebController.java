package pt.ipp.isep.dei.project.controller.controllerweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
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
    private static String UPLOADED_FOLDER = "src/test/resources/temp/";
    private final Logger logger = LoggerFactory.getLogger(ImportFilesWebController.class);
    @Autowired
    GeographicAreaRepository geographicAreaRepository;
    @Autowired
    SensorTypeRepository sensorTypeRepository;
    @Autowired
    AreaTypeRepository areaTypeRepository;

    @Autowired
    InputHelperUI inputHelperUI;

    @PostMapping("/importGA")
    public ResponseEntity<?> importGAFile(
            @RequestPart("file") MultipartFile file) {
        String result;
        logger.debug("Single file upload!");
        String filename;

        if (file.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }

        try {
            Path path = saveUploadedFiles(file);
            String pathToFile = path.toString();
            filename = file.getOriginalFilename();
            long startTime = System.currentTimeMillis();
            int areas = inputHelperUI.acceptPathJSONorXMLAndReadFile(pathToFile, geographicAreaRepository, sensorTypeRepository, areaTypeRepository);
            if (areas > 0) {
                long stopTime = System.currentTimeMillis();
                result = areas + " Geographic Areas have been successfully imported. \n"  +IMPORT_TIME + (stopTime - startTime) + MILLISECONDS;
            }
            else {
                result ="\nNo Geographic Areas were imported."; //TODO dar acesso aos logs?
            }
            Files.delete(path);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully uploaded - " +
                filename + ".\n" + result, new HttpHeaders(), HttpStatus.OK);
    }

    private Path saveUploadedFiles(MultipartFile file) throws IOException {
        Path path;
        byte[] bytes = file.getBytes();
        path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);

        return path;
    }
}



