package Sprint0.Controller;

import Sprint0.Model.TypeArea;
import Sprint0.Model.TypeAreaList;


public class US01Controller {
    private TypeAreaList mList;

    public US01Controller(TypeAreaList type){
        this.mList=type;
    }
    public boolean newTAG (String tipo){
        TypeArea novoTAG = new TypeArea (tipo);
       return mList.addTypeArea(novoTAG);
    }

}
