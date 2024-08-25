package com.amrbsapp.view;

public class ViewConsole {

    public static void printLoginMenu() {
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");
    }

    public static void printMainMenu() {
        System.out.println("Welcome to the Meeting Room Booking System");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Manager");
        System.out.println("3. Login as Member");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public static void printAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("0. View All Rooms");
        System.out.println("1. Create Room");
        System.out.println("2. Edit Room");
        System.out.println("3. Delete Room");
        System.out.println("4. Show All Users");
        System.out.println("5. Add User");
        System.out.println("6. Edit User");
        System.out.println("7. Delete User");
        System.out.println("8. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    public static void printManagerMenu() {
        System.out.println("\nManager Menu:");
        System.out.println("1. View Available Rooms");
        System.out.println("2. Organize Meeting");
        System.out.println("3. Edit Meeting");
        System.out.println("4. View Bookings/Meetings");
        System.out.println("5. Cancel Booking");
        System.out.println("6. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    public static void printMemberMenu() {
        System.out.println("\nMember Menu:");
        System.out.println("1. View Meeting Schedule");
        System.out.println("2. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    public static void printReturningToMainMenu() {
        System.out.println("Returning to Main Menu...");
    }

    public static void printInvalidOption() {
        System.out.println("Invalid option. Please try again.");
    }

    public static void printExiting() {
        System.out.println("Exiting...");
    }
}
