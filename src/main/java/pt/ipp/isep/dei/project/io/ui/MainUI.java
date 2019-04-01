package pt.ipp.isep.dei.project.io.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.TypeAreaList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensorList;
import pt.ipp.isep.dei.project.model.device.config.DeviceTypeConfig;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;
import pt.ipp.isep.dei.project.services.SensorService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pt.ipp.isep.dei.project")
@ComponentScan(basePackages = "pt.ipp.isep.dei.project")
@EntityScan(basePackages = "pt.ipp.isep.dei.project")
public class MainUI {

    @Autowired
    TypeSensorList typeSensorList;

    @Autowired
    TypeAreaList typeAreaList;

    @Autowired
    SensorService sensorService;

    GeographicAreaList geographicAreaList;

    @Autowired
    GeographicAreaRepository geographicAreaRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainUI.class, args);
    }

    @Bean
    public CommandLineRunner mainRun() {
        return args -> {
            List<String> deviceTypeConfig;
            FileInputUtils fileUtils = new FileInputUtils();

            int gridMeteringPeriod;
            String fixConfigFile = "Please fix Configuration File before continuing.";
            try {
                if (fileUtils.validGridMetering()) {
                    gridMeteringPeriod = fileUtils.gridMeteringPeriod;
                } else {
                    System.out.println("ERROR: Configuration File values are incorrect. Energy Grids cannot be created.\n" +
                            fixConfigFile);
                    return;
                }
            } catch (IOException ioe) {
                System.out.println("ERROR: Unable to process configuration file.\n" +
                        fixConfigFile);
                return;
            } catch (NumberFormatException nfe) {
                System.out.println("ERROR: Configuration File value is not a numeric value.\n" +
                        fixConfigFile);
                return;
            }

            int deviceMeteringPeriod;
            try {
                if (fileUtils.validDeviceMetering()) {
                    deviceMeteringPeriod = fileUtils.deviceMeteringPeriod;
                } else return;
            } catch (IllegalArgumentException il) {
                return;
            }

            //DeviceTypeConfiguration - US70

            try {
                DeviceTypeConfig devTConfig = new DeviceTypeConfig();
                deviceTypeConfig = devTConfig.getDeviceTypeConfig();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }


            //FixedTimeProgram Variables

            // *************************
            // ******* < MOCK DATA FOR TESTING PURPOSES >*******
            // *************************
            MockUI mockUI = new MockUI();
            mockUI.initializeMockUI();

            GeographicAreaList mockGeographicAreaList = mockUI.getGeoAreaList();

            TypeSensorList mockTypeSensorList = new TypeSensorList();
            House mockHouse = mockUI.mockHouse(gridMeteringPeriod, deviceMeteringPeriod, deviceTypeConfig);

            //LOAD PERSISTED GA DATA
            this.geographicAreaList = (new GeographicAreaList(geographicAreaRepository)).getAll();

            //MAIN CODE

            Scanner enterToReturnToConsole = new Scanner(System.in);
            int option;
            while (true) {
                System.out.println(
                        "                      ______          ___ _    _____ _    _ \n" +
                                "                    / ____\\ \\        / (_) |  / ____| |  | |\n" +
                                "                   | (___  \\ \\  /\\  / / _| |_| |    | |__| |\n" +
                                "                    \\___ \\  \\ \\/  \\/ / | | __| |    |  __  |\n" +
                                "                    ____) |  \\  /\\  /  | | |_| |____| |  | |\n" +
                                "                   |_____/    \\/  \\/   |_|\\__|\\_____|_|  |_|    2018\n" +
                                "                          \n                                Smart Grid Menu \n"
                );

                // Submenus Input selection

                String[] menu = {
                        " 1. Geographic Area Settings\n",
                        "2. House Settings.\n",
                        "3. Room Settings.\n",
                        "4. Sensor Settings.\n",
                        "5. Energy Grid Settings.\n",
                        "6. House Monitoring.\n",
                        "7. Energy Consumption Management.\n",
                        "0. Exit Application\n"};

                System.out.println("Select the task you want to do:");

                String formattedString = Arrays.toString(menu)
                        .replace(",", "")  //removeGeographicArea the commas
                        .replace("[", "")  //removeGeographicArea the right bracket
                        .replace("]", "");  //removeGeographicArea the left bracket

                System.out.print(formattedString);
                System.out.print("\nEnter option number:\n");
                boolean activeInput = true;

                while (activeInput) {
                    option = InputHelperUI.getInputAsInt();
                    this.geographicAreaList = (new GeographicAreaList(geographicAreaRepository)).getAll();
                    switch (option) {
                        case 1:
                            GASettingsUI view1 = new GASettingsUI(typeAreaList, geographicAreaList);
                            view1.runGASettings();
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 2:
                            HouseConfigurationUI houseC = new HouseConfigurationUI(sensorService, geographicAreaList);
                            houseC.run(mockHouse);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 3:
                            RoomConfigurationUI roomConfiguration = new RoomConfigurationUI();
                            roomConfiguration.run(mockHouse, mockTypeSensorList);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 4:
                            SensorSettingsUI sensorSettings = new SensorSettingsUI();
                            sensorSettings.run(mockGeographicAreaList, mockTypeSensorList);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 5:
                            EnergyGridSettingsUI energyGridSettings = new EnergyGridSettingsUI();
                            energyGridSettings.run(mockHouse);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 6:
                            HouseMonitoringUI houseM = new HouseMonitoringUI();
                            houseM.run(mockHouse);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 7:
                            EnergyConsumptionUI energyConsumptionUI = new EnergyConsumptionUI();
                            energyConsumptionUI.run(mockHouse);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println(UtilsUI.INVALID_OPTION);
                            break;
                    }
                }
            }
        };
    }

    private static void returnToMenu(Scanner scanner) {
        String pressEnter = "\nPress ENTER to return.";
        System.out.println(pressEnter);
        scanner.nextLine();
    }
}