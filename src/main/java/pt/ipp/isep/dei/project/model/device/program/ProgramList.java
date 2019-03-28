package pt.ipp.isep.dei.project.model.device.program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramList {

    private List<Program> programs;

    /**
     * ProgramList() Empty Constructor that initializes an ArrayList of Programs.
     */
    public ProgramList() {
        this.programs = new ArrayList<>();
    }

    /**
     * String Builder of the ProgramList.
     *
     * @return a string with all the programs
     */

    public String buildString() {
        StringBuilder result = new StringBuilder("---------------\n");
        if (programs.isEmpty()) {
            return "This device has no programs\n";
        }
        for (int i = 0; i < programs.size(); i++) {
            Program program = programs.get(i);
            if (program instanceof FixedTimeProgram) {
                result.append("\n").append(i).append(") Program Name: ").append(program.getProgramName());
                result.append(", Duration: ").append(((FixedTimeProgram) program).getDuration());
                result.append(", Energy Consumption: ").append(((FixedTimeProgram) program).getEnergyConsumption());
            }
            if (program instanceof VariableTimeProgram) {
                result.append("\n").append(i).append(") Program Name: ").append(program.getProgramName());
                result.append(", Nominal Power: ").append(((VariableTimeProgram) program).getNominalPower());
            }
        }
        result.append("\n---------------\n");
        return result.toString();
    }


    /**
     * Method that adds a Program to the ProgramList.
     *
     * @param program is the program we want to addWithoutPersisting.
     * @return true if program was successfully added, false otherwise (already existing program)
     */
    public boolean add(Program program) {
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
     * @param program you want to removeGeographicArea from the ProgramList.
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

    /**
     * Method checks if program list is empty.
     *
     * @return true if list is empty, false otherwise
     **/
    public boolean isEmpty() {
        return this.programs.isEmpty();
    }

    /**
     * Checks the program list size and returns the size as int.\
     *
     * @return ProgramList size as int
     **/
    public int size() {
        return this.programs.size();
    }

    /**
     * This method receives an index as parameter and gets a program from program list.
     *
     * @param index the index of the Program.
     * @return returns program that corresponds to index.
     */
    public Program get(int index) {
        if (this.programs.isEmpty()) {
            throw new IndexOutOfBoundsException("The program list is empty.");
        }
        return this.programs.get(index);
    }

    /**
     * Getter (array of programs)
     *
     * @return array of programs
     */
    Program[] getElementsAsArray() {
        int sizeOfResultArray = programs.size();
        Program[] result = new Program[sizeOfResultArray];
        for (int i = 0; i < programs.size(); i++) {
            result[i] = programs.get(i);
        }
        return result;
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof ProgramList)) {
            return false;
        }
        ProgramList list = (ProgramList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
