package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

public class US08Controller {

    private GeographicArea mGeographicAreaContained;
    private GeographicArea mGeographicAreaContainer;
    private GeographicAreaList mGeographicAreaList;


    public US08Controller(GeographicAreaList list) {
        this.mGeographicAreaList = list;
    }

    GeographicArea getmGeographicAreaContained() {
        return this.mGeographicAreaContained;
    }

    GeographicArea getmGeographicAreaContainer() {
        return this.mGeographicAreaContainer;
    }

    /**
     * This method define the GeographicAreas Container and Contained
     */

    public boolean matchGeographicAreas(String nameOfAreaContained, String nameOfAreaContainer) {
        if (mGeographicAreaList.checkIfListIsValid()) {
            for (GeographicArea ga : mGeographicAreaList.getGeographicAreaList()) {
                if (ga.getName().equals(nameOfAreaContained)) {
                    mGeographicAreaContained = ga;
                }
                if (ga.getName().equals(nameOfAreaContainer)) {
                    mGeographicAreaContainer = ga;
                }
            }
            return mGeographicAreaContained != null && mGeographicAreaContainer != null;
        }
        return false;
    }

    /**
     * This methods checks if one area (AreaContained) is contained in another area (AreaContainer)
     */
    public boolean seeIfItsContained() {
        return mGeographicAreaContained.checkIfAreaIsContained(mGeographicAreaContained, mGeographicAreaContainer);
    }
}