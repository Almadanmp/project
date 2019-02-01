package pt.ipp.isep.dei.project.io.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

class FileInputUtils {

    int mDeviceMeteringPeriod;
    int mGridMeteringPeriod;


    boolean validGridMetering() throws IOException {
        int gridMeteringPeriod = readGridMeteringPeriod();
        if(gridMeteringPeriodValidation(gridMeteringPeriod)){
            this.mGridMeteringPeriod = gridMeteringPeriod;
            return true;
        }
        return false;
    }

    private int readGridMeteringPeriod() throws IOException {
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
        if (gridMeteringPeriod == 0) {
            System.out.println("Grid metering value must be greater than 0.");
            return false;
        }
        return 1440 % gridMeteringPeriod == 0;
    }

    //Device

    boolean validDeviceMetering() {
        int deviceMeteringPeriod = readDeviceMeteringPeriod();

        if(deviceMeteringPeriodValidation(deviceMeteringPeriod)){
            this.mDeviceMeteringPeriod = deviceMeteringPeriod;
            return true;
        }
        System.out.println("ERROR: Configuration File values are incorrect. Devices cannot be created.\n" +
                "Please fix the configuration file before continuing.");
        return false;
    }

    private int readDeviceMeteringPeriod() {
        String deviceMeteringPeriod = "";
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("resources/meteringPeriods.properties");
            prop.load(input);
            deviceMeteringPeriod = prop.getProperty("DevicesMeteringPeriod");
            input.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.");
        } catch (IOException ioe) {
            System.out.println("ERROR: Unable to process configuration file.");
        }
        int deviceMPvalue = 0;
        try {
            deviceMPvalue = (Integer) Integer.parseInt(deviceMeteringPeriod);
        } catch (NumberFormatException nfe) {
            System.out.println("Configuration file values are not numeric.");
        }
        return deviceMPvalue;
    }

    private boolean deviceMeteringPeriodValidation(int deviceValue) {
        if (deviceValue == 0) {
            System.out.println("Device metering value must be greater than 0.");
            return false;
        }
        if (1440 % deviceValue != 0) {
            return false;
        }
        return deviceValue % this.mGridMeteringPeriod == 0;
    }
}
