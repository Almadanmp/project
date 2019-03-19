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
     * @param street is the street.
     * @param zip is the zip-code.
     * @param town is the town.
     */
    public Address(String street, String zip, String town) {
        this.street = street;
        this.zip = zip;
        this.town = town;
    }

    String getStreet() {
        return this.street;
    }

    String getZip() {
        return this.zip;
    }

    String getTown() {
        return this.town;
    }

    void setStreet(String street) {
        this.street = street;
    }

    void setZip(String zip) {
        this.zip = zip;
    }

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
