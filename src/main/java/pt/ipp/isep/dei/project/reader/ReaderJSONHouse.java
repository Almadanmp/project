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

    /**
     * Method that Reads a .json file and returns a House DTO.
     *
     * @param filePath .json file filePath.
     * @return a House DTO for US100.
     */
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

    /**
     * Method that returns a House DTO from a JSONObject.
     *
     * @param jsonObject is the json object with the information of the house.
     * @return a House DTO for US100.
     */
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

    /**
     * Method that returns a List of RoomDTO from a JSONObject.
     *
     * @return a List of RoomDTO.
     */
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

    /**
     * Method that returns a List of EnergyGridDTO from a JSONObject.
     *
     * @return a List of EnergyGridDTO.
     */
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
                addRoomToRoomList(roomName);
            }
            energyGridObject.setRoomDTOS(this.roomDTOS);
            energyGridDTOList.add(energyGridObject);
        }
        return energyGridDTOList;
    }

    /**
     * Method that add's every room to the roomList and sets the energyGridName for each one.
     * @param roomName String roomName
     * @return List of Room
     */
    public List<Room> addRoomToRoomList(String roomName) {
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

    /**
     * Method that reads a JSONObject and returns the House Address DTO
     * @param jsonObject JSONObject
     * @return House Address DTO
     */
    private AddressDTO readAddressJSON(JSONObject jsonObject) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(jsonObject.getString("street"));
        addressDTO.setNumber(jsonObject.getString("number"));
        addressDTO.setZip(jsonObject.getString("zip"));
        addressDTO.setTown(jsonObject.getString("town"));
        addressDTO.setCountry(jsonObject.getString("country"));
        return addressDTO;
    }
}
