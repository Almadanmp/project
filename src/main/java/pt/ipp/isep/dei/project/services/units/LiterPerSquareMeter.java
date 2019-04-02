package pt.ipp.isep.dei.project.services.units;

import java.io.IOException;

import static pt.ipp.isep.dei.project.services.units.UnitHelper.*;

public class LiterPerSquareMeter implements RainfallUnit {


    public double toApplicationDefault(double valueToConvert) throws IOException {
        String defaultUnit = getApplicationRainfallDefault();
        return toDefaultRainfallUnit(defaultUnit, valueToConvert, this);
    }

    public double toUserDefault(double valueToConvert) throws IOException {
        String defaultUnit = getUserRainfallDefault();
        return toDefaultRainfallUnit(defaultUnit, valueToConvert, this);
    }

    public double toMillimeter(double volume) {
        return volume;
    }

    public double toLiterPerSquareMeter(double volume) {
        return volume;
    }

    public String buildString() {
        return "LiterPerSquareMeter";
    }
}
