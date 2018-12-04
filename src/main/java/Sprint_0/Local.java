package Sprint_0;

public class Local {
    private double mLatitude;
    private double mLongitude;
    private double mAltitude;

    /**
     * Construtor Local(), com dois parâmetros : um double latitude e um double longitude.
     *  Criado para ser usado no método -> getLinearDistanceBetweenLocalsInKm(Local local1)
     * @param latitude do local
     * @param longitude do local
     */
    public Local(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    /**
     * Construtor Local(), com todos os parâmetros para definir uma localização.
     * @param latitude do local
     * @param longitude do local
     * @param altitude do local
     */
    public Local(double latitude, double longitude, double altitude) {
        setLatitude(latitude);
        setLongitude(longitude);
        setAltitude(altitude);
    }

    /**
     * Setter Altitude
     * @param altitude do local
     */
    public void setAltitude(double altitude) {
        this.mAltitude = altitude;
    }

    /**
     * Setter Latitude
     * @param latitude do local
     */
    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    /**
     * Setter Longitude
     * @param longitude do local
     */
    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    /**
     * Getter Latitude
     * @return Latitude Value
     */
    public double getLatitude() {
        return this.mLatitude;
    }

    /**
     * Getter Longitude
     * @return Longitude value
     */
    public double getLongitude() {
        return this.mLongitude;
    }

    /**
     * Getter Altitude
     * @return Altitude value
     */
    public double getAltitude() {
        return this.mAltitude;
    }

    /**Convert TO radians
     */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**Convert FROM radians
     */
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    /**
     * Método para Obter distância linear entre duas Localizações em Km.
     * Só é necessário construtor Local () com dois parâmetros - Latitude e Longitude
     * @param local1 Localization 1
     * @return linear distance from Localization 1 to Localization 2
     */
    public double getLinearDistanceBetweenLocalsInKm(Local local1) {
        double latitude1 = local1.getLatitude();
        double latitude2 = getLatitude();
        double longitude1 = local1.getLongitude();
        double longitude2 = getLongitude();
        double theta = longitude1 - longitude2;
        double dist = Math.sin(deg2rad(latitude1)) * Math.sin(deg2rad(latitude2)) + Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        return (dist * 60 * 1.1515 * 1.609344);
    }

    /**
     * Specific Method
     * @param testLocal -
     * @return -
     */
    @Override
    public boolean equals(Object testLocal) {
        if (this == testLocal) {
            return true;
        }
        if (!(testLocal instanceof Local)) {
            return false;
        }
        Local localVariable = (Local) testLocal;
        if (java.lang.Double.compare(this.mLatitude,localVariable.getLatitude())==0 && java.lang.Double.compare(this.mLongitude,localVariable.getLongitude())==0 ) {
            return true;
        }
        return false;
    }

    /**
     * Specific Method
     * @return -
     */
    @Override
    public int hashCode() {
        return 1;
    }
}
