package Sprint_0;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the central class.
 */

public class Geographic_Area {
    private TypeArea typeArea;
    private Local local;
    private SensorList sensorList;


    // GeoArea constructors. The minimum amount of data for a GeoArea is a place and a type of area.
    // They can be made with or without a sensor list.

    /**
     * @param typeArea Determines the type of the GeoArea object - e.g. "Street", "City", etc.
     * @param local    Determines the central point of a GeoArea. Is a point, not an area.
     */

    public Geographic_Area(TypeArea typeArea, Local local) {
        setTypeArea(typeArea);
        setLocal(local);
    }

    public Geographic_Area(TypeArea typeArea, Local local, SensorList sensorList) {
        setTypeArea(typeArea);
        setLocal(local);
        setSensorList(sensorList);
    }

    // Setters and Getters for all the parameters.

    public void setTypeArea(TypeArea typeArea) {
        this.typeArea = typeArea;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * @param listToSet The condition inside the method determines that the listToSet will be refused if there are duplicate
     *                  sensors in it.
     * @return Returns true if the list was added. Returns false otherwise.
     */

    public boolean setSensorList(SensorList listToSet) {
        for (int i = 0; i < listToSet.getSensorList().size(); i++) {
            for (int j = i + 1; j < listToSet.getSensorList().size(); j++) {
                if (listToSet.getSensorList().get(i).equals(listToSet.getSensorList().get(j))) {
                    return false;
                }
            }
        }
        this.sensorList = listToSet;
        return true;
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
