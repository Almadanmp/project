package Sprint0.UI;

import Sprint0.Controller.US01Controller;
import Sprint0.Model.TypeAreaList;

import java.util.Scanner;

public class US01UI {
    private US01Controller ctrl;
    private boolean active;


    public US01UI(TypeAreaList type){
        active=false;
        ctrl= new US01Controller(type);
    }

    public void run(){
        System.out.println("Please insert the name of the new Geographic Area Type");
        Scanner ler= new Scanner(System.in);
        String nome = ler.nextLine();
        if(ctrl.newTAG(nome)==true){
            System.out.println("Success");
        }
        else{
            System.out.println("Unsuccess");
        }

    }

}
