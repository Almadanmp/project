package Sprint0.Controller;

import Sprint0.Model.TypeArea;
import Sprint0.UI.MainUI;


public class US01Controller {

    public US01Controller(){
    }

    public boolean newTAG (String name){
        TypeArea tipo = new TypeArea(name);
        return MainUI.mTypeAreaList.addTypeArea(tipo);
    }


}
