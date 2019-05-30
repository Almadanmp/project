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
import pt.ipp.isep.dei.project.dto.DateDTO;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@ApplicationScope
@RequestMapping("/roomMonitoring")
public class RoomMonitoringWebController {

    @Autowired
    RoomRepository roomRepository;

    /**
     * US605 As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     *
     * @param roomId string so we can identify the room.
     * @return the current room temperature from its most recent temperature reading.
     */

    @GetMapping("/currentRoomTemperature")
    public ResponseEntity<Object> getCurrentRoomTemperature(@RequestBody String roomId) {
        Link link;
        double currentRoomTemperature;
        try {
            currentRoomTemperature = roomRepository.getCurrentRoomTempByRoomId(roomId);
            return new ResponseEntity<>(currentRoomTemperature, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            link = linkTo(methodOn(RoomMonitoringWebController.class).
                    getCurrentRoomTemperature(roomId)).withRel("This room does not exist.");
            return new ResponseEntity<>(link, HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("There are no temperature readings for that room", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* US610 - WEB Controller Methods
     Get Max Temperature in a room in a specific day.*/
    @GetMapping("/dayMaxTemperature")
    public ResponseEntity<Object> getRoomMaxTempInDay(@RequestBody String roomId, @RequestBody DateDTO date) {
        Link link;
        double result;
        try {
            result = roomRepository.getRoomMaxTempById(roomId, date.getDate());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            link = linkTo(methodOn(RoomMonitoringWebController.class).
                    getRoomMaxTempInDay(roomId, date)).withRel("This room does not exist.");
            return new ResponseEntity<>(link, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("There are no readings for the given date.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
