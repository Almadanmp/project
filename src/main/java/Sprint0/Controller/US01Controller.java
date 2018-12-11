package Sprint0.Controller;

import Sprint0.Model.TypeArea;
import Sprint0.UI.MainUI;


public class US01Controller {

    public US01Controller(){
    }

    /**
     * This method creates a new Type of Geographic Area.
     * @param name - the String name of the Type of Geographic Area.
     * @return true - the Type of Geographic Area was successfully created;
     * false - the name is null.
     */
    public boolean newTAG (String name){
        TypeArea tipo = new TypeArea(name);
        if (name == null || name.isEmpty()) {
            return false;
        }
        return MainUI.mTypeAreaList.addTypeArea(tipo);
    }
}
