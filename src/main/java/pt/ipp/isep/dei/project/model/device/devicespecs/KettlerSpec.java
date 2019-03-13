package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class KettlerSpec implements DeviceSpecs {

    public static final String COLD_WATER_TEMP = "Cold Water Temperature";
    public static final String VOLUME_WATER = "Volume Water To Heat";
    public static final String PERFORMANCE_RATIO = "Performance Ratio";
    private String invalidName = "The attribute name is invalid.";


    private double coldWaterTemp;
    private double volumeWater;
    private double performanceRatio;

    public KettlerSpec() {
        this.coldWaterTemp = 0;
        this.volumeWater = 0;
        this.performanceRatio = 0.95;
    }

    /**
     * This method returns a list of all class attributes names.
     *
     * @return list of strings containing all class attributes names.
     **/
    @Override
    public List<String> getAttributeNames() {
        List<String> attributeList = new ArrayList<>();
        attributeList.add(COLD_WATER_TEMP);
        attributeList.add(VOLUME_WATER);
        attributeList.add(PERFORMANCE_RATIO);
        return attributeList;
    }

    /**
     * This method receives a string of an attribute name
     * and returns the value correspondent to that name.
     *
     * @param attributeName a string of a class attribute's name.
     * @return attribute value object.
     **/
    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case COLD_WATER_TEMP:
                return this.coldWaterTemp;
            case VOLUME_WATER:
                return this.volumeWater;
            case PERFORMANCE_RATIO:
                return this.performanceRatio;
            default:
                throw new IllegalArgumentException(invalidName);
        }
    }

    /**
     * This method receives an attribute name and an object,
     * and sets the value object as a class parameter (which name corresponds to
     * the name given).
     *
     * @param attributeName a string of a class attribute's name.
     * @return true in case the value is set as parameter, false otherwise.
     **/
    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case COLD_WATER_TEMP:
                if (attributeValue instanceof Double) {
                    this.coldWaterTemp = (Double) attributeValue;
                    return true;
                }
                return false;
            case VOLUME_WATER:
                if (attributeValue instanceof Double) {
                    this.volumeWater = (Double) attributeValue;
                    return true;
                    }
                return false;
            case PERFORMANCE_RATIO:
                if (attributeValue instanceof Double) {
                    double value = (Double) attributeValue;
                    return setPerformanceRatio(value);
                }
                return false;
            default:
                throw new IllegalArgumentException(invalidName);
        }
    }

    /** This receives a value and sets as performance ratio attribute, in
     * case the value is suitable.
     *
     * @param value value as double to be set as performance ratio.
     * @return true in case the value is set, false otherwise.
     *
     * **/
    private boolean setPerformanceRatio(double value){
        if(isARatio(value)){
            this.performanceRatio = value;
            return true;
        }
        return false;
    }

    /** This method receives a  a value and checks if that value is a ratio.
     *
     * @param value value as double to be checked.
     * @return true in case it is a ratio, false otherwise.
     *
     * **/
    boolean isARatio(double value){
        return (value >= 0.0 && value <= 1.0);
    }

    /**
     * This method receives an attribute name and gets the attribute's with the given name
     * measurement unit.
     *
     * @param attributeName a string of a class attribute's name.
     * @return a string with the attribute's measurement unit.
     **/
    @Override
    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case COLD_WATER_TEMP:
                return "ºC";
            case VOLUME_WATER:
                return "L";
            case PERFORMANCE_RATIO:
                return "";
            default:
                throw new IllegalArgumentException(invalidName);
        }
    }
}
