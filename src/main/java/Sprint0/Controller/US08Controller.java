package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.UI.MainUI;

import java.util.List;

public class US08Controller {

    private GeographicArea mGeographicArea1;
    private GeographicArea mGeographicArea2;


    public US08Controller(){}

    public boolean setGeographicAreas(String area1Name, String area2Name){
        if (checkIfListIsValid(MainUI.mGeographicAreaList.getGeographicAreaList())) {
            for (GeographicArea ga1 : MainUI.mGeographicAreaList.getGeographicAreaList()) {
                if (ga1.getName().equals(area1Name)) {
                    mGeographicArea1.setBottomRightVertex(ga1.getBottomRightVertex());
                    mGeographicArea1.setTopLeftVertex(ga1.getTopLeftVertex());
                    mGeographicArea1.setName(area1Name);
                    for (GeographicArea ga2 : MainUI.mGeographicAreaList.getGeographicAreaList()) {
                        if (ga2.getName().equals(area2Name)) {
                            mGeographicArea2.setBottomRightVertex(ga2.getBottomRightVertex());
                            mGeographicArea2.setTopLeftVertex(ga2.getTopLeftVertex());
                            mGeographicArea2.setName(area2Name);
                            return true;
                        }
                    }
                }
            }
        }return false;
    }

    public boolean seeIfItsContained(){
        if (mGeographicArea1.isAreaContainedInAnotherArea(mGeographicArea1,mGeographicArea2)){
        return true;
        }return false;
    }

    private boolean checkIfListIsValid(List<GeographicArea> values){
        if (values == null || values.isEmpty()){
            return false;
        }return true;
    }
}