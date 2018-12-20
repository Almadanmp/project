package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US605Controller;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

public class US605UI {
    private boolean active;
    private String mNameRoom;
    private RoomList mRoomList;
    private String mNameSensor;
    private int dataYear;
    private int dataMonth;
    private int dataDay;
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
        out.print("The room is " + this.mNameRoom + " the Temperature Sensor is " + this.mNameSensor + "\n");
        Date mDate = ctrl.createDate(this.dataYear, this.dataMonth, this.dataDay);
        this.mCurrentTemperature = ctrl.getCurrentRoomTemperature(mDate);
    }

    private void displayState(){
        out.println("The Current Temperature in the room " + this.mNameRoom  +
                " is " + this.mCurrentTemperature);
    }
}