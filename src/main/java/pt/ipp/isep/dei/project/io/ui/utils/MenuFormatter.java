package pt.ipp.isep.dei.project.io.ui.utils;

import java.util.*;

public final class MenuFormatter {


    private static final String NORMAL_TEXT = "\u001B[2m";
    private static final String BOLD_TEXT = "\u001B[1m";
    private static final String TOP_RIGHT_BOX_CHARACTER = "╗";
    private static final String BOTTOM_LEFT_BOX_CHARACTER = "╚";
    private static final String TOP_LEFT_BOX_CHARACTER = "╔";
    private static final String BOTTOM_RIGHT_BOX_CHARACTER = "╝";
    private static final String LINE_BOX_CHARACTER = "═";
    private static final String LEFT_BOX_BRANCH = "╠";
    private static final String VERTICAL_LINE_BOX_CHARACTER = "║";
    private static final String RIGHT_BOX_BRANCH = "╣";
    private static final String BOX_MARGIN = "   ";


    private MenuFormatter() {
        throw new AssertionError();
    }

    /**
     * This method is used to print a menu to the console.
     * @param title is the title of the menu we want to print (for instance, "Main Menu")
     * @param options is a list of strings with all the options we want to print.
     */

    public static void showMenu(String title, List<String> options) {
        // This line gets the largest option in the menu, as a string.

        String longestOption = Collections.max(options, Comparator.comparingInt(String::length));

        // This line checks if the size of the longest option we got before is smaller than the size of the title.
        // If the title is wider, we need to use the width of the title instead of the longest string.

        int boxWidth = Integer.max(longestOption.length(), title.length());

        // We increase the width by a fixed number in order to guarantee some padding / white space after the longest option.

        boxWidth += 8;

        // This is the size of the list, used for numbering it. The last option is printed separate from the others.

        int indexSize = options.size() - 1;
        String lastOption = options.get(indexSize);

        printMenuHeader(title, boxWidth);
        printMenuBody(options, boxWidth);
        printMenuFooter(lastOption, boxWidth);
    }


    /**
     * This method receives a list of strings to print and prints them inside a menu box.
     * @param options is the list of strings we want to print.
     * @param boxWidth is the width of the box we want to print.
     */

    private static void printMenuBody(List<String> options, int boxWidth) {
        // We start the cycle at 1 so that the first option is already printed with index 1. Index 0 is usually the last option.
        for (int i = 1; i <= options.size() -1 ; i++) {
            String option = options.get(i - 1);
            printMenuOption(i, option, boxWidth);
        }
    }

    /**
     * This method receives the last option of a menu as a string, and prints it with a line break (separate from the
     * other options, as it is usually the "leave" or "go back" option), and with the bottom part of the box
     * to close it.
     * @param leaveOption is the last option of the menu, as a string.
     * @param boxWidth is the width of the box we're printing.
     */
    private static void printMenuFooter(String leaveOption, int boxWidth) {
        printBoxEmptyLine(boxWidth);
        printMenuOption(0, leaveOption, boxWidth);
        printMenuBottomLine(boxWidth);
    }

    /**
     * This method prints the header of a menu, with the top part of the box as well as the title of the menu,
     * and a line break before the options begin.
     * @param menuTitle is the title we want to give the menu, as a string.
     * @param boxWidth is the width of the box we want to print.
     */
    private static void printMenuHeader(String menuTitle, int boxWidth) {
        printBoxHeader(boxWidth);
        printMenuTitle(menuTitle, boxWidth);
        printMenuHSeparator(boxWidth);
        printBoxEmptyLine(boxWidth);
    }


