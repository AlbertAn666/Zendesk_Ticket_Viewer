package com.company;

import java.util.Scanner;

public class ViewerConfig {
    public final static String DOMAIN = "zccyinanan";
    public final static String API_TOKEN = "nZLs6q8PMmNmAFYPVfasexsFAINgBSLOjGkiqGSP";
    public final static String USER_NAME = "albertan@bu.edu";
    public final static String PASSWORD = "Xiangyun99";

    public final static String RESET_COLOR = "\u001B[0m";
    public final static String RED_COLOR = "\u001B[31m";
    public final static String GREEN_COLOR = "\u001B[32m";
    public final static String YELLOW_COLOR = "\u001B[33m";
    public final static String BLUE_COLOR = "\u001B[34m";

    public static void showTitle() {
        System.out.println("--------------------------------");
        System.out.println("Welcome to Zendesk Ticket Viewer");
        System.out.println("--------------------------------");
    }

    public static void showMenu() {
        System.out.println(BLUE_COLOR + "Select your option from below: " + RESET_COLOR);
        System.out.println(BLUE_COLOR + "* 1. View all tickets" + RESET_COLOR);
        System.out.println(BLUE_COLOR + "* 2. View a ticket" + RESET_COLOR);
        System.out.println(BLUE_COLOR + "* 3. Exit" + RESET_COLOR);
        System.out.println(BLUE_COLOR + "Input your option: " + RESET_COLOR);
    }

    public static int chooseOption(int range) {
        System.out.println(BLUE_COLOR + "Please input the ticket number" + RESET_COLOR);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while(choice <= 0 || choice > range) {
            System.out.println(RED_COLOR + "Please select from 1-" + range + RESET_COLOR);
            choice = scanner.nextInt();
        }
        return choice;
    }

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
