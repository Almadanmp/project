package Sprint0.Model;

import java.util.ArrayList;
import java.util.List;

public class TypeAreaList {
    private List<TypeArea> mTypeAreaList;

    public TypeAreaList() {
        mTypeAreaList = new ArrayList<>();
    }

    public void setTypeAreaList(ArrayList<TypeArea> list){
        this.mTypeAreaList =list;
    }

    public String printTypeAreaList() {
        String finalString= "Area Types List";
        for(TypeArea tipo: mTypeAreaList)
           finalString = finalString + " \n"+ "-" + tipo.getTypeOfGeographicArea() + ";";
        return finalString;
    }

    public boolean containsTypeArea(TypeArea tipo) {
        return mTypeAreaList.contains(tipo);
    }

    public boolean newTAG(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        TypeArea tipo = new TypeArea(name);
        return addTypeArea(tipo);
    }

    public boolean addTypeArea(TypeArea type) {
        if (!mTypeAreaList.contains(type)){
            mTypeAreaList.add(type);
            return true;
        }
        else{
            return false;
        }
    }
}
