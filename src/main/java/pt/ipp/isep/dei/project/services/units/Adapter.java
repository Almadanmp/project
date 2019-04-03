package pt.ipp.isep.dei.project.services.units;

import java.io.IOException;

public final class Adapter {


    /**
     * This method is used to get a value in a previously defined unit and converts it into the system default unit.
     *
     * @param valueToConvert refers to the unit value.
     * @param unitToConvert is an instance of the Unit class we want to convert.
     * @return the value converted in the system default unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    public static double convertToSystemDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        return UnitHelper.convertToSystemDefault(valueToConvert,unitToConvert);
}

    /**
     * This method is used to get a value in a previously defined unit and converts it into the user default unit.
     *
     * @param valueToConvert refers to the unit value.
     * @param unitToConvert is an instance of the Unit class we want to convert.
     * @return the value converted in the user default unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    public static double convertToUserDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        return UnitHelper.convertToUserDefault(valueToConvert,unitToConvert);
    }

}
