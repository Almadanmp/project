package PT.IPP.ISEP.DEI.Project.Model;

import java.util.Objects;

/**
 * House Class. Defines de House
 */
public class House {
    private String mAdress;
    private Local mGPS;
    private String mZipCode;
    GeographicArea mMotherGA;
    private EnergyGridList mEGList;
    private RoomList mRoomList;

    //CONSTRUCTORS
    public House(){}

    public House (String mAdress, Local mGPS, String mZipCode){
        this.mAdress = mAdress;
        this.mGPS = mGPS;
        this.mZipCode = mZipCode;

    }

    public House(String mAdress, Local mGPS, String mZipCode, GeographicArea mMotherGA) {
        this.mAdress = mAdress;
        this.mGPS = mGPS;
        this.mZipCode = mZipCode;
        this.mMotherGA = mMotherGA;
    }

    //SETTERS AND GETTERS


    public String getmAdress() {
        return mAdress;
    }

    public void setmAdress(String mAdress) {
        this.mAdress = mAdress;
    }

    public Local getmGPS() {
        return mGPS;
    }

    public void setmGPS(Local mGPS) {
        this.mGPS = mGPS;
    }

    public String getmZipCode() {
        return mZipCode;
    }

    public void setmZipCode(String mZipCode) {
        this.mZipCode = mZipCode;
    }

    public GeographicArea getmMotherGA() {
        return mMotherGA;
    }

    public void setmMotherGA(GeographicArea mMotherGA) {
        this.mMotherGA = mMotherGA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(mAdress, house.mAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAdress);
    }
}

