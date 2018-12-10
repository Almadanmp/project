package Sprint0.Model;

import java.util.ArrayList;

public class TypeAreaList {
    private ArrayList<TypeArea> areas;

    public TypeAreaList() {
        areas = new ArrayList<>();
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
