package pt.ipp.isep.dei.project.io.ui;

import java.util.Scanner;

public class UtilsUI {

    public static final String INVALID_OPTION = "Please enter a valid option";

    public static int readInputNumberAsInt() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextDouble()) {
            System.out.println(INVALID_OPTION);
            scan.next();
        }
        Double option = scan.nextDouble();
        return option.intValue();
    }

}
