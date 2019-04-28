package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.RoomSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

/**
 * RoomMonitoringController tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomMonitoringControllerTest {

    // Common artifacts for testing in this class.

    private RoomMonitoringController controller = new RoomMonitoringController();
    private RoomService roomService;

    @Mock
    RoomSensorRepository roomSensorRepository;

    @Mock
    SensorTypeRepository sensorTypeRepository;

    @Mock
    RoomRepository roomRepository;

    @BeforeEach
    void arrangeArtifacts() {
        this.roomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
    }


}