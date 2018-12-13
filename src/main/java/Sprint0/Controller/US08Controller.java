package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;

import java.util.List;

public class US08Controller {

    private GeographicArea mGeographicAreaContained;
    private GeographicArea mGeographicAreaContainer;
    private GeographicAreaList mGeographicAreaList;


    public US08Controller(GeographicAreaList list) {
        this.mGeographicAreaList = list;
    }

    public GeographicArea getmGeographicAreaContained() {
        return this.mGeographicAreaContained;
    }

    public GeographicArea getmGeographicAreaContainer() {
        return this.mGeographicAreaContainer;
    }

    /**
     * This method define the GeographicAreas Container and Contained
     *
     * @param nameOfAreaContained
     * @param nameOfAreaContainer
     * @return
     */
    public boolean matchGeographicAreas(String nameOfAreaContained, String nameOfAreaContainer) {
        if (checkIfListIsValid(mGeographicAreaList.getGeographicAreaList())) {
            for (GeographicArea ga1 : mGeographicAreaList.getGeographicAreaList()) {
                if (ga1.getName().equals(nameOfAreaContained)) {
                    mGeographicAreaContained = ga1;
                    for (GeographicArea ga2 : mGeographicAreaList.getGeographicAreaList()) {
                        if (ga2.getName().equals(nameOfAreaContainer)) {
                            mGeographicAreaContainer = ga2;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * This methods checks if one area (AreaContained) is contained in another area (AreaContainer)
     *
     * @return
     */
    public boolean seeIfItsContained() {
        return mGeographicAreaContained.checkIfAreaIsContained(mGeographicAreaContained, mGeographicAreaContainer);
    }


    /**
     * This method checks if the list exists
     *
     * @param values
     * @return
     */
    private boolean checkIfListIsValid(List<GeographicArea> values) {
        if (values == null || values.isEmpty()) {
            return false;
        }
        return true;
    }
}