package pt.ipp.isep.dei.project.io.ui.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Utility class that aggregates common Date methods used by other classes.
 */
public class DateUtils {

    private DateUtils() {
    }

    /**
     * Method will read a group of values from user and return a date (year, month, day, hour and
     * minute). It will only accept valid numbers.
     *
     * @return date introduced by user
     */
    public static Date getInputYearMonthDay() {
        Scanner scan = new Scanner(System.in);
        int year = getInputYear();
        boolean isLeapyear = new GregorianCalendar().isLeapYear(year);
        int month = getInputMonth();
        int day = getInputDay(isLeapyear, month);
        Date date = new GregorianCalendar(year, month, day).getTime();

        String dateResultFormatted = formatDateNoTime(date);

        System.out.println(("You have chosen the following date:\n") + dateResultFormatted + "\n");
        scan.nextLine();
        return date;
    }

    /**
     * Method thar will removeGeographicArea the hours, minutes and seconds from a Date
     *
     * @param date input date to format
     * @return string that represents the input date but without hour, min. sec
     */
    public static String formatDateNoTime(Date date) {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    /**
     * Method will read a group of values from user and return a date (year, month, day, hour and
     * minute). It will only accept valid numbers.
     *
     * @return date introduced by user
     */
    public static Date getInputYearMonthDayHourMin() {
        int year = getInputYear();
        boolean isLeapYear = new GregorianCalendar().isLeapYear(year);
        int month = getInputMonth();
        int day = getInputDay(isLeapYear, month);
        int hour = getInputHour();
        int minute = getInputMinute();
        Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
        System.out.println(("You have chosen the following date:\n") + date.toString() + "\n");
        return date;
    }

    /**
     * Method will ask the user to introduce a year and will return an int
     * that corresponds to the number introduced by user. User can only introduce
     * values from the gregorian calendar
     *
     * @return int of the year introduced by user
     */
    private static int getInputYear() {
        Scanner scan = new Scanner(System.in);
        int year = -1;
        while (year <= 1581) { //Gregorian Calendar was introduced in 1582, so was the concept of leap year
            year = getInputDateAsInt(scan, "year");
            scan.nextLine();
        }
        return year;
    }

    /**
     * Method will ask the user for a month and will return an int of that value subtracted by one
     *
     * @return int of the month introduced by user
     */
    private static int getInputMonth() {
        Scanner scan = new Scanner(System.in);
        int month = -1;
        while (month <= -1 || month > 11) { // -1 e 11 porque depois se subtrai um valor
            month = getInputDateAsInt(scan, "month") - 1;
            scan.nextLine();
        }
        return month;
    }

    /**
     * Method will ask the user to introduce a day and will return an int
     * that corresponds to the number introduced by user. User can only introduce
     * valid values
     *
     * @return int of the day introduced by user
     */
    private static int getInputDay(boolean isLeapyear, int month) {
        if (month == 1) {
            return getInputFebruaryDay(isLeapyear);
        } else {
            return getInputNotFebruaryDay(month);
        }
    }

    /**
     * Method will receive a boolean and ask the user for a day and will return an int
     * that corresponds to the number introduced by user. The boolean is true in case of leap
     * year and the user can only introduce a number from 1 to 29. Otherwise, it can only a number
     * from 0 to 28.
     *
     * @return int of the day introduced by user
     */
    private static int getInputFebruaryDay(boolean isLeapyear) {
        Scanner scan = new Scanner(System.in);
        int day = -1;
        if (isLeapyear) {
            while (day < 1 || day > 29) {
                day = getInputDateAsInt(scan, "day");
                scan.nextLine();
            }
            return day;
        }
        while (day < 1 || day > 28) {
            day = getInputDateAsInt(scan, "day");
            scan.nextLine();
        }
        return day;
    }

    /**
     * Method will receive an int of a month and ask the user for a day. It will return an int
     * that corresponds to the number introduced by user. The user can only introduce valid days
     * for that month (January - 0 to 31, April - 0 to 30).
     *
     * @return int of the day introduced by user
     */
    private static int getInputNotFebruaryDay(int month) {
        Scanner scan = new Scanner(System.in);
        int day = -1;
        if (isJanuaryMarchMay(month) || isJulyAugust(month) || isOctoberDecember(month)) {
            while (day < 1 || day > 31) {
                day = getInputDateAsInt(scan, "day");
                scan.nextLine();
            }
            return day;
        }
        while (day < 1 || day > 30) {
            day = getInputDateAsInt(scan, "day");
            scan.nextLine();
        }
        return day;
    }

    /**
     * Method will ask the user for an hour and will return an int
     * that corresponds to the number introduced by user. User can only introduce
     * valid values (0 to 23).
     *
     * @return int of the hour introduced by user
     */
    private static int getInputHour() {
        Scanner scan = new Scanner(System.in);
        int hour = -1;
        while (hour < 0 || hour > 23) {
            hour = getInputDateAsInt(scan, "hour");
            scan.nextLine();
        }
        return hour;
    }

    /**
     * Method will ask the user to introduce a minute and will return an int
     * that corresponds to the number introduced by user. User can only introduce
     * valid values (0 to 59).
     *
     * @return int of the minute introduced by user
     */
    private static int getInputMinute() {
        Scanner scan = new Scanner(System.in);
        int minute = -1;
        while (minute < 0 || minute > 59) {
            minute = getInputDateAsInt(scan, "minute");
            scan.nextLine();
        }
        return minute;
    }

    /**
     * Method to get a date as an int.
     *
     * @param scan     the scanner to read input
     * @param dataType the type of date to read (year, month or day)
     * @return value read from the user
     */
    private static int getInputDateAsInt(Scanner scan, String dataType) {
        System.out.println("Enter a " + dataType + ":");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Not a valid " + dataType + ". Try again");
        }
        return scan.nextInt();
    }

    //The next three methods were created because of a code smell

    /**
     * Method that checks if the month given is January, March or May, returning
     * true in case it is, false in case it is not.
     *
     * @param month month to test
     */
    static boolean isJanuaryMarchMay(int month) {
        return month == 0 || month == 2 || month == 4;
    }

    /**
     * Method that checks if the month given is July or August, returning
     * true in case it is, false in case it is not.
     *
     * @param month month to test
     */
    static boolean isJulyAugust(int month) {
        return month == 6 || month == 7;
    }

    /**
     * Method that checks if the month given is October or December, returning
     * true in case it is, false in case it is not.
     *
     * @param month month to test
     */
    static boolean isOctoberDecember(int month) {
        return month == 9 || month == 11;
    }

}