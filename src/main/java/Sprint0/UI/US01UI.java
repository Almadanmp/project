package Sprint0.UI;

import Sprint0.Controller.US01Controller;
import Sprint0.Model.TypeAreaList;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * User Story 01
 * As a system administrator, I wish to define a new type of geographic area, so that later I can classify said geographic area.
 */

public class US01UI {
    private boolean active;
    private boolean mTypeAreaList;
    private TypeAreaList mListFinal;
    private String mTypeArea;


    public US01UI(TypeAreaList list){
        this.mListFinal=list;
        active=false;
    }

    public void run(){
        getInput();
        updateModel();
        displayState();
    }

    private void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the new Geographic Area Type: ");
        this.mTypeArea = scanner.nextLine();
    }

    private void updateModel() {
        US01Controller ctrl = new US01Controller();
        this.mTypeAreaList = ctrl.CreateAndAddTypeAreaToList(mTypeArea);
        System.out.println(ctrl.getTypeAreaList());
    }

    private void displayState() {
        this.active = true;
        if(mTypeAreaList){
            System.out.println("Success, you have inserted a new Type of Geographic Area");
        }
        else{
            System.out.println("Unsuccess");
        }
    }

    }
