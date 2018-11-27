package Sprint_0;

import java.net.Proxy;

public class TypeSensor {
    private String name;

    public TypeSensor(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        String result = this.name;
        return result;
    }

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

    @Override
    public int hashCode(){
        return 1;
    }
}
