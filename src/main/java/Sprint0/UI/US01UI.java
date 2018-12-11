package Sprint0.UI;

import Sprint0.Controller.US01Controller;
import Sprint0.Model.TypeArea;
import Sprint0.Model.TypeAreaList;

import java.util.Scanner;

/**
 * User Story 01
 * As a system administrator, I wish to definy a new type of geographic area, so that later I can classify said geographic area.
 */

public class US01UI {
    private boolean active;
    private TypeAreaList mTypeAreaList;


    public US01UI(){
        active=false;
    }

    public void run(){
        System.out.println("Please insert the name of the new Geographic Area Type");
        Scanner ler= new Scanner(System.in);
        String nome = ler.nextLine();
        US01Controller ctrl1 = new US01Controller();
        this.active = true;
        if(ctrl1.newTAG(nome)){
            System.out.println("Success, you have inserted a new Type of Geographic Area");
        }
        else{
            System.out.println("Unsuccess");
        }
    }






}