    /**
     * This method prints a particular String in the list of options as a line that's part of the menu, including with
     * an index.
     * @param indexOnPrintedList is the index we want to give the option. If it's -1, no index is given to the option (as
     *                           with titles, for instance.
     * @param option is the string we want to print for the option.
     * @param boxWidth is the width of the box we want to print.
     */
    private static void printMenuOption(int indexOnPrintedList, String option, int boxWidth) {
        // Adds the index.

        String optionNumber;
        if (indexOnPrintedList == -1) {
            optionNumber = "";
        } else {
            optionNumber = String.valueOf(indexOnPrintedList);
        }
        String formattedLine = optionNumber + ". " + option;

        // We want to find out how much whitespace there is on both sides of the box, so we can properly calculate the padding necessary.

        int whiteSpaceInLine = boxWidth - option.length();

        // The space to the left of the option is a fixed number of 5 characters with whitespace + index, so we subtract that.

        int whiteSpaceToRightOfLine = whiteSpaceInLine - 5;
        if (indexOnPrintedList >= 10){whiteSpaceToRightOfLine = whiteSpaceInLine - 6;}

        String optionLine = BOX_MARGIN + VERTICAL_LINE_BOX_CHARACTER + "  " + formattedLine + repeatString(" ", whiteSpaceToRightOfLine) + VERTICAL_LINE_BOX_CHARACTER;
        System.out.println(optionLine);
    }

    /**
     * This method prints the title of the menu in bold, centered inside the box.
     * @param title is the title of the menu we want to print.
     * @param boxWidth is the width of the box.
     */
    private static void printMenuTitle(String title, int boxWidth) {
        // We want to find out how much whitespace there is on both sides of the box, so we can properly calculate the padding necessary.

        int whiteSpace = boxWidth - title.length();

        // This makes the title bold.

        String formattedTitle = BOLD_TEXT + title + NORMAL_TEXT;

        // Whitespace / 2 might not be an int, so we have to round the value.

        int whiteSpaceLeftOfLine = Math.round((float)(whiteSpace / 2));
        int whiteSpaceRightOfLine = Math.round((float)(whiteSpace / 2));

        if (whiteSpace % 2 != 0) {
            whiteSpaceRightOfLine++;
        }

        // This section adds the correct amount of whitespace to the left and right of the title, in order to print the box.

        String leftOfTitle = BOX_MARGIN + VERTICAL_LINE_BOX_CHARACTER + repeatString(" ", whiteSpaceLeftOfLine);
        String rightOfTitle = repeatString(" ", whiteSpaceRightOfLine) + VERTICAL_LINE_BOX_CHARACTER;

        System.out.println(leftOfTitle + formattedTitle + rightOfTitle);
    }

    /**
     * This method prints out the header of the box with the proper corners.
     * @param boxWidth is the width of the box we want to print.
     */
    private static void printBoxHeader(int boxWidth) {
        String line = repeatString(LINE_BOX_CHARACTER, boxWidth);
        System.out.println("\n\n\n");
        System.out.println(BOX_MARGIN + TOP_LEFT_BOX_CHARACTER + line + TOP_RIGHT_BOX_CHARACTER);
    }

    /**
     * This method prints out an empty line inside the box, usually for line breaks.
     * @param boxWidth is the width of the box we want to print.
     */
    private static void printBoxEmptyLine(int boxWidth) {
        String line = repeatString(" ", boxWidth);
        System.out.println(BOX_MARGIN + VERTICAL_LINE_BOX_CHARACTER + line + VERTICAL_LINE_BOX_CHARACTER);
    }

    /**
     * This method prints out the bottom line of the box with the proper corners.
     * @param boxWidth is the width of the box we want to print.
     */
    private static void printMenuBottomLine(int boxWidth) {
        String middle = repeatString(LINE_BOX_CHARACTER, boxWidth);
        System.out.println(BOX_MARGIN + BOTTOM_LEFT_BOX_CHARACTER + middle + BOTTOM_RIGHT_BOX_CHARACTER);
    }

    /**
     * This method prints a separation line inside the box, usually for separating the title from the options.
     * @param boxWidth is the width of the box we want to print.
     */
    private static void printMenuHSeparator(int boxWidth) {
        String middle = repeatString(LINE_BOX_CHARACTER, boxWidth);
        System.out.println(BOX_MARGIN + LEFT_BOX_BRANCH + middle + RIGHT_BOX_BRANCH);
    }

    /**
     * This method repeats a string a given number of times, joining them with no meaningful separation.
     * @param stringToRepeat is the string we want to repeat.
     * @param numberOfRepetitions is the number of times we want to repeat the string.
     * @return is the string that results from repeating the string the given amount of times.
     */
    private static String repeatString(String stringToRepeat, int numberOfRepetitions) {
        Collection<String> stringCopies = Collections.nCopies(numberOfRepetitions, stringToRepeat);
        return String.join("", stringCopies);
    }
}
