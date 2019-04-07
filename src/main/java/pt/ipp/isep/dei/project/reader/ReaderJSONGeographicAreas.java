package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.AreaType;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorList;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.services.AreaSensorService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            return areaList.getJSONArray("geographical_area");
        } catch (FileNotFoundException e) {
            UtilsUI.printMessage("The file wasn't found.");
        }
        return new JSONArray();
    }

    /**
     * This method reads a .json file from its absolute filepath and returns an array of DTO objects formed
     * from the data in the file.
     *
     * @param filePath is the absolute filepath of the .json file in the system.
     * @param list     is the list of Geographic areas that comes from Main, since we still don't have a database, to which
     *                 we want to addWithoutPersisting the imported geographic areas.
     * @return is an array of data transfer geographic area objects created with the data in the .json file.
     */

    public int readJSONFileAndAddGeoAreas(String filePath, GeographicAreaList list, AreaSensorService service) {
        ReaderJSONGeographicAreas reader = new ReaderJSONGeographicAreas();
        ReaderController ctrl = new ReaderController(service);
        JSONArray geoAreas = reader.readFile(filePath);
        GeographicArea[] geographicAreasArray;
        geographicAreasArray = readGeoAreasJSON(geoAreas);
        return ctrl.addGeoAreaArrayToList(geographicAreasArray, list);
    }
    /**
     * Is the method that loads geographic areas from the .json file.
     *
     * @param geoAreas is the JSONArray corresponding to the list of geographic areas in the .json file.
     * @return is an array of data transfer geographic area objects created with the data in the JSON Array provided.
     */

    private GeographicArea[] readGeoAreasJSON(JSONArray geoAreas) {
        GeographicArea[] geographicAreasArray = new GeographicArea[geoAreas.length()];
        for (int i = 0; i < geoAreas.length(); i++) {
            JSONObject area = geoAreas.getJSONObject(i);
            JSONObject local = geoAreas.getJSONObject(i).getJSONObject("location");
            String areaID = area.getString("id");
            String areaDescription = area.getString("description");
            AreaType areaType = new AreaType(area.getString("type"));
            double areaWidth = area.getDouble("width");
            double areaLength = (area.getDouble("length"));
            double areaLatitude = local.getDouble(LATITUDE);
            double areaLongitude = local.getDouble(LONGITUDE);
            double areaAltitude = local.getDouble(ALTITUDE);
            Local location = new Local(areaLatitude, areaLongitude, areaAltitude);
            GeographicArea areaObject = new GeographicArea(areaID, areaType, areaWidth, areaLength, location);
            areaObject.setDescription(areaDescription);
            geographicAreasArray[i] = areaObject;
            JSONArray areaSensors = area.getJSONArray("area_sensor");
            AreaSensorList areaSensorsList = readAreaSensorsJSON(areaSensors);
            areaObject.setSensorList(areaSensorsList);
        }
        return geographicAreasArray;
    }

    /**
     * Is the method that loads sensors from a list of sensors in the .json file.
     *
     * @param areaSensors is the array of sensors contained in the .json file, usually the list of sensors
     *                    that belong to an area.
     * @return is an array of data transfer sensor objects created with the data in the given JSON Array.
     */

    private AreaSensorList readAreaSensorsJSON(JSONArray areaSensors) {
        List<AreaSensor> result = new ArrayList<>();
        AreaSensorList areaSensorListObject = new AreaSensorList();
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
            SensorType type = new SensorType(sensorType, sensorUnits);
            JSONObject sensorLocal = areaSensor.getJSONObject("location");
            double sensorLatitude = sensorLocal.getDouble(LATITUDE);
            double sensorLongitude = sensorLocal.getDouble(LONGITUDE);
            double sensorAltitude = sensorLocal.getDouble(ALTITUDE);
            Local local = new Local(sensorLatitude,
                    sensorLongitude, sensorAltitude);
            AreaSensor areaSensorObject = new AreaSensor(sensorId, sensorName, type, local, date);
            areaSensorObject.setAreaSensorList(areaSensorListObject);
            result.add(areaSensorObject);
            entriesChecked++;
        }
        areaSensorListObject.setAreaSensors(result);
        return areaSensorListObject;
    }

}