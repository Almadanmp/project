package pt.ipp.isep.dei.project.dto;



public class LocalDTO {

    private double latitude;
    private double longitude;
    private double altitude;
    private long id;

    public LocalDTO(){}

    public LocalDTO(double latitude, double longitude, double altitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object testDTO) {
        if (this == testDTO) {
            return true;
        }
        if (!(testDTO instanceof LocalDTO)) {
            return false;
        }
        LocalDTO localVariable = (LocalDTO) testDTO;
        return (java.lang.Double.compare(this.id, localVariable.getId()) == 0 && java.lang.Double.compare(this.latitude, localVariable.getLatitude()) == 0 && java.lang.Double.compare(this.longitude, localVariable.getLongitude()) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}