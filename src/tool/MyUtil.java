/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class MyUtil {

    public static Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        do {
            System.out.print(message + ": ");//Pxxxxx
            input = sc.nextLine().toUpperCase().trim();
            if (validStr(input, pattern)) {
                return input;
            }
            System.out.println("Invalid input!");
        } while (true);

    }

    public static double readDouble(String message, double min) {
        String input = "";
        double result;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            try {
                if (input.isEmpty()) return -1;
                result = Double.parseDouble(input);
                if (result >= min) {
                    return result;
                }
                System.out.println("Invalid input!");
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        } while (true);
    }

    public static int readInt(String message, int min) {
        String input = "";
        int result;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            try {
                if (input.isEmpty())    return -1;
                result = Integer.parseInt(input);
                if (result >= min) {
                    return result;
                }
                System.out.println("Invalid input!");
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }

        } while (true);

    }

    public static boolean readAskUser() {
        do {
            System.out.print("Input user: ");
            String inputUser = sc.nextLine().trim().toLowerCase();
            if (inputUser.matches("[t,f,1,0]|true|false|yes|no|n|y")) {
                if (inputUser.matches("[t,1]|true|yes|y")) {
                    return true;
                } else {
                    return false;
                }
            }
            System.out.println("Invalid input!");
        } while (true);
    }

}
