package Sprint_0;

import java.net.Proxy;

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
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter Name
     */
    public String getName() {
        String result = this.name;
        return result;
    }

    /**
     * Specific Method
     * @param testSensor
     * @return
     */
    @Override
    public boolean equals(Object testSensor) {
        if (this == testSensor){
            return true;
        }
        if (!(testSensor instanceof TypeSensor)){
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
