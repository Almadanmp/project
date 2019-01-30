package pt.ipp.isep.dei.project.io.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileInputUtils {

    int deviceMeteringPeriod;  //Public para ir buscar no Device
    int mGridMeteringPeriod;


    boolean validGridMetering() throws IOException {
        int gridMeteringPeriod = readGridMeteringPeriods();
        if(gridMeteringPeriodValidation(gridMeteringPeriod)){
            this.mGridMeteringPeriod = gridMeteringPeriod;
            return true;
        }
        System.out.println("ERROR: Configuration File values are incorrect. Energy Grids cannot be created.\n" +
                    "Please fix Configuration File before continuing.");
        return false;
    }

    private int readGridMeteringPeriods() throws IOException {
        String gridMeteringPeriod;
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("resources/meteringPeriods.properties")){
            prop.load(input);
            gridMeteringPeriod = prop.getProperty("GridMeteringPeriod");
        } catch (IOException ioe) {
            throw new IOException("ERROR: Unable to process configuration file.");
        }
        try {
            return Integer.parseInt(gridMeteringPeriod);
        }
        catch (NumberFormatException nfe){
            throw new NumberFormatException("ERROR: Configuration File value is not a numeric value.");
        }
    }

    private boolean gridMeteringPeriodValidation(int gridMeteringPeriod) {
        return 1440 % gridMeteringPeriod == 0;
    }

    boolean validDeviceMetering() throws  IllegalArgumentException{
        if (!readDeviceMeteringPeriods()) {
            throw new IllegalArgumentException("Teste");
        }
        return true;
    }

    boolean readDeviceMeteringPeriods() {
        String GridMeteringPeriod;
        String DeviceMeteringPeriod;
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("resources/meteringPeriods.properties");
            prop.load(input);
            DeviceMeteringPeriod = prop.getProperty("DevicesMeteringPeriod");
            GridMeteringPeriod = prop.getProperty("GridMeteringPeriod");
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.");
            return false;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        try {
            Integer deviceMPValue = (Integer) Integer.parseInt(DeviceMeteringPeriod);
            Integer gridMPValue = (Integer) Integer.parseInt(GridMeteringPeriod);
            if (deviceMeteringPeriodValidation(deviceMPValue, gridMPValue)) {
                this.deviceMeteringPeriod = deviceMPValue;
                return true;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Configuration file values are not numeric.");
            return false;
        }
        System.out.println("Configuration file values are not supported.");
        return false;
    }
    private boolean deviceMeteringPeriodValidation(int deviceValue, int gridValue) {
        if (1440 % deviceValue != 0) {
            return false;
        } else if (deviceValue % gridValue != 0) {
            return false;
        }
        return true;
    }


}
