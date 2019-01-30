package pt.ipp.isep.dei.project.io.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileInputUtils {

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
        return 1440 % gridMeteringPeriod == 0;
    }

    //Device


    boolean validDeviceMetering() throws  IllegalArgumentException{
        int localGridMeteringPeriod = readDeviceMeteringPeriod2();
        int localDeviceMeteringPeriod = readDeviceMeteringPeriod1();

        if(deviceMeteringPeriodValidation(localDeviceMeteringPeriod,localGridMeteringPeriod)){
            this.mDeviceMeteringPeriod = localDeviceMeteringPeriod;
            return true;
        }
        System.out.println("ERROR: Configuration File values are incorrect. Devices cannot be created.\n" +
                "Please fix the configuration file before continuing.");
        return false;
    }

    int readDeviceMeteringPeriod1() {
        String deviceMeteringPeriod = "";
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("resources/meteringPeriods.properties");
            prop.load(input);
            deviceMeteringPeriod = prop.getProperty("DevicesMeteringPeriod");

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
    int readDeviceMeteringPeriod2() {
        String gridMeteringPeriod = "";
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("resources/meteringPeriods.properties");
            prop.load(input);
            gridMeteringPeriod = prop.getProperty("GridMeteringPeriod");
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.");
        } catch (IOException ioe) {
            System.out.println("ERROR: Unable to process configuration file.");
        }
        int gridMPValue = 0;
        try {
            gridMPValue = (Integer) Integer.parseInt(gridMeteringPeriod);
        } catch (NumberFormatException nfe) {
            System.out.println("Configuration file values are not numeric.");
        }
        return gridMPValue;
    }

    private boolean deviceMeteringPeriodValidation(int deviceValue, int gridValue) {
        if (deviceValue == 0 || gridValue == 0) {
            System.out.println("Values must be greater than 0");
            return false;
        }
        if (1440 % deviceValue != 0) {
            return false;
        } else if (deviceValue % gridValue != 0) {
            return false;
        }
        return true;
    }


}
