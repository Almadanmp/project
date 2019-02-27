package pt.ipp.isep.dei.project.model.device.program;

/**
 * Represents a programmable option for devices.
 * <p>
 * A device may be programmable, i.e. may be able to run several predefined/user defined 12 programs at a
 * configurable starting hour (e.g. washing machine, dishwasher, etc.).
 */

public interface Programmable {

    ProgramList getProgramList();

    void setProgramList(ProgramList programList);

}
