package Sprint0.Model;


import java.util.ArrayList;
import java.util.List;

public class TypeAreaList {
    private List<TypeArea> typeAreaList;

    public TypeAreaList() {
        typeAreaList = new ArrayList<>();
    }

    public void setTypeAreaList(ArrayList<TypeArea> list){
        this.typeAreaList =list;
    }
    public List<TypeArea> getTypeAreaList() {
        return this.typeAreaList;
    }

    public boolean containsTypeArea(TypeArea tipo) {
        return typeAreaList.contains(tipo);
    }

    public boolean newTAG(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        TypeArea tipo = new TypeArea(name);
        return addTypeArea(tipo);
    }

    public boolean addTypeArea(TypeArea type) {
        if (!typeAreaList.contains(type)){
            typeAreaList.add(type);
            return true;
        }
        else{
            return false;
        }
    }
}
