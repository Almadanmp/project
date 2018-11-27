package Sprint_0;

import java.util.ArrayList;
import java.util.List;

public class Geographic_Area {
    private TypeArea typeArea;
    private Local local;
    private SensorList sensorList;

    public Geographic_Area(TypeArea typeArea, Local local) {
        setTypeArea(typeArea);
        setLocal(local);
    }

    public Geographic_Area(TypeArea typeArea, Local local, SensorList sensorList) {
        setTypeArea(typeArea);
        setLocal(local);
        setSensorList(sensorList);
    }

    public void setTypeArea(TypeArea typeArea) {
        this.typeArea = typeArea;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void setSensorList(SensorList listToSet){
        int numDuplicates = 0;
        for (int i = 0; i < listToSet.getSensorList().size(); i++){
            for (int j = i+1; j < listToSet.getSensorList().size(); j++){
                if (listToSet.getSensorList().get(i).equals(listToSet.getSensorList().get(j))){
                    numDuplicates++;
                }
            }
        }
        if (numDuplicates==0){this.sensorList = listToSet;}
    }

    public TypeArea getTypeArea() {
        TypeArea result = this.typeArea;
        return result;
    }

    public Local getLocal() {
        Local result = this.local;
        return result;
    }

    public SensorList getSensorList() {
        SensorList result = this.sensorList;
        return result;
    }
}
