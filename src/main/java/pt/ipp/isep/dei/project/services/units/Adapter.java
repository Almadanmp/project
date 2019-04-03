package pt.ipp.isep.dei.project.services.units;

import java.io.IOException;

public class Adapter {


    public double convertToSystemDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        return UnitHelper.convertToSystemDefault(valueToConvert,unitToConvert);
}

    public double convertToUserDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        return UnitHelper.convertToUserDefault(valueToConvert,unitToConvert);
    }

}
