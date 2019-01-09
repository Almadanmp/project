package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

public class GASettingsController {

    //GEOGRAPHIC AREA SETTINGS CONTROLLER  - SHARED METHODS//

    public List<Integer> matchTypeAreaIndexByString(String input, TypeAreaList typeAreaList) {
        return typeAreaList.matchGeographicAreaTypeIndexByString(input);
    }

    public String printTypeAreaElementsByIndex(List<Integer> listOfIndexesTypeGeographicAreas, TypeAreaList typeAreaList) {
        return typeAreaList.printGATypeElementsByIndex(listOfIndexesTypeGeographicAreas);
    }

    public String printTypeArea(TypeArea typeArea) {
        return typeArea.printTypeGeographicArea();
    }

    public String printGATypeList(TypeAreaList typeAreaList) {
        return typeAreaList.printGATypeWholeList(typeAreaList);
    }

    public String printGAList(GeographicAreaList geoAreaList) {
        return geoAreaList.printGaWholeList(geoAreaList);
    }



    /*
     * User Story 01
     * As a system administrator, I wish to define a new type of geographic area, so that later I can classify said geographic area.
     */

    /**
     * This method creates a new Type of Geographic Area and adds it to a List.
     *
     * @param input - the String name of the Type of Geographic Area.
     * @return true - the Type of Geographic Area was successfully created and added to a list;
     * false - the name is null.
     */
    public boolean createAndAddTypeAreaToList(String input, TypeAreaList typeAreaList) {
        return typeAreaList.newTAG(input);
    }

    /* User Story 02
     As a System Administrator I want to receive a list of all the previously stated Types of area.
     */

    public String getTypeAreaList(TypeAreaList typeAreaList) {
        return typeAreaList.printTypeAreaList();
    }

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

    /**
     * Method to add a new geographic area to a list of geographic areas
     *
     * @param newGeoList geographic area list to add the new geographic area
     * @param newName    input string for geographic area name
     * @param typeArea   input string for type area
     * @param latitude   input number for latitude
     * @param longitude  input number for longitude
     * @return success if a new GA is added, false otherwise
     */
    public boolean addNewGeoAreaToList(GeographicAreaList newGeoList, String newName, String typeArea, double latitude, double longitude, double length, double width) {
        if (newGeoList == null) {
            return false;
        }
        GeographicArea geoToAdd = new GeographicArea(newName, new TypeArea(typeArea), length, width, new Local(latitude, longitude));
        return newGeoList.addGeographicAreaToGeographicAreaList(geoToAdd);
    }

    /* USER STORY 04 -  As an Administrator, I want to get a list of existing geographical areas of a given type. */
    /**
     * Void Method to Set/Match a Geographic Area List with a Certain Type Area Given(String input).
     */
    public GeographicAreaList matchGAByTypeArea(GeographicAreaList geographicAreaList, TypeArea typeArea) {
        String typeAreaName = typeArea.getTypeOfGeographicArea();
        return geographicAreaList.matchGeographicAreaWithInputType(typeAreaName);
    }

    public String getTypeAreaName(TypeArea typeArea) {
        return typeArea.getTypeOfGeographicArea();
    }

    /*USER STORY 07 - As an Administrator, I want to add an existing geographical area to another one (e.g. add city of
    Porto to the district of Porto). */

    private GeographicArea mMotherArea;

    public GeographicArea matchGeoArea(String nameArea, GeographicAreaList geographicAreaList) {
        return geographicAreaList.matchGeoArea(nameArea);
    }

    public void setMotherArea(GeographicArea daughterArea, GeographicArea motherArea) {
        daughterArea.setMotherArea(motherArea);
        this.mMotherArea = motherArea;
    }

    GeographicArea getMotherArea() {
        return this.mMotherArea;

    }

    public String printGeographicAreaListNames(GeographicAreaList geographicAreaList) {
        return geographicAreaList.printGeoAreaList();
    }

    public boolean validateGeoArea(String ga, GeographicAreaList geographicAreaList) {

        return geographicAreaList.validateIfGeographicAreaToGeographicAreaList(ga);
    }

    /*USER STORY 08 - As an Administrator, I want to find out if a geographical area is included, directly
or indirectly, in another one. */

    private GeographicArea mGeographicAreaContained;
    private GeographicArea mGeographicAreaContainer;

    /**
     * This method define the GeographicAreas Container and Contained
     */
    public boolean matchGeographicAreas(String nameOfAreaContained, String nameOfAreaContainer, GeographicAreaList geographicAreaList) {
        if (geographicAreaList.checkIfListIsValid()) {
            for (GeographicArea ga : geographicAreaList.getGeographicAreaList()) {
                if (ga.getId().equals(nameOfAreaContained)) {
                    mGeographicAreaContained = ga;
                }
                if (ga.getId().equals(nameOfAreaContainer)) {
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
