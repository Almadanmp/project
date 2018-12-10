package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.Local;
import Sprint0.Model.TypeArea;

public class US08Controller {

    private GeographicArea mGeographicArea1;
    private TypeArea mTypeArea1;
    private GeographicArea mGeographicArea2;
    private TypeArea mTypeArea2;
    private Local mLocal;

    public US08Controller(GeographicArea geographicArea1, GeographicArea geographicArea2) {
        this.mGeographicArea1 = geographicArea1;
        this.mTypeArea1 = geographicArea1.getTypeArea();
        this.mGeographicArea2 = geographicArea2;
        this.mTypeArea2 = geographicArea2.getTypeArea();
    }

    public String isAreacontainedInGivenArea(){
        if (this.mLocal.isAreaContainedInAnotherArea(this.mGeographicArea1, this.mGeographicArea2)){
            return "This " + mTypeArea1 + " is contained in " + mTypeArea2 + "!";
        }
        return "This " + mTypeArea1 + " is NOT contained in " + mTypeArea2 + "!";
    }
}