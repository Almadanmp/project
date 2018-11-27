package Sprint_0;

public class Geographic_Area_Methods {

    public static double getLinearDistanceBetweenTwoPoints (Geographic_Area parameter1, Geographic_Area parameter2){
        Local l1 = parameter1.getLocal();
        Local l2 = parameter2.getLocal();
        double latitude1 = l1.getLatitude();
        double latitude2 = l2.getLatitude();
        double longitude1 = l1.getLongitude();
        double longitude2 = l2.getLongitude();
        return (Math.sqrt(Math.pow((latitude1 - latitude2),2) + (Math.pow((longitude1 - longitude2),2))));
    }
}
