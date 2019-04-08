package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.AddressDTO;
import pt.ipp.isep.dei.project.model.Address;

public final class AddressMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    private AddressMapper() {
    }

    /**
     * This is the method that converts Address's DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */

    public static Address dtoToObject(AddressDTO dtoToConvert) {
        // Update parameters

        String objectStreet = dtoToConvert.getStreet();

        String objectNumber = dtoToConvert.getNumber();

        String objectZip = dtoToConvert.getZip();

        String objectTown = dtoToConvert.getTown();

        String objectCountry = dtoToConvert.getCountry();


        // Create, update and return new object

        Address resultObject = new Address(objectStreet, objectNumber, objectZip, objectTown, objectCountry);


        return resultObject;
    }

    /**
     * This is the method that converts Address model objects into DTOs with the same data.
     *
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */

    public static AddressDTO objectToDTO(Address objectToConvert) {
        // Update parameters

        String dtoStreet = objectToConvert.getStreet();

        String dtoNumber = objectToConvert.getNumber();

        String dtoZip = objectToConvert.getZip();

        String dtoTown = objectToConvert.getTown();

        String dtoCountry = objectToConvert.getCountry();


        // Create, update and return new object

        AddressDTO resultDTO = new AddressDTO();
        resultDTO.setStreet(dtoStreet);
        resultDTO.setNumber(dtoNumber);
        resultDTO.setZip(dtoZip);
        resultDTO.setTown(dtoTown);
        resultDTO.setCountry(dtoCountry);

        return resultDTO;
    }
}
