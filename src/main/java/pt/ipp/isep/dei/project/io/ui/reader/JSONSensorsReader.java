package pt.ipp.isep.dei.project.io.ui.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONSensorsReader implements Reader {
    /**
     * Method that returns all the valid sensors in a given .json file. It filters out sensors without proper roomIDs
     * and sensors with null elements.
     *
     * @param filepath is the path of the file where we want to import the sensors from.
     * @return is the list of HouseSensorDTOs that were imported.
     */
    public List<RoomSensorDTO> importSensors(String filepath, SensorTypeRepository sensorTypeRepository) {
        List<RoomSensorDTO> result = new ArrayList<>();
        JSONArray importedArray = readFile(filepath); // Imports the whole file as an array.
        for (int x = 0; x < importedArray.length(); x++) {
            JSONObject sensorToImport = importedArray.getJSONObject(x);
            String roomID = getRoomID(sensorToImport);
            if (roomID.equals("error")) { // If the room ID was wrongly provided in the file, skip to next sensor in the file.
                continue;
            }
            try {
                String sensorID = sensorToImport.getString("id");
                String sensorName = sensorToImport.getString("name");
                String sensorType = sensorToImport.getString("type");
                String sensorUnit = sensorToImport.getString("units");
                String objectDate = sensorToImport.getString("start_date");
                RoomSensorDTO importedSensor = new RoomSensorDTO();
                importedSensor.setName(sensorName);
                importedSensor.setDateStartedFunctioning(objectDate);
                sensorTypeRepository.getTypeSensorByName(sensorType, sensorUnit);
                importedSensor.setTypeSensor(sensorType);
                importedSensor.setRoomID(roomID);
                importedSensor.setId(sensorID);
                importedSensor.setActive(true);
                result.add(importedSensor);
            } catch (JSONException ok) {
                continue;
            }
        }
        return result;
    }

    /**
     * Method that reads the file by its filepath. Returns a JSON Array with all the data contained in the file (the highest
     * level in the structure hierarchy).
     *
     * @param filePath is the filepath that we want to check a file from.
     * @return is the JSON Array with all the data contained in the file.
     */
    public JSONArray readFile(String filePath) {
        try {
            File file = new File(filePath);
            String absolutePath = file.getAbsolutePath();
            File abFile = new File(absolutePath);
            InputStream stream = new FileInputStream(abFile);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            return getElementArray(object);
        } catch (NullPointerException | FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method that reads the file by its filepath. Returns a JSON Array with all the data contained in the file (the highest
     * level in the structure hierarchy).
     *
     * @param fileObject is the JSON Object we want to get an array from.
     * @return is the JSON Array with all the data contained in the file, or an exception.
     */
    private JSONArray getElementArray(JSONObject fileObject) {
        try {
            return fileObject.getJSONArray("sensor");
        } catch (JSONException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Method that retrieves the room's ID from the sensor in the file.
     *
     * @param sensorToImport is the JSON Object of the sensor to import.
     * @return is the String that is the roomID parameter in the file, if this parameter exists. If it doesn't (or is null),
     * the method returns an error convention.
     */
    private String getRoomID(JSONObject sensorToImport) {
        try {
            return sensorToImport.getString("room");
        } catch (JSONException ok) {
            return "error"; // Error convention is returned if the room isn't properly defined in the file. This is intended.
        }
    }
}
