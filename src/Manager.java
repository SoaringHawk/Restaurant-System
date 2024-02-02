import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDateTime;
public class Manager implements Builder{
    Account account;
    Scanner scanner = new Scanner(System.in);

    Manager(){
        this.account = new Account();
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 10);
        roomAvailability.put("Double", 10);
        roomAvailability.put("Suite", 10);

        roomPrice = new HashMap<>();
        roomPrice.put("Single", 500.0);
        roomPrice.put("Double", 750.0);
        roomPrice.put("Suite", 1000.0);

        totalRevenue = 0;

        guestList = new String[100][4];
        numberOfGuests = 0;
    }

    public Map<String, String> getUser() {
        Map<String, String> userMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\uer\\Desktop\\SQLite\\mydatabase.db")) {
            String query = "SELECT username, password FROM manager";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");

                        // Put the username and password into the map
                        userMap.put(username, password);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

        return userMap;
    }

    private static boolean isString(String input) {
        return input.matches("[a-zA-Z]+");
    }

    private static boolean isFourDigitNumber(String input) {
        return input.matches("\\d{4}");
    }

    public boolean Verify(String userName, String password) {

        Map<String, String> userMap = getUser();


        if (userMap.containsKey(userName)) {

            String storedPassword = userMap.get(userName);
            return password.equals(storedPassword);
        }


        return false;
    }

    public void Login(){
        String userName;
        String password;
        do {
            System.out.print("enter username: ");
            userName = scanner.nextLine();
            System.out.print("enter password: ");
            password = scanner.nextLine();
            if (!Verify(userName, password)) {
                System.out.println("Invalid username or password. Please type again.");
            } else {
                System.out.println("System loading...");
                viewMenuManager();
            }
        } while (!Verify(userName, password));
    }
    private Map<String, Integer> roomAvailability;
    private Map<String, Double> roomPrice;
    private double totalRevenue;

    private String[][] guestList;
    private int numberOfGuests;





    public double calculateStayPrice(String roomType, int numberOfDays) {
        if (roomAvailability.containsKey(roomType) && roomAvailability.get(roomType) > 0) {
            double pricePerNight = roomPrice.get(roomType);
            double totalPrice = pricePerNight * numberOfDays;

            // Update total revenue
            totalRevenue += totalPrice;

            return totalPrice;
        } else {
            System.out.println("Sorry, " + roomType + " rooms are currently unavailable.");
            return 0;
        }
    }

    public void viewMenuManager() {
        int opt;
        do {
            System.out.println("1. Check Room Available");
            System.out.println("2. Update Room Availability");
            System.out.println("3. Check Revenue");
            System.out.println("4. Check Guest List");
            System.out.println("5. Exit");
            opt = scanner.nextInt();
            switch (opt) {
                case 1 -> checkRoomAvailable();
                case 2 -> updateRoomAvailability();
                case 3 -> System.out.println("Updated total revenue: " + totalRevenue);
                case 4 -> displayGuestInformation();
                case 5 -> {
                    System.out.println("Existing...");
                }
                default -> System.out.println("Invalid option. Please enter again.");
            }
        } while (opt != 5);
    }

    public void checkRoomAvailable() {
        System.out.println("Room availability and prices:");
        for (Map.Entry<String, Integer> entry : roomAvailability.entrySet()) {
            String roomType = entry.getKey();
            int availableRooms = entry.getValue();
            double price = roomPrice.get(roomType);
            System.out.println(roomType + " - Available Rooms: " + availableRooms + " - Price: $" + price + "/night");
        }
    }

    private void updateRoomAvailability() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nManager: Do you want to update room availability? (y/n)");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            for (String roomType : roomAvailability.keySet()) {
                System.out.println("Enter new availability for " + roomType + ":");
                int newAvailability = scanner.nextInt();
                scanner.nextLine();

                roomAvailability.put(roomType, newAvailability);
            }
            System.out.println("Room availability updated successfully.");
        } else {
            System.out.println("No changes made.");
        }
    }

    public void updateRevenue(String roomType) {
        if (roomPrice.containsKey(roomType)) {
            double price = roomPrice.get(roomType);
            totalRevenue += price;
        }
    }

    public void chooseRoom(String roomType) {
            if (Objects.equals(roomType, "Suite")) {
                System.out.println("Suite room - $1000");
                if (roomAvailability.containsKey(roomType) && roomAvailability.get(roomType) > 0) {
                    double price = roomPrice.get(roomType);
                    System.out.println(roomType + " room - $" + price);

                    // Decrease the number of available rooms
                    int currentAvailability = roomAvailability.get(roomType);
                    roomAvailability.put(roomType, currentAvailability - 1);

                    // Update total revenue
                    totalRevenue += price;
                } else {
                    System.out.println("Sorry, " + roomType + " rooms are currently unavailable.");
                }

            } else if (Objects.equals(roomType, "Single")) {
                System.out.println("Single room - $500");
                if (roomAvailability.containsKey(roomType) && roomAvailability.get(roomType) > 0) {
                    double price = roomPrice.get(roomType);
                    System.out.println(roomType + " room - $" + price);

                    // Decrease the number of available rooms
                    int currentAvailability = roomAvailability.get(roomType);
                    roomAvailability.put(roomType, currentAvailability - 1);

                    // Update total revenue
                    totalRevenue += price;
                } else {
                    System.out.println("Sorry, " + roomType + " rooms are currently unavailable.");
                }

            } else if (Objects.equals(roomType, "Double")) {
                System.out.println("Double room - $750");
                if (roomAvailability.containsKey(roomType) && roomAvailability.get(roomType) > 0) {
                    double price = roomPrice.get(roomType);
                    System.out.println(roomType + " room - $" + price);

                    // Decrease the number of available rooms
                    int currentAvailability = roomAvailability.get(roomType);
                    roomAvailability.put(roomType, currentAvailability - 1);

                    // Update total revenue
                    totalRevenue += price;
                } else {
                    System.out.println("Sorry, " + roomType + " rooms are currently unavailable.");
                }

            } else {
                System.out.println("Invalid option. Please type again.");
            }
    }

    public void promptGuestInformation() {
        System.out.println("Enter guest information:");

        // Prompting for guest information
        for (int i = numberOfGuests; i < guestList.length; i++) {
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Date of Birth (yyyy/MM/dd): ");
            String dateOfBirth = scanner.nextLine();

            System.out.print("Identification: ");
            String identification = scanner.nextLine();

            // Store guest information in the array
            guestList[i] = new String[]{firstName, lastName, dateOfBirth, identification};
            numberOfGuests++;
            break;
        }
    }
    public void displayGuestInformation() {
        System.out.println("List of Guests' Information:");
        System.out.println(String.format("%-15s%-15s%-15s%-15s", "First Name", "Last Name", "Date of Birth", "Identification"));
        for (int i = 0; i < numberOfGuests; i++) {
            System.out.println(String.format("%-15s%-15s%-15s%-15s",
                    guestList[i][0], guestList[i][1], guestList[i][2], guestList[i][3]));
        }
    }
}
