package pt.ipp.isep.dei.project.model;

/**
 * Class that represents the Address of a House .
 */

public class Address {
    private String street;
    private String zip;
    private String town;

    /**
     * Standard address constructor, used for creating addresses.
     *
     * @param street is the street.
     * @param zip    is the zip-code.
     * @param town   is the town.
     */
    public Address(String street, String zip, String town) {
        this.street = street;
        this.zip = zip;
        this.town = town;
    }

    /**
     * Standard getter method, to return the Street name.
     *
     * @return the string with the street name.
     */
    String getStreet() {
        return this.street;
    }

    /**
     * Standard getter method, to return the zip code.
     *
     * @return the string with the zip code.
     */
    String getZip() {
        return this.zip;
    }

    /**
     * Standard getter method, to return the Town name.
     *
     * @return the string with the town name.
     */
    String getTown() {
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
     * Standard setter method, to define the Town name.
     *
     * @param town the string with the town name.
     */
    void setTown(String town) {
        this.town = town;
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
