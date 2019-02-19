package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;


public class WaterHeaterSpec implements DeviceSpecs {
    public static final String VOLUME_OF_WATER = "Volume Of Water";
    public static final String HOT_WATER_TEMP = "Hot Water Temperature";
    public static final String COLD_WATER_TEMP = "Cold Water Temperature";
    public static final String PERFORMANCE_RATIO = "Performance Ratio";
    public static final String VOLUME_OF_WATER_HEAT = "Volume Of Water To Heat";


    private Double volumeOfWater;
    private Double hotWaterTemperature;
    private Double performanceRatio;
    private Double coldWaterTemperature;
    private Double volumeOfWaterToHeat;


    public WaterHeaterSpec() {
        coldWaterTemperature = 0.0;
        volumeOfWaterToHeat = 0.0;
    }


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(VOLUME_OF_WATER);
        result.add(HOT_WATER_TEMP);
        result.add(PERFORMANCE_RATIO);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case VOLUME_OF_WATER:
                return volumeOfWater;
            case HOT_WATER_TEMP:
                return hotWaterTemperature;
            case PERFORMANCE_RATIO:
                return performanceRatio;
            case COLD_WATER_TEMP:
                return coldWaterTemperature;
            case VOLUME_OF_WATER_HEAT:
                return volumeOfWaterToHeat;
            default:
                return 0.0;
        }
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case VOLUME_OF_WATER:
                return "L";
            case HOT_WATER_TEMP:
                return "ºC";
            case PERFORMANCE_RATIO:
                return "";
            case COLD_WATER_TEMP:
                return "ºC";
            case VOLUME_OF_WATER_HEAT:
                return "L";
            default:
                return false;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
        switch (attributeName) {
            case VOLUME_OF_WATER:
                if (attributeValue instanceof Double) {
                    this.volumeOfWater = (Double) attributeValue;
                    return true;
                }
                return false;
            case HOT_WATER_TEMP:
                if (attributeValue instanceof Double) {
                    this.hotWaterTemperature = (Double) attributeValue;
                    return true;
                }
                return false;
            case PERFORMANCE_RATIO:
                if (attributeValue instanceof Double) {
                    this.performanceRatio = (Double) attributeValue;
                    return true;
                }
                return false;
            case COLD_WATER_TEMP:
                if (attributeValue instanceof Double) {
                    this.coldWaterTemperature = (Double) attributeValue;
                    return true;
                }
                return false;
            case VOLUME_OF_WATER_HEAT:
                if (attributeValue instanceof Double) {
                    this.volumeOfWaterToHeat = (Double) attributeValue;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}
