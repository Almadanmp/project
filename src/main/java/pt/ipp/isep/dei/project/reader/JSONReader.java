package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.SensorDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JSONReader {

    /**
     * This method reads a .json file from its absolute filepath and returns an array of DTO objects formed
     * from the data in the file.
     * @param filePath is the absolute filepath of the .json file in the system.
     * @return is an array of data transfer geographic area objects created with the data in the .json file.
     */

    public GeographicAreaDTO[] readFile(String filePath) {
        try {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(stream);
            JSONObject object = new JSONObject(tokener);
            JSONObject areaList = object.getJSONObject("geographical_area_list");
            JSONArray geoAreas = areaList.getJSONArray("geographical_area");
            GeographicAreaDTO[] geographicAreasArray;
            geographicAreasArray = readGeoAreas(geoAreas);
            return geographicAreasArray;
        } catch (FileNotFoundException e) {
            System.out.println("The file wasn't found.");
        }
        return new GeographicAreaDTO[0];
    }

    /**
     * Is the method that loads geographic areas from the .json file.
     * @param geoAreas is the JSONArray corresponding to the list of geographic areas in the .json file.
     * @return is an array of data transfer geographic area objects created with the data in the JSON Array provided.
     */

    private GeographicAreaDTO[] readGeoAreas(JSONArray geoAreas) {
        GeographicAreaDTO[] geographicAreasArray = new GeographicAreaDTO[geoAreas.length()];
        for (int i = 0; i < geoAreas.length(); i++) {
            JSONObject area = geoAreas.getJSONObject(i);
            JSONObject local = geoAreas.getJSONObject(i).getJSONObject("location");
            GeographicAreaDTO areaDTO = new GeographicAreaDTO();
            areaDTO.setId(area.getString("id"));
            areaDTO.setDescription(area.getString("description"));
            areaDTO.setTypeArea(area.getString("type"));
            areaDTO.setWidth(area.getDouble("width"));
            areaDTO.setLength(area.getDouble("length"));
            areaDTO.setLatitudeGeoAreaDTO(local.getDouble("latitude"));
            areaDTO.setLongitudeGeoAreaDTO(local.getDouble("longitude"));
            areaDTO.setAltitudeGeoAreaDTO(local.getDouble("altitude"));
            geographicAreasArray[i] = areaDTO;
            JSONArray areaSensors = area.getJSONArray("area_sensor");
            SensorDTO[] areaSensorsArray = readAreaSensors(areaSensors);
            addSensorDTOStoAreaDTO(areaSensorsArray, areaDTO);
        }
        return geographicAreasArray;
    }

    /**
     * Is the method that loads sensors from a list of sensors in the .json file.
     * @param areaSensors is the array of sensors contained in the .json file, usually the list of sensors
     *                    that belong to an area.
     * @return is an array of data transfer sensor objects created with the data in the given JSON Array.
     */

    private SensorDTO[] readAreaSensors(JSONArray areaSensors) {
        SensorDTO[] result = new SensorDTO[areaSensors.length()];
        for (int k = 0; k < areaSensors.length(); k++) {
            JSONObject areaSensor = areaSensors.getJSONObject(k);
            JSONObject sensor = areaSensor.getJSONObject("sensor");
            String sensorId = sensor.getString("id");
            String sensorName = sensor.getString("name");
            String sensorDate = sensor.getString("start_date");
            String sensorType = sensor.getString("type");
            String sensorUnits = sensor.getString("units");
            JSONObject sensorLocal = areaSensor.getJSONObject("location");
            double sensorLatitude = sensorLocal.getDouble("latitude");
            double sensorLongitude = sensorLocal.getDouble("longitude");
            double sensorAltitude = sensorLocal.getDouble("altitude");
            SensorDTO sensorDTO = new SensorDTO();
            sensorDTO.setId(sensorId);
            sensorDTO.setName(sensorName);
            sensorDTO.setDateStartedFunctioning(sensorDate);
            sensorDTO.setTypeSensor(sensorType);
            sensorDTO.setUnits(sensorUnits);
            sensorDTO.setLatitude(sensorLatitude);
            sensorDTO.setLongitude(sensorLongitude);
            sensorDTO.setAltitude(sensorAltitude);
            result[k] = sensorDTO;
        }
        return result;
    }

    /**
     * Method that adds all the sensorDTOs in an array to a GeographicAreaDTO.
     * @param sensorsToAdd is the array containing all the sensor DTOs that we want to add somewhere.
     * @param area is the area to which we want to add the sensors.
     */

    private void addSensorDTOStoAreaDTO(SensorDTO[] sensorsToAdd, GeographicAreaDTO area) {
        for (SensorDTO sensor : sensorsToAdd) {
            area.addSensorDTO(sensor);
        }
    }
}
