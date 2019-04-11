package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.dto.HouseSensorDTO;

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
     * @param filepath is the path of the file where we want to import the sensors from.
     * @return is the list of HouseSensorDTOs that were imported.
     */
    public List<HouseSensorDTO> importSensors(String filepath) {
        List<HouseSensorDTO> result = new ArrayList<>();
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
                HouseSensorDTO importedSensor = new HouseSensorDTO();
                importedSensor.setName(sensorName);
                importedSensor.setDateStartedFunctioning(objectDate);
                importedSensor.setTypeSensor(sensorType);
                importedSensor.setUnits(sensorUnit);
                importedSensor.setRoomID(roomID);
                importedSensor.setId(sensorID);
                importedSensor.setActive(true);
                result.add(importedSensor);
            }
            catch (NullPointerException ok){
                continue;
            }
        }
        return result;
    }

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

    private JSONArray getElementArray(JSONObject fileObject) {
        try {
            return fileObject.getJSONArray("sensor");
        } catch (JSONException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private String getRoomID(JSONObject sensorToImport) {
        try {
            return sensorToImport.getString("room");
        } catch (NullPointerException ok) {
            return "error"; // Error convention is returned if the room isn't properly defined in the file. This is intended.
        }
    }
}
