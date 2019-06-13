package pt.ipp.isep.dei.project.dto;

public class AddressLocalGeographicAreaIdDTO {

    private Long motherAreaID;
    private String street;
    private String number;
    private String zip;
    private String town;
    private String country;
    private double latitude;
    private double longitude;
    private double altitude;

    public double getAltitude() {
        return altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public Long getMotherAreaID() {
        return motherAreaID;
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getTown() {
        return town;
    }

    public String getZip() {
        return zip;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setMotherAreaID(Long motherAreaID) {
        this.motherAreaID = motherAreaID;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AddressLocalGeographicAreaIdDTO)) {
            return false;
        }
        AddressLocalGeographicAreaIdDTO localVariable = (AddressLocalGeographicAreaIdDTO) obj;
        return (this.street.equals(localVariable.street) && this.town.equals(localVariable.town) &&
                this.zip.equals(localVariable.zip));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
