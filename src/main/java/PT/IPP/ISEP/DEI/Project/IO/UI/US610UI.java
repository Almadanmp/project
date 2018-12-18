package PT.IPP.ISEP.DEI.Project.IO.UI;
import PT.IPP.ISEP.DEI.Project.Controller.US610Controller;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

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
    private int mHouseFloor;
    private double mDimensions;
    private String mNameSensor;
    private int dataYear;
    private int dataMonth;
    private int dataDay;
    private Scanner scanner;
    private double mMaxTemperature;

    public US610UI(){
        this.active=false;
    }

    private void run(){
        this.active = true;
        getInputRoom();
        getInputHouseFloor();
        getInputDimensions();
        getInputSensorName();
        getInputDate();
        updateModel();
        displayState();

    }

    private void getInputRoom() {
        this.mNameRoom = readInputString("Room");
    }

    private void getInputHouseFloor(){
        System.out.println("\nEnter the house floor:\t");
        while(!scanner.hasNextInt()) {
            scanner.next();
            out.println("Not a valid House Floor. Try again");
        }
        this.mHouseFloor = scanner.nextInt();
        scanner.nextLine();
    }

    private void getInputDimensions(){
        System.out.println("\nEnter the Room Dimensions\t");
        while(!scanner.hasNextInt()) {
            scanner.next();
            out.println("Not a valid Dimension. Try again");
        }
        this.mDimensions = scanner.nextInt();
        scanner.nextLine();
    }

    private void getInputSensorName() {
        this.mNameSensor = readInputString("Sensor");
    }

    private void getInputDate(){
        System.out.println("\nEnter the day:\t");
        out.println("\nEnter the year:\t");
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


    private void updateModel() {
        this.mRoomList=new RoomList();
        US610Controller ctrl = new US610Controller(mRoomList);
        out.print("The room is " + this.mNameRoom + " the Temperature Sensor is " + this.mNameSensor +
                " and the date is " + this.dataDay +"-"+ this.dataMonth +"-"+ this.dataYear + "\n");
        Date mDate = ctrl.createDate(this.dataYear, this.dataMonth, this.dataDay);
        this.mMaxTemperature = ctrl.getMaxTemperatureInARoomOnAGivenDay(mDate);
    }

    private void displayState(){
        US610Controller ctrl = new US610Controller(this.mRoomList);
        if (ctrl.doesListContainRoomByName(this.mNameRoom) && ctrl.doesSensorListInARoomContainASensorByName(this.mNameSensor)) {
            out.println("The Maximum Temperature in the room " + this.mNameRoom + "\n" +
                    " on the day " + this.dataDay + "-" + this.dataMonth + "-" + this.dataYear + "\n" +
                    " was " + this.mMaxTemperature);
        }
        out.println("this is all wrong");
    }

    private String createInputMsg(String inputType) {
        return "Please Insert The " + inputType + " : ";
    }

    private String createInvalidStringMsg(String inputType) {
        return "That's not a valid " + inputType + ". Please insert only Alphabetic Characters";
    }


    private String readInputString(String inputType) {
        System.out.print(createInputMsg(inputType));

        while (!scanner.hasNext("[a-zA-Z\\sà-ùÀ-Ù]*")) {
            System.out.println(createInvalidStringMsg(inputType));
            scanner.next();
        }
        return scanner.next();
    }

}
