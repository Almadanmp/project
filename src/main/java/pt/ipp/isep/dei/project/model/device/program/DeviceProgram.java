package pt.ipp.isep.dei.project.model.device.program;

public interface DeviceProgram {

    /**
     * Represents a program option for devices.
     * A programmable device implements a number of different programs. Types of programs may vary from device to
     * device.
     */

    String buildProgramString();

    void setProgramName(String name);

    String getProgramName();

    void setNominalPower(double nominalPower);

    double getNominalPower();

}
