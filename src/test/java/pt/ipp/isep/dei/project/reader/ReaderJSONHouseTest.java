package pt.ipp.isep.dei.project.reader;

import org.junit.Test;
import pt.ipp.isep.dei.project.dto.HouseDTO;

public class ReaderJSONHouseTest {

    @Test
    public void seeHouseDTO() {
        ReaderJSONHouse readerJSONHouse = new ReaderJSONHouse();
        HouseDTO houseDTO = readerJSONHouse.readFile("src/test/resources/readingsFiles/DataSet_sprint06_HouseData.json");
        System.out.println(houseDTO);
    }
}
