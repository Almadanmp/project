package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReaderJSONGeographicAreas implements Reader{

    /**
     * This method reads a .json file from its absolute filepath and returns an array of DTO objects formed
     * from the data in the file.
     * @param filePath is the absolute filepath of the .json file in the system.
     * @param list is the list of Geographic areas that comes from Main, since we still don't have a database, to which
     *             we want to add the imported geographic areas.
     * @return is an array of data transfer geographic area objects created with the data in the .json file.
     */

    public int readFile(String filePath, GeographicAreaList list) {
        HouseConfigurationController controller = new HouseConfigurationController();
        try {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            JSONObject areaList = object.getJSONObject("geographical_area_list");
            JSONArray geoAreas = areaList.getJSONArray("geographical_area");
            GeographicArea[] geographicAreasArray;
            geographicAreasArray = readGeoAreas(geoAreas);
            return controller.addGeoAreasToList(geographicAreasArray, list);
        } catch (FileNotFoundException e) {
            UtilsUI.printMessage("The file wasn't found.");
        }
        return 0;
    }

    /**
     * Is the method that loads geographic areas from the .json file.
     * @param geoAreas is the JSONArray corresponding to the list of geographic areas in the .json file.
     * @return is an array of data transfer geographic area objects created with the data in the JSON Array provided.
     */

    private GeographicArea[] readGeoAreas(JSONArray geoAreas) {
        GeographicArea[] geographicAreasArray = new GeographicArea[geoAreas.length()];
        for (int i = 0; i < geoAreas.length(); i++) {
            JSONObject area = geoAreas.getJSONObject(i);
            JSONObject local = geoAreas.getJSONObject(i).getJSONObject("location");
            String areaID = area.getString("id");
            String areaDescription = area.getString("description");
            TypeArea areaType = new TypeArea(area.getString("type"));
            double areaWidth = area.getDouble("width");
            double areaLength = (area.getDouble("length"));
            double areaLatitude = local.getDouble("latitude");
            double areaLongitude = local.getDouble("longitude");
            double areaAltitude = local.getDouble("altitude");
            GeographicArea areaObject = new GeographicArea(areaID, areaType, areaWidth, areaLength, new Local(areaLatitude,
                    areaLongitude, areaAltitude));
            areaObject.setDescription(areaDescription);
            geographicAreasArray[i] = areaObject;
            JSONArray areaSensors = area.getJSONArray("area_sensor");
            List<Sensor> areaSensorsList = readAreaSensors(areaSensors);
            areaObject.addSensors(areaSensorsList);
        }
        return geographicAreasArray;
    }

    /**
     * Is the method that loads sensors from a list of sensors in the .json file.
     * @param areaSensors is the array of sensors contained in the .json file, usually the list of sensors
     *                    that belong to an area.
     * @return is an array of data transfer sensor objects created with the data in the given JSON Array.
     */

    private List<Sensor> readAreaSensors(JSONArray areaSensors) {
        List<Sensor> result = new ArrayList<>();
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
            double sensorLatitude = sensorLocal.getDouble("latitude");
            double sensorLongitude = sensorLocal.getDouble("longitude");
            double sensorAltitude = sensorLocal.getDouble("altitude");
            Sensor sensorObject = new Sensor(sensorId, sensorName, new TypeSensor(sensorType, sensorUnits), new Local(sensorLatitude,
                    sensorLongitude, sensorAltitude), date);
            result.add(sensorObject);
            entriesChecked++;
        }
        return result;
    }
}
