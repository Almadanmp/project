package Sprint_0;

/**
 * Defines the Type of the Sensor.
 */
public class TypeSensor {
    private String name;

    /**
     * Empty Constructor to always create an object that names the Tye of the Sensor.
     */
    public TypeSensor(String name) {
        setName(name);
    }


    /**
     * Setter Name
     * @param name of type of sensor
     */
    public void setName(String name) {
        if (isTypeSensorNameValid(name)){
            this.name = name;
        }
    }

    /**
     * Getter Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to restrain input name so they cant be null or empty
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */
    public static boolean isTypeSensorNameValid(String name) {
        if (name != null && !name.isEmpty()) {
                return true;
        }
        throw new IllegalArgumentException("Please Insert Valid Name");
    }

    /**
     * Specific Method
     * @param testSensor -
     * @return boolean
     */
    @Override
    public boolean equals(Object testSensor) {
        if (this == testSensor) {
            return true;
        }
        if (!(testSensor instanceof TypeSensor)) {
            return false;
        }
        TypeSensor typeSensor = (TypeSensor) testSensor;
          if(this.getName().equals(typeSensor.getName())){
            return true;
        }
        return false;
    }

    /**
     * Specific Method
     * @return
     */
    @Override
    public int hashCode(){
        return 1;
    }
}
