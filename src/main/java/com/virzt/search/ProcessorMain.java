package com.virzt.search;

import com.virzt.search.utils.SettingUtils;

/**
 * Main class. Run this file to start the program
 * @author Hemed Ali Al Ruwehy
 * Date: 18-11-2016
 */
public class ProcessorMain {

    /**
     * Main method for the program
     **/
    public static void main(String[] args) {
        System.out.println(
                "\n================================================================"
                        + "\n\tHello there! The program is ready. Please type your input."
                        + "\n================================================================");

        while (SettingUtils.PROCEED) {
            String userInput = InputProcessor.getInputFromConsole();
            InputProcessor.processInput(userInput);
        }
    }
}