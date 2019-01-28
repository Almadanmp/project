package pt.ipp.isep.dei.project.io.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileInputUtils {

public int deviceMeteringPeriod;  //Public para ir buscar no Device

    public boolean testeMethod() throws  IllegalArgumentException{
        if (!readMeteringPeriods()) {
            throw new IllegalArgumentException("Teste");
        }
        return true;
    }

    public boolean readMeteringPeriods() {
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
