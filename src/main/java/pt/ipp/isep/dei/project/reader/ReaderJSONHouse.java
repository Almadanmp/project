package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.Room;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReaderJSONHouse implements Reader {
    private List<RoomDTO> roomDTOS;
    private JSONArray roomList;
    private JSONArray gridList;
    private String gridRoomName;

    public HouseDTO readFile(String filePath) {
        try {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            return readHouseJSON(object);
        } catch (FileNotFoundException e) {
            UtilsUI.printMessage("The file wasn't found.");
        }
        return new HouseDTO();
    }

    private HouseDTO readHouseJSON(JSONObject jsonObject) {
        HouseDTO houseDTO = new HouseDTO();
        this.roomList = jsonObject.getJSONArray("room");
        JSONObject address = jsonObject.getJSONObject("adress");
        this.gridList = jsonObject.getJSONArray("grid");
        List<RoomDTO> roomDTOList = readRoomsJSON();
        AddressDTO addressDTO = readAddressJSON(address);
        List<EnergyGridDTO> energyGridDTOList = readGridsJSON();
        houseDTO.setAddress(addressDTO);
        houseDTO.setLocation(new LocalDTO(0.0, 0.0, 0.0));
        houseDTO.setRoomList(roomDTOList);
        houseDTO.setEnergyGridList(energyGridDTOList);
        return houseDTO;
    }

   private  List<RoomDTO> readRoomsJSON() {
        List<RoomDTO> roomArray = new ArrayList<>();
        for (int i = 0; i < this.roomList.length(); i++) {
            JSONObject room = this.roomList.getJSONObject(i);
            String roomName = room.getString("id");
            String roomDescription = room.getString("description");
            int roomFloor = Integer.parseInt(room.getString("floor"));
            double roomWidth = room.getDouble("width");
            double roomLength = room.getDouble("length");
            double roomHeight = room.getDouble("height");
            RoomDTO roomObject = new RoomDTO();
            roomObject.setName(roomName);
            roomObject.setDescription(roomDescription);
            roomObject.setFloor(roomFloor);
            roomObject.setWidth(roomWidth);
            roomObject.setLength(roomLength);
            roomObject.setHeight(roomHeight);
            roomArray.add(roomObject);
        }
        roomDTOS = roomArray;
        return roomArray;
    }

    public List<EnergyGridDTO> readGridsJSON() {
        List<EnergyGridDTO> energyGridDTOList = new ArrayList<>();
        for (int i = 0; i < this.gridList.length(); i++) {
            int e = this.gridList.getJSONObject(i).getJSONArray("rooms").length();
            JSONObject grid = this.gridList.getJSONObject(i);
            String gridName = grid.getString("name");
            EnergyGridDTO energyGridObject = new EnergyGridDTO();
            energyGridObject.setName(gridName);

            for (int y = 0; y < e; y++) {
                Object jsonArray = grid.getJSONArray("rooms").get(y);
                this.gridRoomName = jsonArray.toString();
                addRoomToGrid();
            }
            energyGridObject.setRoomDTOS(this.roomDTOS);
            energyGridDTOList.add(energyGridObject);
        }
        return energyGridDTOList;
    }

   public  List<Room> addRoomToGrid( ) {
        List<Room> roomFinalList = new ArrayList<>();
        for (int x = 0; x < roomDTOS.size(); x++) {
            if (roomDTOS.get(x).getName().equals(this.gridRoomName)) {
                RoomDTO roomDTO = roomDTOS.get(x);
                roomDTO.setEnergyGridName(this.gridRoomName);
                this.roomDTOS.add(roomDTO);
                roomFinalList.add(RoomMapper.dtoToObjectUS100(roomDTO));
            }
        }
        return roomFinalList;
    }

    private AddressDTO readAddressJSON(JSONObject address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getString("street"));
        addressDTO.setNumber(address.getString("number"));
        addressDTO.setZip(address.getString("zip"));
        addressDTO.setTown(address.getString("town"));
        addressDTO.setCountry(address.getString("country"));
        return addressDTO;
    }
}
