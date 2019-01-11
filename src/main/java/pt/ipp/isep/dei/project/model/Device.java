package pt.ipp.isep.dei.project.model;

class Device implements Metered {
    //private String mName;
    //private String mDeviceType;
    //private Room mParentRoom;
    //private ReadingList mEnergyConsumptionList;
    private double mNominalPower;

    Device(){
    }

    public double getNominalPower(){
        return this.mNominalPower;
    }

}
