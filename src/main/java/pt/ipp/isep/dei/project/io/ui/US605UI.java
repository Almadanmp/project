package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US605Controller;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * US605   As a Regular User, I want to get the current temperature in a room, in order to check
 * if it meets my personal comfort requirements.
 */

public class US605UI {
    private boolean active;
    private String mNameRoom;
    private RoomList mRoomList;
    private String mNameSensor;
    private double mCurrentTemperature;

    public US605UI(){
        this.active=false;
    }

    public void run(RoomList list){
        while(this.active = true) {
            if(!getInputRoom(list)){
                return;
            }
            if(!getInputSensorName(list)){
                return;
            }
            updateModel(list);
            displayState();
            return;
        }
    }

    //Acrescentar numeros ao regex
    private boolean getInputRoom(RoomList list){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Room you want to get the Current Temperature from: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name for a Room. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.mNameRoom = scanner.next();
        US605Controller ctrl = new US605Controller(list);
        if(ctrl.doesListContainRoomByName(this.mNameRoom)) {
            System.out.println("You chose the Room "+this.mNameRoom);
        }
        else{
            System.out.println("This room does not exist in the list of rooms.");
            return false;
        }
        return true;
    }

    //Acrescentar numeros ao regex
    private boolean getInputSensorName(RoomList list) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Sensor you want to get the Current Temperature from: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name for a Sensor. Please insert only Alphabetic Characters");
        }
        this.mNameSensor = scanner.next();
        US605Controller ctrl = new US605Controller(list);
        if(ctrl.doesSensorListInARoomContainASensorByName(this.mNameSensor)) {
            System.out.println("You chose the Sensor " + this.mNameSensor);
        }
        else{
            System.out.println("This sensor does not exist in the list of sensors.");
            return false;
        }
        return true;
    }

    private void updateModel(RoomList list) {
        US605Controller ctrl = new US605Controller(list);
        out.print("The room is " + this.mNameRoom + " and the Temperature Sensor is " + this.mNameSensor + "\n");
        Date mDate = new Date();
        this.mCurrentTemperature = ctrl.getCurrentRoomTemperature(mDate);
    }

    private void displayState(){
        out.println("The Current Temperature in the room " + this.mNameRoom  +
                " is " + this.mCurrentTemperature);
    }
}