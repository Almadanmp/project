package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderJSONHouse implements Reader {
    private List<RoomDTO> roomDTOS;
    private JSONArray roomList;
    private JSONArray gridList;
    private String gridName;

    public HouseDTO readFile(String filePath) {
        try {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            return readHouseJSON(object);
        } catch (FileNotFoundException | JSONException | IllegalArgumentException e) {
           throw new IllegalArgumentException(UtilsUI.printMessage("The JSON file is invalid."));
        }
    }

    private HouseDTO readHouseJSON(JSONObject jsonObject) {
        HouseDTO houseDTO = new HouseDTO();
        JSONObject address;
        try {
            this.roomList = jsonObject.getJSONArray("room");
            address = jsonObject.getJSONObject("adress");
            this.gridList = jsonObject.getJSONArray("grid");

        } catch (JSONException e) {
           throw new IllegalArgumentException();
        }
        List<RoomDTO> roomDTOList = readRoomsJSON();
        AddressDTO addressDTO = readAddressJSON(address);
        List<EnergyGridDTO> energyGridDTOList = readGridsJSON();
        houseDTO.setAddress(addressDTO);
        houseDTO.setLocation(new LocalDTO(0.0, 0.0, 0.0));
        houseDTO.setRoomList(roomDTOList);
        houseDTO.setEnergyGridList(energyGridDTOList);
        return houseDTO;
    }

    private List<RoomDTO> readRoomsJSON() {
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
            this.gridName = grid.getString("name");
            EnergyGridDTO energyGridObject = new EnergyGridDTO();
            energyGridObject.setName(gridName);

            for (int y = 0; y < e; y++) {
                Object jsonArray = grid.getJSONArray("rooms").get(y);
                String roomName = jsonArray.toString();
                addRoomToGrid(roomName);
            }
            energyGridObject.setRoomDTOS(this.roomDTOS);
            energyGridDTOList.add(energyGridObject);
        }
        return energyGridDTOList;
    }

    public List<Room> addRoomToGrid(String roomName) {
        List<Room> roomFinalList = new ArrayList<>();
        for (int x = 0; x < roomDTOS.size(); x++) {
            if (roomDTOS.get(x).getName().equals(roomName)) {
                RoomDTO roomDTO = roomDTOS.get(x);
                roomDTO.setEnergyGridName(this.gridName);
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
