package pt.ipp.isep.dei.project.model;

import javax.persistence.Embeddable;

/**
 * Class that represents the Address of a House .
 */
@Embeddable
public class Address {
    private String street;
    private String number;
    private String zip;
    private String town;
    private String country;


    /**
     * Standard address constructor, used for creating addresses.
     *
     * @param street is the street.
     * @param zip    is the zip-code.
     * @param town   is the town.
     */
    public Address(String street, String number, String zip, String town, String country) {
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.town = town;
        this.country = country;
    }

    /**
     * Standard getter method, to return the Street name.
     *
     * @return the string with the street name.
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Standard getter method, to return the number.
     *
     * @return the string with the number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Standard getter method, to return the zip code.
     *
     * @return the string with the zip code.
     */
    public String getZip() {
        return this.zip;
    }

    /**
     * Standard getter method, to return the Town name.
     *
     * @return the string with the town name.
     */
    public String getTown() {
        return this.town;
    }

    /**
     * Standard setter method, to define the Street name.
     *
     * @param street the string with the street name.
     */
    void setStreet(String street) {
        this.street = street;
    }

    /**
     * Standard setter method, to define the Zip code.
     *
     * @param zip the string with the zip code.
     */
    void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Standard setter method, to define the number.
     *
     * @param number the string with the number.
     */
    void setNumber(String number) {
        this.number = number;
    }

    /**
     * Standard setter method, to define the Town name.
     *
     * @param town the string with the town name.
     */
    void setTown(String town) {
        this.town = town;
    }

    /**
     * Standard getter method, to return the Country name.
     *
     * @return the string with the country name.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Standard setter method, to define the country name.
     *
     * @param country the string with the country name.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object testAddress) {
        if (this == testAddress) {
            return true;
        }
        if (!(testAddress instanceof Address)) {
            return false;
        }
        Address localVariable = (Address) testAddress;
        return (this.street.equals(localVariable.street) && this.town.equals(localVariable.town) &&
                this.zip.equals(localVariable.zip));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
