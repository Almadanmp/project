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
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.device.config.DeviceTypeConfig;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;
import pt.ipp.isep.dei.project.repository.HouseCrudRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pt.ipp.isep.dei.project")
@ComponentScan(basePackages = "pt.ipp.isep.dei.project")
@EntityScan(basePackages = "pt.ipp.isep.dei.project")
public class MainUI {

    @Autowired
    private SensorTypeRepository sensorTypeRepository;
    @Autowired
    private AreaTypeRepository areaTypeRepository;
    @Autowired
    private HouseCrudRepo houseCrudRepo;
    @Autowired
    private HouseMonitoringUI houseMonitoringUI;
    @Autowired
    private HouseConfigurationUI houseConfigurationUI;
    @Autowired
    private RoomConfigurationUI roomConfigurationUI;
    @Autowired
    private EnergyConsumptionUI energyConsumptionUI;
    @Autowired
    private EnergyGridSettingsUI energyGridSettingsUI;
    @Autowired
    private RoomMonitoringUI roomMonitoringUI;
    @Autowired
    private GASettingsUI gaSettingsUI;
    @Autowired
    private SensorSettingsUI sensorSettingsUI;

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
                if (fileUtils.gridMeteringPeriodIsValid()) {
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

            //Sensor Types
            try {
                fileUtils.getSensorTypeConfig();
                fileUtils.addSensorTypesToRepository(sensorTypeRepository);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }

            //Area Types
            try {
                fileUtils.getAreaTypeConfig();
                fileUtils.addAreatypesToRepository(areaTypeRepository);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }


            //FixedTimeProgram Variables

            // *************************
            // ******* < MOCK DATA FOR TESTING PURPOSES >*******
            // *************************


            House house = mainHouse(houseCrudRepo, gridMeteringPeriod, deviceMeteringPeriod, deviceTypeConfig);

            //LOAD PERSISTED GA DATA

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

                List<String> mainMenuOptions = new ArrayList<>();
                mainMenuOptions.add("Geographic Area Settings");
                mainMenuOptions.add("House Settings.");
                mainMenuOptions.add("Room Settings.");
                mainMenuOptions.add("Sensor Settings.");
                mainMenuOptions.add("Energy Grid Settings.");
                mainMenuOptions.add("Room Monitoring.");
                mainMenuOptions.add("House Monitoring.");
                mainMenuOptions.add("Energy Consumption Management.");
                mainMenuOptions.add("Exit Application");

                MenuFormatter.showMenu("Main Menu", mainMenuOptions);


                boolean activeInput = true;

                while (activeInput) {
                    option = InputHelperUI.getInputAsInt();
                    switch (option) {
                        case 1:
                            gaSettingsUI.runGASettings();
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 2:
                            houseConfigurationUI.run(house);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 3:
                            roomConfigurationUI.run(house);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 4:
                            sensorSettingsUI.run();
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 5:
                            energyGridSettingsUI.run(house);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 6:
                            roomMonitoringUI.run(house);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 7:
                            houseMonitoringUI.run(house);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 8:
                            energyConsumptionUI.run();
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 0:
                            System.exit(0);
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

    private static House mainHouse(HouseCrudRepo houseCrudRepo, int gridMeteringPeriod, int deviceMeteringPeriod, List<String> deviceTypeConfig) {
        House house;
        Optional<House> aux = houseCrudRepo.findById("01");
        if (aux.isPresent()) {
            house = aux.get();
            house.setGridMeteringPeriod(gridMeteringPeriod);
            house.setDeviceMeteringPeriod(deviceMeteringPeriod);
            house.buildAndSetDeviceTypeList(deviceTypeConfig);
            houseCrudRepo.save(house);
            return house;
        }
        house = new House("01", new Local(0, 0, 0), gridMeteringPeriod, deviceMeteringPeriod, deviceTypeConfig);
        return houseCrudRepo.save(house);
    }
}