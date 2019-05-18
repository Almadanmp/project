package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.DateDTO;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.repository.HouseCrudeRepo;

import java.util.Date;

@RestController
@ApplicationScope
@RequestMapping("/houseMonitoring")
public class HouseMonitoringWebController {

    @Autowired
    GeographicAreaRepository geographicAreaRepository;
    @Autowired
    HouseCrudeRepo houseCrudeRepo;
    @Autowired
    GeographicAreaHouseService geographicAreaHouseService;


    /* US 633 - Controller Methods
    As Regular User, I want to get the day with the highest temperature amplitude in the house area in a given period. */

    @GetMapping("/houses")
    public ResponseEntity<String> getHighestTemperatureAmplitudeDate(@RequestBody DateDTO dateDTO) {
        House house = houseCrudeRepo.findAll().get(0);
        Long geographicAreaID = house.getMotherAreaID();
        GeographicArea geographicArea = geographicAreaRepository.get(geographicAreaID);
        if (geographicArea == null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
        AreaSensor areaSensor = geographicAreaHouseService.getClosestAreaSensorOfGivenType("temperature", house, geographicArea);
        if (areaSensor == null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
        Date date = areaSensor.getDateHighestAmplitudeBetweenDates(dateDTO.getInitialDate(), dateDTO.getEndDate());
        double value = areaSensor.getReadingValueOnGivenDay(date);
        return new ResponseEntity<>(DateUtils.formatDateNoTime(date) + ", with " + value + "ºC", HttpStatus.OK);
    }

}
