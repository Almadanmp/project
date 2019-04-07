package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import pt.ipp.isep.dei.project.dto.AddressDTO;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;

import java.util.List;

public class ReaderJSONHouseTest {
    @Test
    public void seeRooms() {
        ReaderJSONHouse readerJSONHouse = new ReaderJSONHouse();
        JSONArray jsonArray = readerJSONHouse.readFile("src/test/resources/readerReadings/DataSet_sprint06_HouseData.json");
        List<RoomDTO> roomDTO = readerJSONHouse.readRoomsJSON(jsonArray);
        System.out.println(roomDTO);
    }

    @Test
    public void seeAddress() {
        ReaderJSONHouse readerJSONHouse = new ReaderJSONHouse();
        JSONObject jsonArray = readerJSONHouse.readFile1("src/test/resources/readerReadings/DataSet_sprint06_HouseData.json");
        AddressDTO addressDTO = readerJSONHouse.readAddressJSON(jsonArray);
        System.out.println(addressDTO);
    }

    @Test
    public void seeGrids() {
        ReaderJSONHouse readerJSONHouse = new ReaderJSONHouse();
        JSONArray jsonArray = readerJSONHouse.readFile2("src/test/resources/readerReadings/DataSet_sprint06_HouseData.json");
        JSONArray jsonArray1 = readerJSONHouse.readFile("src/test/resources/readerReadings/DataSet_sprint06_HouseData.json");
        readerJSONHouse.readRoomsJSON(jsonArray1);
        List<EnergyGridDTO> energyGridDTOS = readerJSONHouse.readGridsJSON(jsonArray);
        System.out.println(energyGridDTOS);
    }
}
