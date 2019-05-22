package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.DateDTO;
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
    @GetMapping("/highestAmplitude")
    public ResponseEntity<String> getHighestTemperatureAmplitudeDate(@RequestBody DateDTO dateDTO) {
        String result;
        try {
            result = geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
