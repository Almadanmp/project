package PT.IPP.ISEP.DEI.Project.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HouseList {

    private List<House> mHouseList;

    public HouseList() {
        mHouseList = new ArrayList<>();
    }

    public HouseList(House house) {
        mHouseList = new ArrayList<>();
        mHouseList.add(house);
    }

    public boolean addHouseToHouseList(House house) {
        if (!mHouseList.contains(house)) {
            mHouseList.add(house);
            return true;
        }
        return false;
    }

    public boolean checkIfHouseListIsValid() {
        {
            return !mHouseList.isEmpty();
        }
    }

    public List<House> getHouseList() {
        return mHouseList;
    }

    public boolean checkIfHouseListContainsHouseWithGivenDesignation(String houseDesignation) {
        for(House h : this.mHouseList) {
            String houseDesignationToTest = h.getHouseDesignation();
            if(houseDesignationToTest.equals(houseDesignation)) {
                return true;
            }
        }
        return false;
    }

    public boolean addRoomToHouseInHouseList(String houseDesignation, Room roomToAdd) {
        for(House h : this.mHouseList) {
            String houseDesignationToTest = h.getHouseDesignation();
            if(houseDesignationToTest.equals(houseDesignationToTest)) {
                if(h.addRoomToRoomList(roomToAdd)) {
                 return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean matchHouse (String houseToMatch){
        for (House g: mHouseList){
            if (g.getHouseDesignation().equals(houseToMatch)){
                return true;
            }
        }
        return false;
    }

    public House getHouseByDesignation(String name) {
        for (House g : mHouseList) {
            if (g.getHouseDesignation().equals(name)) {
                return g;
            }
        }
        return null;
    }

    public String printHouseList() {
        StringBuilder finalString = new StringBuilder();
        String emptyList= "The list is empty.";
        if(mHouseList.isEmpty()) {
            return emptyList;
        }
        finalString.append("House List:");
        for(House house: mHouseList) {
            finalString.append(" \n" + "-").append(house.getHouseDesignation()).append(";");
        }
        return finalString.toString();
    }


    public void printHouseList(GeographicArea geoArea) {
        System.out.println("---------------");

        for (int i = 0; i < geoArea.getHouseList().getHouseList().size(); i++) {
            House aux = geoArea.getHouseList().getHouseList().get(i);
            System.out.print(i + ") Designation: " + aux.getHouseDesignation() + " | ");
            System.out.print("Address: " + aux.getmAddress() + " | ");
            System.out.print("ZipCode: " + aux.getmZipCode());
            System.out.println();
        }
        System.out.println("---------------");
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof HouseList)) {
            return false;
        }
        HouseList list = (HouseList) testObject;
        return Arrays.equals(this.getHouseList().toArray(), list.getHouseList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
