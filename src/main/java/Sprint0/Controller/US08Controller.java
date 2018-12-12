package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;

import java.util.List;

public class US08Controller {

    private GeographicArea mGeographicAreaContained;
    private GeographicArea mGeographicAreaContainer;
    private GeographicAreaList mGeographicAreaList;


    public US08Controller(GeographicAreaList list){this.mGeographicAreaList = list;}

    public boolean matchGeographicAreas(String area1Name, String area2Name){
        if (checkIfListIsValid(mGeographicAreaList.getGeographicAreaList())) {
            for (GeographicArea ga1 : mGeographicAreaList.getGeographicAreaList()) {
                if (ga1.getName().equals(area1Name)) {
                    mGeographicAreaContained = ga1;
                    break;
                }
            }
            for (GeographicArea ga2 : mGeographicAreaList.getGeographicAreaList()) {
                        if (ga2.getName().equals(area2Name)) {
                            mGeographicAreaContainer = ga2;
                            return true;
                        }
                    }
        }return false;
    }

    public boolean seeIfAreasHaveVertices(){
        return mGeographicAreaContained.doGeographicAreasHaveVerticesDetermined(mGeographicAreaContained, mGeographicAreaContainer);
    }

    public boolean seeIfItsContained() {
        return mGeographicAreaContained.isAreaContainedInAnotherArea(mGeographicAreaContained, mGeographicAreaContainer);
    }

    private boolean checkIfListIsValid(List<GeographicArea> values){
        if (values == null || values.isEmpty()){
            return false;
        }return true;
    }
}