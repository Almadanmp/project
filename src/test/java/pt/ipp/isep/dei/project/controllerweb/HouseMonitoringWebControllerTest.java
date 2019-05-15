package pt.ipp.isep.dei.project.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project.dto.DateDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class HouseMonitoringWebControllerTest {

    @Autowired
    HouseMonitoringWebController houseMonitoringWebController;


    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private Date validDate1;
    private Date validDate2;

    @BeforeEach
    void arrangeArtifacts() {
        validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            validDate1 = validSdf.parse("01/01/2018 15:00:00");
            validDate2 = validSdf.parse("01/01/2019 17:00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
    }


//    @Test
//    public void getHighestTemperatureAmplitudeDate() throws Exception {
//
//        DateDTO dateDTO = new DateDTO();
//        dateDTO.setInitialDate(validDate1);
//        dateDTO.setEndDate(validDate2);
//
//
//        ResponseEntity<String> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateDTO);
//
//    }
}


