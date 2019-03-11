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

    private void addSensorDTOStoAreaDTO(SensorDTO[] sensorsToAdd, GeographicAreaDTO area) {
        for (SensorDTO sensor : sensorsToAdd) {
            area.addSensorDTO(sensor);
        }
    }
}
