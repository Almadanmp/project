package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.UI.MainUI;

import java.util.List;

public class US08Controller {

    private GeographicArea mGeographicAreaContained;
    private GeographicArea mGeographicAreaContainer;


    public US08Controller(){}

    public boolean setGeographicAreas(String area1Name, String area2Name){
        if (checkIfListIsValid(MainUI.mGeographicAreaList.getGeographicAreaList())) {
            for (GeographicArea ga1 : MainUI.mGeographicAreaList.getGeographicAreaList()) {
                if (ga1.getName().equals(area1Name)) {
                    mGeographicAreaContained = ga1;
                    for (GeographicArea ga2 : MainUI.mGeographicAreaList.getGeographicAreaList()) {
                        if (ga2.getName().equals(area2Name)) {
                            mGeographicAreaContainer = ga2;
                            return true;
                        }
                    }
                }
            }
        }return false;
    }

    public boolean seeIfItsContained(){
        if (mGeographicAreaContained.isAreaContainedInAnotherArea(mGeographicAreaContained,mGeographicAreaContainer)){
        return true;
        }return false;
    }

    private boolean checkIfListIsValid(List<GeographicArea> values){
        if (values == null || values.isEmpty()){
            return false;
        }return true;
    }
}