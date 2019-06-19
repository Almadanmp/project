package pt.ipp.isep.dei.project.io.ui.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReaderJSONGeographicAreas implements Reader {

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ALTITUDE = "altitude";

    public JSONArray readFile(String filePath) {
        try {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            JSONObject areaList = object.getJSONObject("geographical_area_list");
            JSONArray result = areaList.getJSONArray("geographical_area");
            stream.close();
            return result;
        } catch (FileNotFoundException e) {
            UtilsUI.printMessage("The file wasn't found.");
        } catch (IOException e) {
            UtilsUI.printMessage("Unable to close file.");
        }
        return new JSONArray();
    }

    /**
     * This method reads a .json file from its absolute filepath and returns an array of DTO objects formed
     * from the data in the file.
     *
     * @param filePath                 is the absolute filepath of the .json file in the system.
     * @param geographicAreaRepository is the list of Geographic areas that comes from Main, since we still don't have a database, to which
     *                                 we want to addWithoutPersisting the imported geographic areas.
     * @return is an array of data transfer geographic area objects created with the data in the .json file.
     */

    public int readJSONFileAndAddGeoAreas(String filePath, GeographicAreaRepository geographicAreaRepository, SensorTypeRepository sensorTypeRepository, AreaTypeRepository areaTypeRepository) {
        JSONArray geoAreas = readFile(filePath);
        return readGeoAreasJSON(geoAreas, geographicAreaRepository, sensorTypeRepository, areaTypeRepository);
    }

    /**
     * Is the method that loads geographic areas from the .json file.
     *
     * @param geoAreas is the JSONArray corresponding to the list of geographic areas in the .json file.
     * @return is an array of data transfer geographic area objects created with the data in the JSON Array provided.
     */

    private int readGeoAreasJSON(JSONArray geoAreas, GeographicAreaRepository geographicAreaRepository, SensorTypeRepository sensorTypeRepository, AreaTypeRepository areaTypeRepository) {
        int result = 0;

        for (int i = 0; i < geoAreas.length(); i++) {
            JSONObject area = geoAreas.getJSONObject(i);
            JSONObject local = geoAreas.getJSONObject(i).getJSONObject("location");
            String areaID = area.getString("id");
            String areaDescription = area.getString("description");
            String areaType = (area.getString("type"));
            double areaWidth = area.getDouble("width");
            double areaLength = (area.getDouble("length"));
            double areaLatitude = local.getDouble(LATITUDE);
            double areaLongitude = local.getDouble(LONGITUDE);
            double areaAltitude = local.getDouble(ALTITUDE);
            Local location = new Local(areaLatitude, areaLongitude, areaAltitude);
            try {
                AreaType areaType1 = areaTypeRepository.getAreaTypeByName(areaType);
                GeographicArea areaObject = geographicAreaRepository.createGA(areaID, areaType1.getName(), areaWidth, areaLength, location);
                areaObject.setDescription(areaDescription);
                JSONArray areaSensors = area.getJSONArray("area_sensor");
                if (geographicAreaRepository.addAndPersistGA(areaObject)) {
                    result++;
                    readAreaSensorsJSON(areaSensors, areaObject, sensorTypeRepository);
                    geographicAreaRepository.updateGeoArea(areaObject);
                }
            } catch (IllegalArgumentException ignored) {
                ignored.getMessage();
            }
        }
        return result;
    }

    /**
     * Is the method that loads sensors from a list of sensors in the .json file.
     *
     * @param areaSensors is the array of sensors contained in the .json file, usually the list of sensors
     *                    that belong to an area.
     */

    private void readAreaSensorsJSON(JSONArray areaSensors, GeographicArea geographicArea, SensorTypeRepository sensorTypeRepository) {
        int entriesChecked = 0;
        while (entriesChecked < areaSensors.length()) {
            JSONObject areaSensor = areaSensors.getJSONObject(entriesChecked);
            JSONObject sensor = areaSensor.getJSONObject("sensor");
            String sensorId = sensor.getString("id");
            String sensorName = sensor.getString("name");
            String sensorDate = sensor.getString("start_date");
            SimpleDateFormat validDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = validDateFormat.parse(sensorDate);
            } catch (ParseException c) {
                entriesChecked++;
                continue;
            }
            String sensorType = sensor.getString("type");
            String sensorUnits = sensor.getString("units");
            JSONObject sensorLocal = areaSensor.getJSONObject("location");
            double sensorLatitude = sensorLocal.getDouble(LATITUDE);
            double sensorLongitude = sensorLocal.getDouble(LONGITUDE);
            double sensorAltitude = sensorLocal.getDouble(ALTITUDE);
            Local local = new Local(sensorLatitude,
                    sensorLongitude, sensorAltitude);
            try {
                sensorTypeRepository.getTypeSensorByName(sensorName, sensorUnits);
                AreaSensor sensorToAdd = geographicArea.createAreaSensor(sensorId, sensorName, sensorType, local, date);
                geographicArea.addSensor(sensorToAdd);
                entriesChecked++;

            } catch (IllegalArgumentException ignored) {
                ignored.getMessage();
            }
        }
    }

}