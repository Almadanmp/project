package Sprint_0;

public class Geographic_Area_Methods {

    /**converte PARA graus radianos
     */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**converte DE graus radianos
     */
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    /**
     *Math.sin e Math.cos, juntamente com a função deg2rad, converte os valores cartesianos em radianos para poder calcular o arcoseno
     *Math.acos corresponde ao arcoseno, ou seja, seno com potencia de -1.
     * rad2deg devolve o resultado em graus
     *
     * @return devolve o resultado devidamente convertido para kilometros
     */
    public static double getLinearDistanceBetweenTwoPoints(Geographic_Area parameter1, Geographic_Area parameter2) {
        Local l1 = parameter1.getLocal();
        Local l2 = parameter2.getLocal();
        double latitude1 = l1.getLatitude();
        double latitude2 = l2.getLatitude();
        double longitude1 = l1.getLongitude();
        double longitude2 = l2.getLongitude();
        double theta = longitude1 - longitude2;
        double dist = Math.sin(deg2rad(latitude1)) * Math.sin(deg2rad(latitude2)) + Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        return (dist * 60 * 1.1515 * 1.609344);
    }

    public static double getLinearDistanceBetweenTwoSensors(Sensor sensor1, Sensor sensor2) {
        Local l1 = sensor1.getLocal();
        Local l2 = sensor2.getLocal();
        double latitude1 = l1.getLatitude();
        double latitude2 = l2.getLatitude();
        double longitude1 = l1.getLongitude();
        double longitude2 = l2.getLongitude();
        double theta = longitude1 - longitude2;
        double dist = Math.sin(deg2rad(latitude1)) * Math.sin(deg2rad(latitude2)) + Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        return (dist * 60 * 1.1515 * 1.609344);
    }
}
