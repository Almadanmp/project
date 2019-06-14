package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.mappers.RoomMinimalMapper;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.*;

@Service
public class EnergyGridRoomService implements pt.ipp.isep.dei.project.dddplaceholders.Service {

    private static final String BUILDER = "---------------\n";
    @Autowired
    EnergyGridRepository energyGridRepository;
    @Autowired
    RoomRepository roomRepository;

    public List<Room> getRoomList(EnergyGrid energyGrid) {
        List<Room> gridRooms = new ArrayList<>();
        for (String id : energyGrid.getRoomIdList()) {
            List<Room> rooms = roomRepository.getAllRooms();
            for (Room room : rooms) {
                if (id.equals(room.getId())) {
                    gridRooms.add(room);
                }
            }
        }
        return gridRooms;
    }

    public List<Room> getRoomListNotInGrid(EnergyGrid energyGrid) {
        List<Room> rooms = roomRepository.getAllRooms(); //todos
        List<String> unWantedRooms = energyGrid.getRoomIdList();
        for (String s : unWantedRooms) {
            Optional<Room> room = roomRepository.findRoomByID(s);
            room.ifPresent(rooms::remove);
        }
        return rooms;
    }

    public List<String> getEnergyGridIdAccordingToRoomPosition(EnergyGrid energyGrid, List<Room> rooms) {
        List<EnergyGrid> grids = energyGridRepository.getAllGrids();
        grids.remove(energyGrid);
        List<String> gridIds = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            for (EnergyGrid e : grids) {
                if (e.getRoomIdList().contains(rooms.get(i).getId())) {
                    gridIds.add(e.getName());
                }
            }
        }
        return gridIds;
    }

    /**
     * Method accesses the sum of nominal powers of all rooms and devices connected to a grid..
     *
     * @return is the sum of nominal powers of all rooms and devices connected to a grid.
     */
    public double getNominalPower(EnergyGrid energyGrid) {
        double result = 0;
        List<Room> finalRooms = getRoomList(energyGrid);
        for (Room r : finalRooms) {
            result += r.getNominalPower();
        }
        return result;
    }

    /**
     * Setter for a new room to be added to the energy grid.
     *
     * @param roomToAdd Room to be added to the list of rooms in the energy grid.
     * @return returns true if the room is actually added to the energy grid.
     */

    public boolean addRoom(EnergyGrid energyGrid, Room roomToAdd) {
        List<String> roomIds = energyGrid.getRoomIdList();
        if (roomIds.contains(roomToAdd.getId())) {
            return false;
        }
        energyGrid.addRoomId(roomToAdd.getId());
        return true;
    }

    /**
     * This method receives an index as parameter and gets a room from energy grid's room list.
     *
     * @param index the index of the Room.
     * @return returns room that corresponds to index.
     */
    public Room getRoom(EnergyGrid energyGrid, int index) {
        List<Room> finalRooms = getRoomList(energyGrid);
        if (finalRooms.isEmpty()) {
            throw new IndexOutOfBoundsException("The room list is empty.");
        }
        return finalRooms.get(index);
    }

    /**
     * String Builder of the RoomList.
     *
     * @return a String of the Rooms in the RoomList.
     */
    public String buildEnergyGridRoomsAsString(EnergyGrid energyGrid) {
        List<Room> finalRooms = getRoomList(energyGrid);
        StringBuilder result = new StringBuilder(BUILDER);
        if (finalRooms.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < finalRooms.size(); i++) {
            Room aux = finalRooms.get(i);
            result.append(i).append(") ID: ").append(aux.getId()).append(" |").
                    append(" Description: ").append(aux.getDescription()).append(" |\n");
        }
        result.append(BUILDER);
        return result.toString();
    }

    /**
     * Method gets all devices associated to energy grid
     *
     * @return energy grid's entire DeviceList
     */
    public DeviceList getDeviceList(EnergyGrid energyGrid) {
        List<Room> finalRooms = getRoomList(energyGrid);
        DeviceList devices = new DeviceList();
        for (Room r : finalRooms) {
            devices.addDevicesToThisDeviceList(r.getDeviceList());
        }
        return devices;
    }

    /**
     * Method goes through all rooms associated to energy grid and returns
     * the number of devices in each room
     *
     * @return energy grid's associated devices as int
     */
    public int getNumberOfDevices(EnergyGrid energyGrid) {
        List<Room> finalRooms = getRoomList(energyGrid);
        int sum = 0;
        for (Room r : finalRooms) {
            sum += r.getNumberOfDevices();
        }
        return sum;
    }

    /**
     * This method receives an index as parameter and gets a device from energy grid's device list.
     *
     * @param index the index of the device
     * @return returns device that corresponds to index.
     */
    public Device getDeviceByIndex(int index, EnergyGrid energyGrid) {
        DeviceList deviceList = this.getDeviceList(energyGrid);
        if (deviceList.isEmpty()) {
            throw new IndexOutOfBoundsException("The device list is empty.");
        }
        return deviceList.get(index);
    }

    /**
     * Method to removeGeographicArea a room from the energy grid.
     *
     * @param room the room we want to removeGeographicArea from the energy grid.
     * @return returns true if the room is successfully removed from the energy grid.
     */
    public boolean removeRoom(EnergyGrid energyGrid, Room room) {
        List<String> roomIds = energyGrid.getRoomIdList();
        if (roomIds.contains(room.getId())) {
            energyGrid.removeRoomId(room.getId());
            return true;
        }
        return false;
    }


    boolean removeRoomById(EnergyGrid energyGrid, String roomID) {
        List<Room> finalRooms = getRoomList(energyGrid);
        for (Room r : finalRooms) {
            if (r.getId().equals(roomID)) {
                finalRooms.remove(r);
                energyGrid.removeRoomId(r.getId());
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a string displaying the name of the devices in the energy grid.
     *
     * @return returns a string displaying the name of the devices in the energy grid.
     */
    public String buildDeviceListString(EnergyGrid energyGrid) {
        return this.getDeviceList(energyGrid).buildString();
    }

    /**
     * Creates a String with the device index, device type, device name and the room in which the device is contained.
     *
     * @return a String with the device index, device type, device name and the room in which the device is contained.
     */
    public String buildDeviceListWithTypeString(EnergyGrid energyGrid) {
        List<Room> finalRooms = getRoomList(energyGrid);
        StringBuilder result = new StringBuilder(BUILDER);
        for (Room r : finalRooms) {
            DeviceList devices = r.getDeviceList();
            for (int i = 0; i < devices.size(); i++) {
                String deviceType = devices.get(i).getType();
                result.append(r.buildDevicesStringByType(deviceType));
            }
        }
        result.append(BUILDER);
        return result.toString();
    }


    /**
     * US722 As a Power User [or Administrator], I want the sum of the energy consumption of all energy-metered rooms
     * in the grid in the interval.
     *
     * @param initialDate for metering period.
     * @param finalDate   for metering period.
     * @return total metered energy consumption of a grid in a given time interval.
     */
    public double getGridConsumptionInInterval(EnergyGrid energyGrid, Date initialDate, Date finalDate) {
        List<Room> finalRooms = getRoomList(energyGrid);
        double consumption = 0;
        for (Room r : finalRooms) {
            consumption += r.getConsumptionInInterval(initialDate, finalDate);

        }
        return consumption;
    }

    /**
     * This method goes through every room in list and returns logs contained in interval given.
     *
     * @param startDate the initial date of the interval
     * @param endDate   the final date of the interval
     * @return log list with every log contained in interval given.
     */
    public LogList getLogsInInterval(EnergyGrid energyGrid, Date startDate, Date endDate) {
        List<Room> finalRooms = getRoomList(energyGrid);
        LogList logsInInterval = new LogList();
        for (Room r : finalRooms) {
            logsInInterval.addLogList(r.getLogsInInterval(startDate, endDate));
        }
        return logsInInterval;
    }

    /**
     * This method checks if energy grid's Device List is empty.
     *
     * @return true if energy grid's DeviceList is empty, false otherwise.
     **/
    public boolean isDeviceListEmpty(EnergyGrid energyGrid) {
        List<Room> finalRooms = getRoomList(energyGrid);
        int sum = 0;
        for (Room r : finalRooms) {
            if (r.getDeviceListSize() != 0) {
                sum = sum + 1;
            }
        }
        return sum <= 0;
    }

    /**
     * Method for US 147 - As an Administrator, I want to attach a room to a house grid, so that the room’s power and
     * energy consumption is included in that grid.
     * This method adds a room to the grid and persists in the database.
     *
     * @param roomId   is the roomId we want to add to the grid
     * @param gridName is the name of the grid we want to add the room to
     * @return true if the room was successfully added, false otherwise
     */
    public boolean attachRoomToGrid(String roomId, String gridName) {
        EnergyGrid energyGrid = energyGridRepository.getById(gridName);
        Room room = getRoomById(roomId);
        if (addRoom(energyGrid, room)) {
            energyGridRepository.addGrid(energyGrid);
            return true;
        }
        return false;
    }

    Room getRoomById(String id) {
        Optional<Room> value = roomRepository.findRoomByID(id);
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("ERROR: There is no Room with the selected ID.");
    }

    /**
     * This method detaches a Room assigned to an Energy Grid from that Grid; it preserves all of the room's
     * characteristics, and the room is maintained in the repository.
     *
     * @param roomID is the ID of the room we want to remove from the Grid, as it exists in the database.
     * @param gridID is the ID of the grid that contains the room we want to remove.
     * @return is true if the room was successfully removed; is false if the grid didn't contain a room with the given
     * ID.
     * @throws NoSuchElementException this exception is thrown if the database doesn't contain an Energy Grid with
     *                                the given ID.
     */

    public boolean removeRoomFromGrid(String roomID, String gridID) {
        EnergyGrid grid = energyGridRepository.getById(gridID);
        boolean result = removeRoomById(grid, roomID);
        energyGridRepository.addGrid(grid);
        return result;
    }

    /**
     * Method for US 145 - As an Administrator, I want to have a list of existing rooms attached to a house grid, so
     * that I can attach/detach rooms from it.
     * This method returns a List of Rooms Dto Web from a grid.
     *
     * @param gridId is the name of the grid.
     * @return a List of Rooms Dto Web from a grid.
     */
    public List<RoomDTOMinimal> getRoomsDtoWebInGrid(String gridId) {
        EnergyGrid energyGrid = energyGridRepository.getById(gridId);
        List<Room> roomList = getRoomList(energyGrid);
        return RoomMinimalMapper.objectsToDtosWeb(roomList);
    }

    /**
     * Method for US 145 - As an Administrator, I want to have a list of existing rooms attached to a house grid, so
     * that I can attach/detach rooms from it.
     * This method returns a List of Rooms Dto Web from a grid.
     *
     * @param gridId is the name of the grid.
     * @return a List of Rooms Dto Web from a grid.
     */
    public List<RoomDTOMinimal> getRoomsDtoWebNotInGrid(String gridId) {
        EnergyGrid energyGrid = energyGridRepository.getById(gridId);
        List<Room> roomList = getRoomListNotInGrid(energyGrid);
        List<String> gridIds = getEnergyGridIdAccordingToRoomPosition(energyGrid, roomList);
        List<RoomDTOMinimal> result = RoomMinimalMapper.objectsToDtosWeb(roomList);
        for (int i = 0; i < result.size(); i++){
            result.get(i).setGridID(gridIds.get(i));
        }
        return result;
    }


    /**
     * US 147
     * Method that returns a RoomDtoWeb from a given id, with a given Grid Id also.
     *
     * @param gridId is the grid where the room is at.
     * @param roomId is the room id.
     * @return a RoomDtoWeb from a given id, with a given Grid Id also.
     */
    public RoomDTOMinimal getMinimalRoomDTOById(String gridId, String roomId) {
        List<RoomDTOMinimal> list = getRoomsDtoWebInGrid(gridId);
        for (RoomDTOMinimal r : list) {
            if (r.getName().equals(roomId)) {
                return r;
            }
        }
        return null;
    }

}
