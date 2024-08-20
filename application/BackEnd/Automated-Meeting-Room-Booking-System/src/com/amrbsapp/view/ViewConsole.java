package com.amrbsapp.view;

import com.amrbsapp.controller.RoomController;
import com.amrbsapp.dao.RoomDAO;
import com.amrbsapp.dao.impl.RoomImpl;
import com.amrbsapp.entity.Room;
import com.amrbsapp.service.RoomService;
import com.amrbsapp.service.impl.RoomServiceImpl;

import java.util.Scanner;

public class ViewConsole {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mainChoice = 0;

        RoomController roomController = new RoomController(new RoomServiceImpl(new RoomImpl()));

//        roomController.addRoom(new Room(1, 10, null,false));
//        roomController.addRoom(new Room(2, 5, null,false));
//        roomController.addRoom(new Room(3, 6, null,true));
//        roomController.addRoom(new Room(4, 2, null,false));
//        roomController.updateRoom(new Room(1, 7, null,false));
//        roomController.getAllRooms().forEach(System.out::println);
//        roomController.getAvailableRooms().forEach(System.out::println);
//        System.out.println(roomController.getRoom(1).getRoomID());
//        roomController.deleteRoom(2);
//        do {
//            System.out.println("Welcome to the Meeting Room Booking System");
//            System.out.println("1. Login as Admin");
//            System.out.println("2. Login as Manager");
//            System.out.println("3. Login as Member");
//            System.out.println("4. Exit");
//            System.out.print("Choose an option: ");
//            mainChoice = scanner.nextInt();
//
//            switch (mainChoice) {
//                case 1:
//                    int adminChoice = 0;
//                    do {
//                        System.out.println("\nAdmin Menu:");
//                        System.out.println("1. Create Room");
//                        System.out.println("2. Edit Room");
//                        System.out.println("3. Manage Users");
//                        System.out.println("4. Back to Main Menu");
//                        System.out.print("Choose an option: ");
//                        adminChoice = scanner.nextInt();
//
//                        switch (adminChoice) {
//                            case 1:
//                                createRoom();
//                                break;
//                            case 2:
//                                editRoom();
//                                break;
//                            case 3:
//                                manageUsers();
//                                break;
//                            case 4:
//                                System.out.println("Returning to Main Menu...");
//                                break;
//                            default:
//                                System.out.println("Invalid option. Please try again.");
//                        }
//                    } while (adminChoice != 4);
//                    break;
//
//                case 2:
//                    int managerChoice = 0;
//                    do {
//                        System.out.println("\nManager Menu:");
//                        System.out.println("1. Book Room");
//                        System.out.println("2. Organize Meeting");
//                        System.out.println("3. View Bookings");
//                        System.out.println("4. Back to Main Menu");
//                        System.out.print("Choose an option: ");
//                        managerChoice = scanner.nextInt();
//
//                        switch (managerChoice) {
//                            case 1:
//                                bookRoom();
//                                break;
//                            case 2:
//                                organizeMeeting();
//                                break;
//                            case 3:
//                                viewBookings();
//                                break;
//                            case 4:
//                                System.out.println("Returning to Main Menu...");
//                                break;
//                            default:
//                                System.out.println("Invalid option. Please try again.");
//                        }
//                    } while (managerChoice != 4);
//                    break;
//
//                case 3:
//                    int memberChoice = 0;
//                    do {
//                        System.out.println("\nMember Menu:");
//                        System.out.println("1. View Meeting Schedule");
//                        System.out.println("2. Back to Main Menu");
//                        System.out.print("Choose an option: ");
//                        memberChoice = scanner.nextInt();
//
//                        switch (memberChoice) {
//                            case 1:
//                                viewMeetingSchedule();
//                                break;
//                            case 2:
//                                System.out.println("Returning to Main Menu...");
//                                break;
//                            default:
//                                System.out.println("Invalid option. Please try again.");
//                        }
//                    } while (memberChoice != 2);
//                    break;
//
//                case 4:
//                    System.out.println("Exiting...");
//                    break;
//
//                default:
//                    System.out.println("Invalid option. Please try again.");
//            }
//        } while (mainChoice != 4);

        scanner.close();
    }
}
