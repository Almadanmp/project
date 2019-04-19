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
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.config.DeviceTypeConfig;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.HouseSensorService;
import pt.ipp.isep.dei.project.model.sensor.ReadingService;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeService;
import pt.ipp.isep.dei.project.repository.*;

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
    private SensorTypeService sensorTypeService;

    @Autowired
    private AreaTypeService areaTypeService;

    @Autowired
    private AreaSensorService areaSensorService;

    @Autowired
    private AreaSensorRepository areaSensorRepository;

    @Autowired
    private ReadingService readingService;
    @Autowired
    private GeographicAreaService geographicAreaService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private HouseSensorService houseSensorService;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseSensorRepository houseSensorRepository;

    @Autowired
    private EnergyGridService energyGridService;

    @Autowired
    private HouseService houseService;
    @Autowired
    SensorTypeRepository sensorTypeRepository;
    @Autowired
    GeographicAreaRepository geographicAreaRepository;
    @Autowired
    AreaTypeRepository areaTypeRepository;

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


            //FixedTimeProgram Variables

            // *************************
            // ******* < MOCK DATA FOR TESTING PURPOSES >*******
            // *************************

            SensorTypeService mockSensorTypeList = new SensorTypeService(sensorTypeRepository);

            House house = mainHouse(houseRepository, gridMeteringPeriod, deviceMeteringPeriod, deviceTypeConfig);

            //LOAD PERSISTED GA DATA

            this.geographicAreaService = new GeographicAreaService(geographicAreaRepository, areaTypeRepository, areaSensorRepository);

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
                mainMenuOptions.add("House Monitoring.");
                mainMenuOptions.add("Energy Consumption Management.");
                mainMenuOptions.add("Exit Application");

                MenuFormatter.showMenu("Main Menu", mainMenuOptions);


                boolean activeInput = true;

                while (activeInput) {
                    option = InputHelperUI.getInputAsInt();
                    switch (option) {
                        case 1:
                            GASettingsUI view1 = new GASettingsUI(areaSensorService, readingService, houseService, houseSensorService);
                            view1.runGASettings(areaTypeService, geographicAreaService, areaSensorService);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 2:
                            HouseConfigurationUI houseC = new HouseConfigurationUI(areaSensorService, readingService, houseService, houseSensorService);
                            houseC.run(house, geographicAreaService, houseSensorService, roomService, roomRepository, houseSensorRepository, energyGridService);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 3:
                            RoomConfigurationUI roomConfiguration = new RoomConfigurationUI(roomService);
                            roomConfiguration.run(house, sensorTypeService, houseSensorService);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 4:
                            SensorSettingsUI sensorSettings = new SensorSettingsUI();
                            sensorSettings.run(geographicAreaService, mockSensorTypeList, areaSensorService);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 5:
                            EnergyGridSettingsUI energyGridSettings = new EnergyGridSettingsUI();
                            energyGridSettings.run(house, energyGridService, roomService);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 6:
                            HouseMonitoringUI houseM = new HouseMonitoringUI();
                            houseM.run(house, areaSensorService, houseSensorService, readingService, roomService);
                            returnToMenu(enterToReturnToConsole);
                            activeInput = false;
                            break;
                        case 7:
                            EnergyConsumptionUI energyConsumptionUI = new EnergyConsumptionUI();
                            energyConsumptionUI.run(house, roomService, energyGridService);
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

    private static House mainHouse(HouseRepository houseRepository, int gridMeteringPeriod, int deviceMeteringPeriod, List<String> deviceTypeConfig) {
        House house;
        Optional<House> aux = houseRepository.findById("01");
        if (aux.isPresent()) {
            house = aux.get();
            house.setGridMeteringPeriod(gridMeteringPeriod);
            house.setDeviceMeteringPeriod(deviceMeteringPeriod);
            house.setDeviceTypeList(deviceTypeConfig);
            houseRepository.save(house);
            return house;
        }
        house = new House("01", new Local(0, 0, 0), gridMeteringPeriod, deviceMeteringPeriod, deviceTypeConfig);
        return houseRepository.save(house);
    }
}