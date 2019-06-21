package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

public abstract class CommonProgrammableDevice extends CommonDevice {
    private ProgramList programList;

    public CommonProgrammableDevice(DeviceSpecs deviceSpecs) {
        super(deviceSpecs);
        this.programList = new ProgramList();
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.programList;
    }

    public void setProgramList(ProgramList programList) {
        this.programList = programList;
    }
}
