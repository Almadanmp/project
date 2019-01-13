package pt.ipp.isep.dei.project.model;

public class Device implements Metered {
    //private String mName;
    //private Room mParentRoom;
    private double mNominalPower;
   // private DeviceSpecs deviceSpecs;

    public Device(){
    }

    //temporary before is gets moved to DeviceSpecs
    public void setmNominalPower(Double nompower) {
        this.mNominalPower = nompower;
    }

    public double getNominalPower(){
        return this.mNominalPower;
    }

}
