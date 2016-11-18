package com.virzt.search;

import com.virzt.search.utils.SettingUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;


/**
 * Abstract: The class which holds static methods for processing the input
 * based on the programming test from Virzt
 * <p>
 *
 * @author Hemed Ali Al Ruwehy
 *         Date: 18-11-2016
 * @version 1.0-SNAPSHOT
 **/
public class InputProcessor {
    private static Map<String, Integer> map = new HashMap<>();
    private static Stack<String> singleValueStack = new Stack<>();
    private static Stack<String> multipleValueStack = new Stack<>();

    /**
     * Process a given input
     *
     * @param input in the form of String
     **/
    public static void processInput(String input) {
        if (input.contains("=")) {
            String[] userInput = input.split("=");
            String key = userInput[0].trim();
            String value = userInput[1].trim();
            //Process single value
            if (value.indexOf('+') == -1) {
                processSingleValue(key, value);
            }
            //Process multiple values
            if (value.indexOf('+') >= 0) {
                processMultipleValues(key, value);
            }

        }
    }

    /**
     * Get input from the console
     **/
    public static String getInputFromConsole() {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine().trim();
        return input;
    }

    /**
     * Process single input value
     *
     * @param key
     * @param value
     **/
    public static void processSingleValue(String key, String value) {
        // Contains only one value and it is numeric
        if (SettingUtils.isNumeric(value)) {
            map.put(key, Integer.parseInt(value));
            printOutput(key, value);

            while (singleValueStack.size() > 0) {
                String[] v = singleValueStack.pop().split("=");
                processSingleValue(v[0], v[1]);
            }
        } else { //Contains one value, but it's not numeric
            if (map.get(value) != null) {
                map.put(key, map.get(value));
                printOutput(key, map.get(value));
            } else {//If the variable is not yet assigned, get more inputs
                singleValueStack.push(key + "=" + value);
                processInput(getInputFromConsole());
            }
        }
    }


    /**
     * Process multiple input values that are separated by a plus sign.
     *
     * @param key
     * @param value
     **/
    public static void processMultipleValues(String key, String value) {
        String[] v = value.split("\\+");
        boolean proceed = true;
        int sum = 0;
        //If the expression does not contain alphabets [A-Z]
        if (!value.matches(".*[a-z-A-Z].*")) {
            for (int i = 0; i < v.length; i++) {
                sum = sum + Integer.parseInt(v[i].trim());
            }
            map.put(key, sum);
            processSingleValue(key, String.valueOf(sum));
        } else {
            int count = 0;
            for (int i = 0; i < v.length && proceed; i++) {
                String token = v[i].trim();
                if (!SettingUtils.isNumeric(token)) {
                    //If the variable is already assigned
                    if (map.get(token) != null) {
                        sum = sum + map.get(token);
                        count++;
                    } else {//If values are not assigned yet, get more input and store the relation to the stack
                        proceed = false;
                        multipleValueStack.push(key + "=" + value);
                        processInput(getInputFromConsole());
                    }
                } else {
                    sum = sum + Integer.parseInt(token);
                    count++;
                }
            }
            if (count == v.length) {
                map.put(key, sum);
                processSingleValue(key, String.valueOf(sum));
            }

            //If there exist an expression in the stack, process it.
            while (multipleValueStack.size() > 0) {
                String[] s = multipleValueStack.pop().split("=");
                processMultipleValues(s[0], s[1]);
            }
        }
    }

    /**
     * Print out the output
     *
     * @param key
     * @param value
     ***/
    public static void printOutput(String key, String value) {
        System.out.println("===> " + key + " = " + value);
    }

    /**
     * Print out the output
     *
     * @param key
     * @param value
     ***/
    public static void printOutput(String key, int value) {
        System.out.println("===> " + key + " = " + value);
    }

}