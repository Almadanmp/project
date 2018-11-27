package Sprint_0;

public class Local {
    private double latitude;
    private double longitude;
    private double altitude;

    public Local(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Local(double latitude, double longitude, double altitude) {
        setLatitude(latitude);
        setLongitude(longitude);
        setAltitude (altitude);
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        double result = this.latitude;
        return result;
    }

    public double getLongitude() {
        double result = this.longitude;
        return result;
    }

    public double getAltitude() {
        double result = this.altitude;
        return result;
    }

    public double getLinearDistanceBetweenLocals(Local local1, Local local2){
        double firstLat = local1.getLatitude();
        double secondLat = local2.getLatitude();
        double firstLon = local1.getLongitude();
        double secondLon = local2.getLongitude();
        double linearDistance =  Math.sqrt(Math.pow(Math.abs(secondLat-firstLat),2)+Math.pow(Math.abs(secondLon-firstLon),2));
        return linearDistance;
    }

    public boolean equals(Object testLocal) {
        if (this == testLocal) {
            return true;
        }
        if (!(testLocal instanceof Local)) {
            return false;
        }
        Local localVariable = (Local) testLocal;
        if (localVariable.getLatitude() == this.latitude && localVariable.getLongitude() == this.longitude) {
            return true;
        }
        return false;
    }
}
