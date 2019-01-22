package pt.ipp.isep.dei.project.model.device;

        import java.util.List;

/**
 * Represents a programmable option for devices.
 *
 * A device may be programmable, i.e. may be able to run several predefined/user defined 12 programs at a
 * configurable starting hour (e.g. washing machine, dishwasher, etc.).
 */

public interface Programmable {

    List<Program> getProgramList();

    Program getProgramByName(String name);

    boolean addProgram(Program program);

    //como remover? por nome?
    boolean removeProgram(Program program);

}
