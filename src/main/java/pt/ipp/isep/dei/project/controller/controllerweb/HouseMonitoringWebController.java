package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.DateIntervalDTO;
import pt.ipp.isep.dei.project.dto.DateValueDTO;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;

import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@ApplicationScope
@RequestMapping("/houseMonitoring")
public class HouseMonitoringWebController {

    @Autowired
    GeographicAreaHouseService geographicAreaHouseService;

    /* US 633 - WEB Controller Methods
    As Regular User, I want to get the day with the highest temperature amplitude in the house area in a given period. */
    @GetMapping("/highestAmplitude")
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
