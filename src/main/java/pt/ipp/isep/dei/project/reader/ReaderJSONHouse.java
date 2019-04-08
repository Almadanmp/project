package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.dto.AddressDTO;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.HouseDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReaderJSONHouse implements Reader {
    private List<RoomDTO> roomDTOS;

    public HouseDTO readFile(String filePath) {
        HouseDTO houseDTO = new HouseDTO();
        try {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            JSONArray roomList = object.getJSONArray("room");
            JSONObject address = object.getJSONObject("adress");
            JSONArray gridList = object.getJSONArray("grid");
            List<RoomDTO> roomDTOList = readRoomsJSON(roomList);
            AddressDTO addressDTO = readAddressJSON(address);
            List<EnergyGridDTO> energyGridDTOList = readGridsJSON(gridList);
            houseDTO.setAddress(addressDTO);
            houseDTO.setRoomList(roomDTOList);
            houseDTO.setEnergyGridList(energyGridDTOList);
            return houseDTO;
        } catch (FileNotFoundException e) {
            UtilsUI.printMessage("The file wasn't found.");
        }
        return new HouseDTO();
    }

    public List<RoomDTO> readRoomsJSON(JSONArray rooms) {
        List<RoomDTO> roomArray = new ArrayList<>();
        for (int i = 0; i < rooms.length(); i++) {
            JSONObject room = rooms.getJSONObject(i);
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

    public List<EnergyGridDTO> readGridsJSON(JSONArray energyGrid) {
        List<EnergyGridDTO> energyGridDTOList = new ArrayList<>();
        for (int i = 0; i < energyGrid.length(); i++) {
            List<RoomDTO> roomDTOList = new ArrayList<>();
            int e = energyGrid.getJSONObject(i).getJSONArray("rooms").length();
            JSONObject grid = energyGrid.getJSONObject(i);
            String gridName = grid.getString("name");
            EnergyGridDTO energyGridObject = new EnergyGridDTO();
            energyGridObject.setName(gridName);
            for (int y = 0; y < e; y++) {
                Object jsonArray = grid.getJSONArray("rooms").get(y);
                String gridRoomName = jsonArray.toString();
                for (int x = 0; x < roomDTOS.size(); x++) {
                    if (roomDTOS.get(x).getName().equals(gridRoomName)) {
                        RoomDTO roomDTO = roomDTOS.get(x);
                        roomDTOList.add(roomDTO);
                    }
                }
            }
            energyGridObject.setRoomDTOS(roomDTOList);
            energyGridDTOList.add(energyGridObject);
        }
        return energyGridDTOList;
    }

    public AddressDTO readAddressJSON(JSONObject address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getString("street"));
        addressDTO.setNumber(address.getString("number"));
        addressDTO.setZip(address.getString("zip"));
        addressDTO.setTown(address.getString("town"));
        addressDTO.setCountry(address.getString("country"));
        return addressDTO;
    }
}
