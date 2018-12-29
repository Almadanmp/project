package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

public class US07Controller {

    private GeographicAreaList mGeographicAreaList;
    private GeographicArea mMotherArea;


    public US07Controller(GeographicAreaList list) {
        this.mGeographicAreaList = list;
    }

    public GeographicArea matchGeoArea(String nameArea){
        return mGeographicAreaList.matchGeoArea(nameArea);
    }

    public void setMotherArea(GeographicArea daughterArea, GeographicArea motherArea){
        daughterArea.setMotherArea(motherArea);
        this.mMotherArea = motherArea;
    }

    public GeographicArea getMotherArea (){
        return this.mMotherArea;

    }

    public GeographicAreaList getGeographicAreaList() {
        return mGeographicAreaList;
    }

    public String printGeographicAreaListNames() {
        return mGeographicAreaList.printGeoAreaList();
    }

    public boolean validateGeoArea(String ga) {

        return mGeographicAreaList.validateIfGeographicAreaToGeographicAreaList(ga);
    }




}
