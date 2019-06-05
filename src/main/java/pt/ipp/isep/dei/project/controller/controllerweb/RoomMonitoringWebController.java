package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@ApplicationScope
@RequestMapping("/roomMonitoring")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
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

    @GetMapping("/currentRoomTemperature/{roomId}")
    public ResponseEntity<Object> getCurrentRoomTemperature(@PathVariable String roomId) {
        Link link;
        double currentRoomTemperature;
        try {
            currentRoomTemperature = roomRepository.getCurrentRoomTempByRoomId(roomId);
            return new ResponseEntity<>(currentRoomTemperature, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            link = linkTo(methodOn(RoomMonitoringWebController.class).
                    getCurrentRoomTemperature(roomId)).withRel("This room does not exist.");
            return new ResponseEntity<>(link, HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("There are no temperature readings for that room", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* US610 - WEB Controller Methods
     Get Max Temperature in a room in a specific day.*/
    @GetMapping("/dayMaxTemperature/{roomId}")
    public ResponseEntity<Object> getRoomMaxTempInDay(@PathVariable String roomId, @RequestParam ("initialDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) String day) {
        Link link;
        double result;
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = df.parse(day);
        }
        catch (ParseException c) {
            c.printStackTrace();
        }
        if(date==null){
            link = linkTo(methodOn(RoomMonitoringWebController.class).
                    getRoomMaxTempInDay(roomId,day)).withRel("This date is not valid.");
            return new ResponseEntity<>(link, HttpStatus.OK);
        }
        try {
            result = roomRepository.getRoomMaxTempById(roomId, date);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            link = linkTo(methodOn(RoomMonitoringWebController.class).
                    getRoomMaxTempInDay(roomId,day)).withRel("This room does not exist.");
            return new ResponseEntity<>(link, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("There are no readings for the given date.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
