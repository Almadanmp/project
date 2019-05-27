package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.DateIntervalDTO;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;

import java.util.NoSuchElementException;

@RestController
@ApplicationScope
@RequestMapping("/houseMonitoring")
public class HouseMonitoringWebController {

    @Autowired
    GeographicAreaHouseService geographicAreaHouseService;

    /* US 633 - WEB Controller Methods
    As Regular User, I want to get the day with the highest temperature amplitude in the house area in a given period. */
    @PostMapping("/highestAmplitude")
    public ResponseEntity<String> getHighestTemperatureAmplitudeDate(@RequestBody DateIntervalDTO dateIntervalDTO) {
        String result;
        try {
            result = geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
