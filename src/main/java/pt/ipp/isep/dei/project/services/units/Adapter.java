package pt.ipp.isep.dei.project.services.units;

import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.io.ui.reader.wrapper.ReadingDTOWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Adapter {


    private Adapter() {
        throw new IllegalStateException("Utility class");
    }


    /**
     * This method is used to get a value in a previously defined unit and converts it into the system default unit.
     *
     * @param valueToConvert refers to the unit value.
     * @param unitToConvert  is an instance of the Unit class we want to convert.
     * @return the value converted in the system default unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    static double convertToSystemDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        return UnitHelper.convertToSystemDefault(valueToConvert, unitToConvert);
    }

    /**
     * This method is used to get a value in a previously defined unit and converts it into the user default unit.
     *
     * @param valueToConvert refers to the unit value.
     * @param unitToConvert  is an instance of the Unit class we want to convert.
     * @return the value converted in the user default unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    static double convertToUserDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        return UnitHelper.convertToUserDefault(valueToConvert, unitToConvert);
    }

    /**
     * This method receives a List of Reading DTO Wrappers and returns a HashMap that contains Reading DTO
     * with its corresponding sensorId.
     *
     * @param readingDTOWrapperList list of reading Dto wrappers
     * @return hashmap containing Reading DTOs with its corresponding sensor ID
     **/
    public static List<ReadingDTO> readingDTOWrapperConversion(List<ReadingDTOWrapper> readingDTOWrapperList) {
        List<ReadingDTO> finalList = new ArrayList<>();
        for (ReadingDTOWrapper wrapper : readingDTOWrapperList) {
            if (wrapper == null) {
                continue;
            }
            ReadingDTO readingDTO = new ReadingDTO();
            String startUnitString = wrapper.getUnit();
            Unit startUnit = UnitHelper.convertStringToUnit(startUnitString);
            double startValue = wrapper.getValue();
            try {
                double endValue = convertToSystemDefault(startValue, startUnit);
                readingDTO.setValue(endValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Unit defaultUnit = UnitHelper.convertUnitToSystemDefault(startUnit);

            readingDTO.setUnit(defaultUnit.buildString());
            readingDTO.setDate(wrapper.getDate());
            readingDTO.setSensorId(wrapper.getSensorId());
            if (!finalList.contains(readingDTO)) {
                finalList.add(readingDTO);
            }
        }
        return finalList;
    }

}
