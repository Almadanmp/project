package Sprint0.Model;


import java.util.ArrayList;
import java.util.List;

public class TypeAreaList {
    private List<TypeArea> areas;

    public TypeAreaList() {
        areas = new ArrayList<>();
    }

    public void setTypeAreaList(ArrayList<TypeArea> list){
        this.areas=list;
    }
    public List<TypeArea> getTypeAreaList() {
        return this.areas;
    }

    public boolean containsTypeArea(TypeArea tipo) {
        return areas.contains(tipo);
    }

    public boolean addTypeArea(TypeArea type) {
        if (!areas.contains(type)){
            areas.add(type);
            return true;
        }
        else{
            return false;
        }
    }
}
