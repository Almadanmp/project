package pt.ipp.isep.dei.project.io.ui.reader;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.AddressDTO;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.HouseDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaderJSONHouseTest {


    ReaderJSONHouse readerJSONHouse = new ReaderJSONHouse();

    @Test
    public void seeHouseDTO() {
        HouseDTO expectedResult = new HouseDTO();
        AddressDTO addressExpectedResult = new AddressDTO();
        addressExpectedResult.setStreet("R. Dr. António Bernardino de Almeida");
        addressExpectedResult.setNumber("431");
        addressExpectedResult.setZip("4200-072");
        addressExpectedResult.setTown("Porto");
        addressExpectedResult.setCountry("Portugal");
        List<RoomDTO> roomListExpectedResult = new ArrayList<>();
        List<EnergyGridDTO> energyGridListExpectedResult = new ArrayList<>();
        RoomDTO roomDTO1 = new RoomDTO();
        roomDTO1.setName("B107");
        roomDTO1.setDescription("Classroom");
        roomDTO1.setFloor(1);
        roomDTO1.setWidth(10);
        roomDTO1.setLength(6);
        roomDTO1.setHeight(3.5);
        RoomDTO roomDTO2 = new RoomDTO();
        roomDTO2.setName("B109");
        roomDTO2.setDescription("Classroom");
        roomDTO2.setFloor(1);
        roomDTO2.setWidth(10);
        roomDTO2.setLength(6);
        roomDTO2.setHeight(3.5);
        RoomDTO roomDTO3 = new RoomDTO();
        roomDTO3.setName("B106");
        roomDTO3.setDescription("Classroom");
        roomDTO3.setFloor(1);
        roomDTO3.setWidth(13);
        roomDTO3.setLength(7);
        roomDTO3.setHeight(3.5);
        RoomDTO roomDTO4 = new RoomDTO();
        roomDTO4.setName("B209");
        roomDTO4.setDescription("Classroom");
        roomDTO4.setFloor(2);
        roomDTO4.setWidth(10);
        roomDTO4.setLength(6);
        roomDTO4.setHeight(3.5);
        RoomDTO roomDTO5 = new RoomDTO();
        roomDTO5.setName("B210");
        roomDTO5.setDescription("Meeting room");
        roomDTO5.setFloor(2);
        roomDTO5.setWidth(5);
        roomDTO5.setLength(5.5);
        roomDTO5.setHeight(3.5);
        RoomDTO roomDTO6 = new RoomDTO();
        roomDTO6.setName("B102");
        roomDTO6.setDescription("Reprographics Centre");
        roomDTO6.setFloor(1);
        roomDTO6.setWidth(7);
        roomDTO6.setLength(21);
        roomDTO6.setHeight(3.5);
        RoomDTO roomDTO7 = new RoomDTO();
        roomDTO7.setName("B405A");
        roomDTO7.setDescription("DEI Datacenter");
        roomDTO7.setFloor(4);
        roomDTO7.setWidth(6);
        roomDTO7.setLength(3);
        roomDTO7.setHeight(3.5);
        roomListExpectedResult.add(roomDTO1);
        roomListExpectedResult.add(roomDTO2);
        roomListExpectedResult.add(roomDTO3);
        roomListExpectedResult.add(roomDTO4);
        roomListExpectedResult.add(roomDTO5);
        roomListExpectedResult.add(roomDTO6);
        roomListExpectedResult.add(roomDTO7);
        EnergyGridDTO energyGridExpectedResult1 = new EnergyGridDTO();
        energyGridExpectedResult1.setName("B building");
        List<RoomDTO> energyGrid1RoomList1 = new ArrayList<>();
        energyGrid1RoomList1.add(roomDTO3);
        energyGrid1RoomList1.add(roomDTO1);
        energyGrid1RoomList1.add(roomDTO2);
        energyGrid1RoomList1.add(roomDTO4);
        energyGrid1RoomList1.add(roomDTO5);
        energyGridExpectedResult1.setRoomDTOS(energyGrid1RoomList1);
        EnergyGridDTO energyGridExpectedResult2 = new EnergyGridDTO();
        energyGridExpectedResult2.setName("B building technical");
        List<RoomDTO> energyGrid1RoomList2 = new ArrayList<>();
        energyGrid1RoomList2.add(roomDTO6);
        energyGrid1RoomList2.add(roomDTO7);
        energyGridExpectedResult2.setRoomDTOS(energyGrid1RoomList2);
        energyGridListExpectedResult.add(energyGridExpectedResult1);
        energyGridListExpectedResult.add(energyGridExpectedResult2);
        expectedResult.setRoomList(roomListExpectedResult);
        expectedResult.setEnergyGridList(energyGridListExpectedResult);
        expectedResult.setAddress(addressExpectedResult);
        HouseDTO houseDTO = readerJSONHouse.readFile("src/test/resources/houseFiles/DataSet_sprint06_HouseData.json");
        assertEquals(expectedResult, houseDTO);
    }

//    @Test
//    public void seeIfThrowsIllegalArgumentException() {
//        ReaderJSONHouse readerJSONHouse = new ReaderJSONHouse();
//        HouseDTO houseDTO = readerJSONHouse.readFile("src/test/resources/readingsFiles/DataSet_sprint05_SensorData.json");
//        HouseDTO houseDTO1 = new HouseDTO();
//        assertEquals(houseDTO,houseDTO1);
//    }
}

