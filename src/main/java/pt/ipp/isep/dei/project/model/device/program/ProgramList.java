package pt.ipp.isep.dei.project.model.device.program;


import java.util.ArrayList;
import java.util.List;

public class ProgramList {

    private List<Program> programs;

    /**
     * ProgramList() Empty Constructor that initializes an ArrayList of Programs.
     */
    public ProgramList() {
        programs = new ArrayList<>();
    }

    /**
     * Getter of the ProgramList.
     *
     * @return the ProgramList.
     */
    public List<Program> getProgramList() {
        return this.programs;
    }

    /**
     * String Builder of the ProgramList.
     *
     * @return
     */
    public String buildProgramListString() {
        StringBuilder result = new StringBuilder("---------------\n");
        if (getProgramList().isEmpty()) {
            return "This device has no programs\n";
        }
        for (int i = 0; i < getProgramList().size(); i++) {
            Program program = getProgramList().get(i);
            result.append("\n").append(i).append(") Program Name: ").append(program.getProgramName());
            result.append(", Duration: ").append(program.getDuration());
            result.append(", Energy Consumption: ").append(program.getEnergyConsumption());
        }
        result.append("\n---------------\n");
        return result.toString();
    }

    /**
     * Method that adds a Program to the ProgramList.
     *
     * @param program is the program we want to add.
     * @return true if program was successfully added, false otherwise (already existing program)
     */
    public boolean addProgram(Program program) {
        if (!(programs.contains(program))) {
            programs.add(program);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that removes a Program from the ProgramList.
     *
     * @param program you want to remove from the ProgramList.
     * @return true if program was successfully removed, false otherwise.
     */
    boolean removeProgram(Program program) {
        if (programs.contains(program)) {
            programs.remove(program);
            return true;
        } else {
            return false;
        }
    }

    /** Method checks if program list is empty.
     * @return true if list is empty, false otherwise**/
    public boolean isEmpty() {
        return this.programs.isEmpty();
    }


}
