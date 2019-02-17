package pt.ipp.isep.dei.project.model.device.program;


import java.util.ArrayList;
import java.util.List;

public class ProgramList {

    private List<Program> mProgramList;


    public ProgramList(){
        mProgramList = new ArrayList<>();
    }

    public List<Program> getProgramList(){
        return this.mProgramList;
    }


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

    public String buildProgramListStringForEach() {
        StringBuilder result = new StringBuilder();
        if (getProgramList().isEmpty()) {
            return "This device has no programs\n";
        }
        for (int i = 0; i < getProgramList().size(); i++) {
            Program program = getProgramList().get(i);
            result.append("\n").append(program.buildProgramString());
        }
        return result.toString();
    }

    public boolean addProgram(Program program) {
        if (!(mProgramList.contains(program))) {
            mProgramList.add(program);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeProgram(Program program) {
        if (mProgramList.contains(program)) {
            mProgramList.remove(program);
            return true;
        } else {
            return false;
        }
    }


}
