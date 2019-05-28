package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.DateIntervalDTO;
import pt.ipp.isep.dei.project.dto.DateValueDTO;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@ApplicationScope
@RequestMapping("/houseMonitoring")
public class HouseMonitoringWebController {

    @Autowired
    GeographicAreaHouseService geographicAreaHouseService;

    /* US620 - WEB Controller Methods
     As a Regular User, I want to get the total rainfall in the house area for a given day.*/
    @GetMapping("/totalRainfall")
    public ResponseEntity<Object> getTotalRainfallDay(@RequestBody Date date) {
        double result;
        Link link;
        try {
            result = geographicAreaHouseService.getTotalRainfallOnGivenDay(date);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalStateException e) {
            link = linkTo(methodOn(HouseMonitoringWebController.class).
                    getTotalRainfallDay(date)).withRel("Retry with a different date.");
            return new ResponseEntity<>(link, HttpStatus.OK);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* US 630 - WEB Controller Methods
    As a Regular User, I want to get the last coldest day (lower maximum temperature) in the house area in a given period. */
    @PostMapping("/coldestDay")
    public ResponseEntity<Object> getLastColdestDay(@RequestBody DateIntervalDTO dateIntervalDTO) {
        DateValueDTO result;
        Link link;
        try {
            result = geographicAreaHouseService.getLastColdestDay(dateIntervalDTO);
            link = linkTo(methodOn(HouseMonitoringWebController.class).getLastColdestDay(dateIntervalDTO)).withRel("Retry with a different period.");
            result.add(link);

        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /* US 631 - WEB Controller Methods
 As a Regular User, I want to get the first hottest day (higher maximum temperature) in the house area in a given period.  */
    @PostMapping("/hottestDay")
    public ResponseEntity<Object> getHottestDay(@RequestBody DateIntervalDTO dateIntervalDTO) {
        DateValueDTO result;
        Link link;
        try {
            result = geographicAreaHouseService.getHottestDay(dateIntervalDTO);
            link = linkTo(methodOn(HouseMonitoringWebController.class).getHottestDay(dateIntervalDTO)).withRel("Retry with a different period.");
            result.add(link);

        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /* US 633 - WEB Controller Methods
    As Regular User, I want to get the day with the highest temperature amplitude in the house area in a given period. */
    @PostMapping("/highestAmplitude")
    public ResponseEntity<Object> getHighestTemperatureAmplitudeDate(@RequestBody DateIntervalDTO dateIntervalDTO) {
        DateValueDTO result;
        Link link;
        try {
            result = geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO);
            link = linkTo(methodOn(HouseMonitoringWebController.class).getHighestTemperatureAmplitudeDate(dateIntervalDTO)).withRel("Retry with a different period.");
            result.add(link);

        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
