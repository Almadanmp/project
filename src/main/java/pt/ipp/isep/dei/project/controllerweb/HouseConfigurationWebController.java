package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;

@RestController
public class HouseConfigurationWebController {

    // USER STORY 101
//    /**
//     * Sets the address of a given House.
//     *
//     * @param street  the house's street.
//     * @param number  the house's number.
//     * @param town    The house's town.
//     * @param zip     The house's zip code.
//     * @param country The houses's country.
//     * @param house   is the house we're going to set the address of.
//     */
//    @RequestMapping(value = "/house_settings", method = RequestMethod.PATCH)
//    ResponseEntity<Object> setHouseAddress(@PathVariable("street") String street, @PathVariable("number") String number,
//                                           @PathVariable("zip") String zip, @PathVariable("town") String town, @PathVariable("country") String country, @PathVariable("house") House house) {
//        Address address = new Address(street, number, zip, town, country);
//        house.setAddress(address);
//        return new ResponseEntity<>("The address has been set", HttpStatus.OK);
//    }
//
//    /**
//     * @param latitude  is the latitude we're going to set.
//     * @param longitude is the longitude we're going to set.
//     * @param altitude  is the altitude we're going to set.
//     * @param house     is the house we're going to change the zip code to.
//     */
//    @RequestMapping(value = "/house_settings", method = RequestMethod.PATCH)
//    public void setHouseLocal(double latitude, double longitude, double altitude, House house) {
//        house.setLocation(latitude, longitude, altitude);
//    }
//
//    /**
//     * This method receives a house and a geographic area and sets this as
//     * the house mother area.
//     **/
//    @RequestMapping(value = "/house_settings", method = RequestMethod.PATCH)
//    public void setHouseMotherArea(House house, GeographicArea geographicArea) {
//        house.setMotherArea(geographicArea);
//    }

}
