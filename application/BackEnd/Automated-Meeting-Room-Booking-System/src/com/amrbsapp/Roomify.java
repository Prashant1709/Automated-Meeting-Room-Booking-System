package com.amrbsapp;

import com.amrbsapp.controller.*;
import com.amrbsapp.entity.*;
import com.amrbsapp.view.ViewConsole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Roomify {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoomController roomController = ControllerProvider.getRoomController();
        UserController userController = ControllerProvider.getUserController();
        BookingController bookingController = ControllerProvider.getBookingController();
        MeetingController meetingController = ControllerProvider.getMeetingController();
        AmenityController amenityController = ControllerProvider.getAmenityController();

        int mainChoice;
        int invalidLoginAttempts = 2;
        do {
            ViewConsole.printLoginMenu();
            mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    // Simulating a login process
                    System.out.print("Enter Username: ");
                    String username = scanner.next();
                    System.out.print("Enter Password: ");
                    String password = scanner.next();

                    if (invalidLoginAttempts == 0) {
                        System.out.println("Too many invalid login attempts. Exiting...");
                        exit(0);
                        break;
                    }
                    String userType = ControllerProvider.getUserController().authenticateUser(username, password);
                    User user = ControllerProvider.getUserController().login(username, password);
                    List<Amenity> amenityList = amenityController.getAllAmenities();
                    switch (userType) {
                        case "Admin":
                            int adminChoice;
                            do {
                                ViewConsole.printAdminMenu();
                                adminChoice = scanner.nextInt();

                                switch (adminChoice) {
                                    case 0:
                                        // viewRooms();
                                        List<Room> rooms = roomController.getAllRooms();
                                        System.out.println("RoomId Capacity");
                                        for (Room room : rooms) {
                                            System.out.println(room.getRoomID() + ". " + room.getCapacity());
                                        }
                                        break;
                                    case 1:
                                        // createRoom() but take inputs from user;
                                        System.out.println("Enter Room ID, Capacity: ");
                                        int roomId = scanner.nextInt();
                                        int capacity = scanner.nextInt();
                                        System.out.println("Select amenities for the room:");
                                        for (Amenity amenity : amenityList) {
                                            System.out.println(amenity.getAmenityID() + ". " + amenity.getName()+" "+amenity.getCreditCost());
                                        }
                                        List<Amenity> amenities = new ArrayList<>();
                                        do {
                                            System.out.println("Enter amenity ID to add to room (0 to exit): ");
                                            int amenityId = scanner.nextInt();
                                            if (amenityId == 0) {
                                                break;
                                            }
                                            Amenity amenity = amenityController.getAmenity(amenityId);
                                            amenities.add(amenity);
                                        } while (true);
                                        roomController.addRoom(new Room(roomId,capacity, amenities, false));
                                        break;
                                    case 2:
                                        // editRoom() but take inputs from user;
                                        int editRoomId = scanner.nextInt();
                                        int editCapacity = scanner.nextInt();
                                        System.out.println("Select amenities for the room:");
                                        for (Amenity amenity : amenityList) {
                                            System.out.println(amenity.getAmenityID() + ". " + amenity.getName()+" "+amenity.getCreditCost());
                                        }
                                        List<Amenity> editAmenities = new ArrayList<>();
                                        do {
                                            System.out.println("Enter amenity ID to add to room (0 to finish): ");
                                            int amenityId = scanner.nextInt();
                                            if (amenityId == 0) {
                                                break;
                                            }
                                            Amenity amenity = amenityController.getAmenity(amenityId);
                                            editAmenities.add(amenity);
                                        } while (true);
                                        roomController.updateRoom(new Room(editRoomId,editCapacity, editAmenities, false));
                                        break;
                                    case 3:
                                        // deleteRoom();
                                        System.out.println("Enter Room ID to delete: ");
                                        int deleteRoomId = scanner.nextInt();
                                        roomController.deleteRoom(deleteRoomId);
                                        break;
                                    case 4:
                                        // manageUsers();
                                        List<User> users = userController.getAllUsers();
                                        for (User alluser : users) {
                                            System.out.println(alluser.getUserID() + ". " + alluser.getName() + " " + alluser.getEmail() + " " + alluser.getRole());
                                        }
                                        break;
                                    case 5:
                                        // addUser() but take inputs from user;
                                        System.out.println("Enter User ID, Name, Email, Password, Role (ADMIN, MANAGER, MEMBER): ");
                                        int userId = scanner.nextInt();
                                        String name = scanner.next();
                                        String email = scanner.next();
                                        String pwd = scanner.next();
                                        String role = scanner.next();
                                        RoleType roleType = RoleType.valueOf(role.toUpperCase());
                                        if (roleType.name().equals("ADMIN")) {
                                            userController.addUser(new Admin(userId, name, email, pwd, roleType));
                                        } else {
                                            if (roleType.name().equals("MANAGER")) {
                                                userController.addUser(new Manager(userId, name, email, pwd, roleType, 2000));
                                            } else {
                                                userController.addUser(new Member(userId, name, email, pwd, roleType));
                                            }
                                        }
                                        break;
                                    case 6:
                                        // editUser() but take inputs from user;
                                        System.out.println("Enter User ID, Name, Email, Password, Role (ADMIN, MANAGER, MEMBER): ");
                                        int editUserId = 0;
                                        String editName = "", editEmail = "", editPwd = "", editRole = "";
                                        editUserId = scanner.nextInt();
                                        editName = scanner.next();
                                        editEmail = scanner.next();
                                        editPwd = scanner.next();
                                        editRole = scanner.next();
                                        RoleType editRoleType = RoleType.valueOf(editRole.toUpperCase());
                                        if (editRoleType.name().equals("ADMIN")) {
                                            userController.updateUser(new Admin(editUserId, editName, editEmail, editPwd, editRoleType));
                                        } else {
                                            if (editRoleType.name().equals("MANAGER")) {
                                                userController.updateUser(new Manager(editUserId, editName, editEmail, editPwd, editRoleType, 2000));
                                            } else {
                                                userController.updateUser(new Member(editUserId, editName, editEmail, editPwd, editRoleType));
                                            }
                                        }
                                        break;
                                    case 7:
                                        // deleteUser();
                                        System.out.println("Enter User ID to delete: ");
                                        int deleteUserId = scanner.nextInt();
                                        userController.deleteUser(deleteUserId);
                                        break;
                                    case 8:
                                        ViewConsole.printReturningToMainMenu();
                                        break;
                                    default:
                                        ViewConsole.printInvalidOption();
                                }
                            } while (adminChoice != 8);
                            break;

                        case "Manager":
                            int managerChoice;
                            do {
                                ViewConsole.printManagerMenu();
                                if (userController.checkMondayAnd6PM()) {
                                    userController.assignCredits(user.getUserID(), 2000);
                                }
                                int credits = userController.getUser(user.getUserID()).getRole()==RoleType.MANAGER?((Manager)user).getCredits():0;
                                managerChoice = scanner.nextInt();
                                System.out.println("Credits: "+credits);
                                switch (managerChoice) {
                                    case 1:
                                        // view available rooms;
                                        System.out.println("Available Rooms:");
                                        System.out.println("RoomId Capacity");
                                        List<Room> rooms = roomController.getAvailableRooms();
                                        for (Room room : rooms) {
                                            System.out.println(room.getRoomID() + ". " + room.getCapacity());
                                        }
                                        break;
                                    case 2:
                                        // organizeMeeting and if save meeting return true then save it as booking and reduce credits;
                                        System.out.println("Enter Meeting ID, Room ID, Start Time, Meeting Type, Duration: ");
                                        int meetingId = scanner.nextInt();
                                        int roomId = scanner.nextInt();
                                        Room room = roomController.getRoom(roomId);
                                        List<User> users = userController.getAllUsers();
                                        System.out.println("Select users for the meeting:");
                                        for (User alluser : users) {
                                            System.out.println(alluser.getUserID() + ". " + alluser.getName() + " " + alluser.getEmail() + " " + alluser.getRole());
                                        }
                                        List<User> attendees = new ArrayList<>();
                                        do {
                                            System.out.println("Enter user ID to add to meeting (0 to finish): ");
                                            int userId = scanner.nextInt();
                                            if (userId == 0) {
                                                break;
                                            }
                                            User attendee = userController.getUser(userId);
                                            attendees.add(attendee);
                                        } while (true);
                                        System.out.println("Enter Start Time (yyyy-MM-ddTHH:mm:ss): ");
                                        String startTime = scanner.next(); // "2024-09-01T10:00:00"
                                        LocalDateTime startDateTime = LocalDateTime.parse(startTime);
                                        System.out.println("Select Meeting Type:");
                                        MeetingType[] meetingTypes = MeetingType.values();
                                        for (MeetingType meetingType : meetingTypes) {
                                            System.out.println(meetingType.ordinal() + ". " + meetingType);
                                        }
                                        int meetingType = scanner.nextInt();
                                        System.out.println("Enter duration of meeting in minutes: ");
                                        int duration = scanner.nextInt();
                                        System.out.println("Select amenities for the room:");
                                        for (Amenity amenity : amenityList) {
                                            System.out.println(amenity.getAmenityID() + ". " + amenity.getName()+" "+amenity.getCreditCost());
                                        }
                                        List<Amenity> amenities = new ArrayList<>();
                                        do {
                                            System.out.println("Enter amenity ID to add to room (0 to finish): ");
                                            int amenityId = scanner.nextInt();
                                            if (amenityId == 0) {
                                                break;
                                            }
                                            Amenity amenity = amenityController.getAmenity(amenityId);
                                            credits = credits - amenity.getCreditCost();
                                            amenities.add(amenity);
                                        } while (true);
                                        Meeting meeting = new Meeting(meetingId, room, attendees, startDateTime, MeetingType.valueOf(meetingTypes[meetingType].name()), duration,amenities);
                                        boolean isAdded = meetingController.addMeeting(meeting);
                                        if (isAdded) {
                                            userController.assignCredits(user.getUserID(), credits);
                                            bookingController.addBooking(new Booking(meetingId, String.valueOf(room.getRoomID()),String.valueOf(user.getUserID()), startDateTime, duration));
                                        }
                                        break;
                                    case 3:
                                        // editMeeting();
                                        System.out.println("Enter Meeting ID, Room ID, User ID, Start Time, Meeting Type, Duration: ");
                                        int editMeetingId = scanner.nextInt();
                                        int editRoomId = scanner.nextInt();
                                        int editUserId = scanner.nextInt();
                                        String editStartTime = scanner.next();
                                        LocalDateTime editStartDateTime = LocalDateTime.parse(editStartTime);
                                        System.out.println("Select Meeting Type:");
                                        MeetingType[] editMeetingTypes = MeetingType.values();
                                        for (MeetingType meetType : editMeetingTypes) {
                                            System.out.println(meetType.ordinal() + ". " + meetType);
                                        }
                                        int editMeetingType = scanner.nextInt();
                                        int editDuration = scanner.nextInt();
                                        Meeting editMeeting = new Meeting(editMeetingId, roomController.getRoom(editRoomId), userController.getAllUsers(), editStartDateTime, MeetingType.values()[editMeetingType], editDuration,amenityList);
                                        boolean isUpdated = meetingController.updateMeeting(editMeeting);
                                        if (isUpdated) {
                                            bookingController.updateBooking(new Booking(editMeetingId, String.valueOf(roomController.getRoom(editRoomId).getRoomID()),String.valueOf(userController.getUser(editUserId).getUserID()), editStartDateTime, editDuration));
                                        }
                                        break;
                                    case 4:
                                        // viewBookings();
                                        System.out.println("Select 1 to view all bookings or 2 to view meetings: ");
                                        int viewChoice = scanner.nextInt();
                                        if (viewChoice == 1) {
                                            List<Booking> bookings = bookingController.getAllBookings();
                                            for (Booking booking : bookings) {
                                                System.out.println(booking.getBookingID() + ". " + booking.getBookedBy() + " " + booking.getDuration() + " " + booking.getBookingDate());
                                            }
                                        } else {
                                            List<Meeting> meetings = meetingController.getAllMeetings();
                                            for (Meeting meetng : meetings) {
                                                System.out.println(meetng.getMeetingID() + ". " + meetng.getRoom().getRoomID() + " " + meetng.getMeetingType() + " " + meetng.getDuration());
                                            }
                                        }
                                        break;
                                        case 5:
                                        // deletemeeting and booking;
                                            System.out.println("Enter Meeting ID to delete: ");
                                        int deleteMeetingId = scanner.nextInt();
                                        boolean meetingDeleted = meetingController.deleteMeeting(deleteMeetingId);
                                        if (meetingDeleted) {
                                            bookingController.deleteBooking(deleteMeetingId);
                                        }
                                    case 6:
                                        ViewConsole.printReturningToMainMenu();
                                        break;
                                    default:
                                        ViewConsole.printInvalidOption();
                                }
                            } while (managerChoice != 6);
                            break;

                        case "Member":
                            int memberChoice;
                            do {
                                ViewConsole.printMemberMenu();
                                memberChoice = scanner.nextInt();

                                switch (memberChoice) {
                                    case 1:
                                        // viewMeetingSchedule();
                                        System.out.println("Meeting Schedule:");
                                        List<Meeting> meetings = meetingController.getMeetingsByUserId(user.getUserID());
                                        for (Meeting meeting : meetings) {
                                            System.out.println(meeting.getMeetingID() + ". " + meeting.getRoom().getRoomID() + " " + meeting.getMeetingType() + " " + meeting.getDuration());
                                        }
                                        break;
                                    case 2:
                                        ViewConsole.printReturningToMainMenu();
                                        break;
                                    default:
                                        ViewConsole.printInvalidOption();
                                }
                            } while (memberChoice != 2);
                            break;

                        default:
                            System.out.println("Login failed. Invalid username or password." +invalidLoginAttempts +" Attempts left");
                            invalidLoginAttempts--;
                    }
                    break;

                case 2:
                    ViewConsole.printExiting();
                    break;

                default:
                    ViewConsole.printInvalidOption();
            }
        } while (mainChoice != 2);

        scanner.close();
    }
}
