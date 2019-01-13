package pt.ipp.isep.dei.project.model;

class Device implements Metered {
    //private String mName;
    //private Room mParentRoom;
    private double mNominalPower;
   // private DeviceSpecs deviceSpecs;

    Device(){
    }

    public double getNominalPower(){
        return this.mNominalPower;
    }

}
