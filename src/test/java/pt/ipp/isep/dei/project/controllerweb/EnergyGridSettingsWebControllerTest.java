package pt.ipp.isep.dei.project.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.mappers.EnergyGridMapper;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
public class EnergyGridSettingsWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EnergyGridRepository energyGridRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private EnergyGridSettingsWebController energyGridSettingsWebController;



    @BeforeEach
    void setData() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void seeIfCreateEnergyGridWorks() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(any(EnergyGridDTO.class));

        this.mockMvc.perform(post("/gridSettings/grids")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"B building\",\n" +
                        "  \"houseID\": \"7\",\n" +
                        "  \"maxContractedPower\": \"45\"\n" +
                        "}"))
                .andExpect(status().isCreated());

    }

    @Test
     void seeIfCreateEnergyGridGenerates201() {

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45, "7");

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateEnergyGridGenerates409() {

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45, "7");

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(false).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.CONFLICT, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateEnergyGridGenerates400NullName() {

        EnergyGrid validGrid = new EnergyGrid(null, 45, "7");

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateEnergyGridGenerates400NullHouseID() {

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45, null);

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }

    @Test
     void seeIfGetAllGridsDoesNotWork() throws Exception {
        String uri = "/gridSettings/grids";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
     void seeIfDetachRoomFromGridWorksInvalid() throws Exception {
        // Arrange

        String URI = "/gridSettings/grids/B%20Building";

        // Act

        MvcResult actualResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = actualResult.getResponse().getStatus();

        // Assert

        assertEquals(404, status);
    }

    @Test
     void seeIfDeleteRoomFromGridWorks() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();

        Mockito.doReturn(true).when(energyGridRepository).removeRoomFromGrid(any(String.class),any(String.class));

        this.mockMvc.perform(delete("/gridSettings/grids/B building")
                .contentType(MediaType.TEXT_PLAIN)
                .content("B106"))
                .andExpect(status().isOk());
    }
}
