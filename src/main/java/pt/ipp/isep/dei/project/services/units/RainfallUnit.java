package pt.ipp.isep.dei.project.services.units;

import java.io.IOException;

/**
 * Represents all temperature units.
 * Provides methods to transmute between units.
 */

interface RainfallUnit extends Unit {

    /**
     * Method to convert rainfall according to default Unit
     * @return rainfall in default unit
     */
    double toApplicationDefault(double valueToConvert) throws IOException;

    double toUserDefault(double valueToConvert) throws IOException;
    /**
     * Method to convert rainfall values to millimeter
     * @return rainfall in millimeter
     */
    double toMillimeter(double volume);

    /**
     * Method to convert rainfall values to LiterPerSquareMeter
     * @return rainfall in LiterPerSquareMeter
     */
    double toLiterPerSquareMeter(double volume);
}
