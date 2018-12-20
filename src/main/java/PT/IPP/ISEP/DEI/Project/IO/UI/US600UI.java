package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US600Controller;
import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;

import java.util.Scanner;

public class US600UI {
    private boolean active;
    private GeographicAreaList mGeographicAreaList;
    private GeographicArea mGeoArea;
    private String mGeoAreaName;

    public US600UI(){
        this.active=false;
    }

    public void run(GeographicAreaList list){
        while(this.active = true) {
        if(!displayGeographicAreasAndGetInput(list)) {
            return;
        }
        return;
        }
    }

    public boolean displayGeographicAreasAndGetInput(GeographicAreaList list){
        US600Controller ctrl = new US600Controller(list);
        ctrl.printGeoAreaList();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Geographic Area in which your House is in: ");
        this.mGeoAreaName = scanner.next();
        if (ctrl.validateIfGeographicAreaToGeographicAreaList(mGeoAreaName)){
            System.out.println("You have inserted the Geographic Area "+mGeoAreaName);
        }
        else
        {
            System.out.println("That Geographic Area does not belong to the List.");
            return false;}
            return true;
    }




}
