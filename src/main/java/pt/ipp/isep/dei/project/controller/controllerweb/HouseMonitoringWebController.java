package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.DateDTO;
import pt.ipp.isep.dei.project.dto.DateValueDTO;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@ApplicationScope
@RequestMapping("/houseMonitoring")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
public class HouseMonitoringWebController {

    @Autowired
    GeographicAreaHouseService geographicAreaHouseService;

    private final String periodRetry = "Retry with a different period.";

    /**
     * US600
     * As a Regular User, I want to get the current temperature in the house area. If, in the
     * first element with temperature sensors of the hierarchy of geographical areas that
     * includes the house, there is more than one temperature sensor, the nearest one
     * should be used.
     *
     * @return current house temperature from closest area sensor to house.
     */
    @GetMapping("/currentHouseAreaTemperature")
    public ResponseEntity<Object> getCurrentHouseAreaTemperature() {
        double currentHouseAreaTemp;
        try {
            currentHouseAreaTemp = geographicAreaHouseService.getHouseAreaTemperature();
            return new ResponseEntity<>(currentHouseAreaTemp, HttpStatus.OK);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* US620 - WEB Controller Methods
     As a Regular User, I want to get the total rainfall in the house area for a given day.*/
    @GetMapping("/totalRainfall")
    public ResponseEntity<Object> getTotalRainfallInGivenDay(@RequestBody DateDTO date) {
        double result;
        Link link;
        try {
            result = geographicAreaHouseService.getTotalRainfallOnGivenDay(date.getDate());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            link = linkTo(methodOn(HouseMonitoringWebController.class).
                    getTotalRainfallInGivenDay(date)).withRel("No readings available for this date.");
            return new ResponseEntity<>(link, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /* 623 - I want to get the average daily rainfall in the house area for a given period (days) */
    @GetMapping("/averageRainfall")
    public ResponseEntity<Object> getAverageDailyRainfall(@RequestParam("initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date initialDate,
                                                          @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        double result;
        try {
            result = geographicAreaHouseService.getAverageDailyRainfall(initialDate, endDate);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /* US 630 - WEB Controller Methods
    As a Regular User, I want to get the last coldest day (lower maximum temperature) in the house area in a given period. */
    @GetMapping("/coldestDay")
    public ResponseEntity<Object> getLastColdestDay(@RequestParam("initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date initialDate,
                                                    @RequestParam("finalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date finalDate) {
        DateValueDTO result;
        Link link;
        try {
            result = geographicAreaHouseService.getLastColdestDay(initialDate, finalDate);
            link = linkTo(methodOn(HouseMonitoringWebController.class).getLastColdestDay(initialDate, finalDate)).withRel(periodRetry);
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
    @GetMapping("/hottestDay")
    public ResponseEntity<Object> getHottestDay(@RequestParam("initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date initialDate,
                                                @RequestParam("finalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date finalDate) {
        DateValueDTO result;
        Link link;
        try {
            result = geographicAreaHouseService.getHottestDay(initialDate, finalDate);
            link = linkTo(methodOn(HouseMonitoringWebController.class).getHottestDay(initialDate, finalDate)).withRel(periodRetry);
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
    @GetMapping("/highestAmplitude")
    public ResponseEntity<Object> getHighestTemperatureAmplitudeDate(@RequestParam("initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date initialDate,
                                                                     @RequestParam("finalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date finalDate) {
        DateValueDTO result;
        Link link;
        try {
            result = geographicAreaHouseService.getHighestTemperatureAmplitude(initialDate, finalDate);
            link = linkTo(methodOn(HouseMonitoringWebController.class).getHighestTemperatureAmplitudeDate(initialDate, finalDate)).withRel(periodRetry);
            result.add(link);

        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
