package Sprint0.UI;

import Sprint0.Controller.US07Controller;
import Sprint0.Model.GeographicArea;

public class US07UI {

    private GeographicArea mGeographicArea1;
    private GeographicArea mGeographicArea2;
    private US07Controller mUI;

    public US07UI (GeographicArea ga1, GeographicArea ga2){
        this.mGeographicArea1 = ga1;
        this.mGeographicArea2 = ga2;
        US07Controller controller = new US07Controller(mGeographicArea1, mGeographicArea2);
    }

    public void run(){

    }

}
