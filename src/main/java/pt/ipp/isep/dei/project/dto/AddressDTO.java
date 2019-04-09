package pt.ipp.isep.dei.project.dto;

public class AddressDTO {
    private String street;
    private String number;
    private String zip;
    private String town;
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object testAddress) {
        if (this == testAddress) {
            return true;
        }
        if (!(testAddress instanceof AddressDTO)) {
            return false;
        }
        AddressDTO localVariable = (AddressDTO) testAddress;
        return (this.street.equals(localVariable.street) && this.town.equals(localVariable.town) &&
                this.zip.equals(localVariable.zip));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
