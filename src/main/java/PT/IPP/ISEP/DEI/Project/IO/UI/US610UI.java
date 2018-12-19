package PT.IPP.ISEP.DEI.Project.IO.UI;
import PT.IPP.ISEP.DEI.Project.Controller.US610Controller;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

import java.sql.SQLOutput;
import java.util.*;

import static java.lang.System.out;

/**
 * As a Regular User, I want to get the maximum temperature in a room in a given day,
 * in order to check if heating/cooling in that room was effective.
 */
public class US610UI {
    private boolean active;
    private String mNameRoom;
    private RoomList mRoomList;
    private String mNameSensor;
    private int dataYear;
    private int dataMonth;
    private int dataDay;

    private double mMaxTemperature;

    public US610UI(){
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
            getInputDate();
            updateModel(list);
            displayState();
            return;
        }
    }

    private boolean getInputRoom(RoomList list){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Room you want to get the Maximum Temperature from: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name for a Room. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.mNameRoom = scanner.next();
        US610Controller ctrl = new US610Controller(list);
        if(ctrl.doesListContainRoomByName(this.mNameRoom)) {
            System.out.println("You chose the Room "+this.mNameRoom);
        }
        else{
            System.out.println("This room does not exist in the list of rooms.");
            return false;
        }
        return true;
    }

    private boolean getInputSensorName(RoomList list) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Sensor you want to get the Maximum Temperature from: ");
        this.mNameSensor = scanner.next();
        US610Controller ctrl = new US610Controller(list);
        if(ctrl.doesSensorListInARoomContainASensorByName(this.mNameSensor)) {
            System.out.println("You chose the Sensor " + this.mNameSensor);
        }
        else{
            System.out.println("This sensor does not exist in the list of sensors.");
            return false;
        }
        return true;
    }


    private void getInputDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the day:");
        out.println("Enter the year:");
        while(!scanner.hasNextInt()) {
            scanner.next();
        out.println("Not a valid year. Try again");
    }
        this.dataYear = scanner.nextInt();
        scanner.nextLine();
        out.println("\nEnter the Month:\t");
        while(!scanner.hasNextInt()) {
            scanner.next();
        out.println("Not a valid month. Try again");
    }
        this.dataMonth = scanner.nextInt();
        scanner.nextLine();
        out.println("\nEnter the Day:\t");
        while(!scanner.hasNextInt()) {
            scanner.next();
        out.println("Not a valid day. Try again");
    }
        this.dataDay = scanner.nextInt();
        out.println("You entered the date successfully!");
        scanner.nextLine();
    }


    private void updateModel(RoomList list) {
        US610Controller ctrl = new US610Controller(list);
        out.print("The room is " + this.mNameRoom + " the Temperature Sensor is " + this.mNameSensor +
                " and the date is " + this.dataDay +"-"+ this.dataMonth +"-"+ this.dataYear + "\n");
        Date mDate = ctrl.createDate(this.dataYear, this.dataMonth, this.dataDay);
        this.mMaxTemperature = ctrl.getMaxTemperatureInARoomOnAGivenDay(mDate);
    }

    private void displayState(){
            out.println("The Maximum Temperature in the room " + this.mNameRoom  +
                    " on the day " + this.dataDay + "-" + this.dataMonth + "-" + this.dataYear +
                    " was " + this.mMaxTemperature);
    }




}
