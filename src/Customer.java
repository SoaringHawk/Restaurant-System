import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer implements Builder {

    Account account;
    Scanner scanner = new Scanner(System.in);
    int opt;
    String selectedRoom;
    Manager manager = new Manager();
    Chef chef = new Chef();

    Receptionist recep = new Receptionist();

    Customer() {
        this.account = new Account();

    }

    public Map<String, String> getUser() {
        Map<String, String> userMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\uer\\Desktop\\SQLite\\mydatabase.db")) {
            String query = "SELECT username, password FROM customer";
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


    public void Login() {
        String userName;
        String password;
        do {
            System.out.print("enter username: ");
            userName = scanner.nextLine();
            if (!isString(userName)) {
                System.out.println("Invalid username format. Please enter a string.");
                break;
            }
            System.out.print("enter password: ");
            password = scanner.nextLine();
            if (!isFourDigitNumber(password)) {
                System.out.println("Invalid password format. Please enter a 4-digit number.");
                break;
            }
            if (!Verify(userName, password)) {
                System.out.println("Invalid username or password. Please try again.");
            } else {
                System.out.println("System loading...");
                viewMenuCustomer();
            }

        } while (!Verify(userName, password));
    }


    public Customer(Manager manager, Chef chef, Receptionist recep) {
        this.manager = manager;
        this.chef = chef;
        this.recep = recep;
    }

    public void viewMenuCustomer() {
        do {
            System.out.println("Menu: ");
            System.out.println("1. Reserve a room");
            System.out.println("2. Order meal");
            System.out.println("3. Exit");
            System.out.println("Please enter your option: ");
            opt = scanner.nextInt();
            switch (opt) {
                case 1 -> {
                    manager.promptGuestInformation();
                    manager.checkRoomAvailable();
                    System.out.print("Please select a room: ");
                    selectedRoom = scanner.next();
                    manager.updateRevenue(selectedRoom);
                    manager.chooseRoom(selectedRoom);
                    recep.askDates();
                    Duration duration = Duration.between(recep.arrivalDate.atStartOfDay(), recep.leavingDate.atStartOfDay());
                    int dayDifference = (int) duration.toDays();

                    // Assuming manager is an instance of the Manager class
                    double totalPrice = manager.calculateStayPrice(selectedRoom, dayDifference);
                    if (totalPrice > 0) {
                        System.out.println("Total Price for your stay: $" + totalPrice + "for each night");
                    }
                    makePayment();
                    printReceipt();
                }
                case 2 -> {
                    String orderedFood;
                    chef.displayFoodMenu();
                    System.out.print("enter food you wish to have: ");
                    orderedFood = scanner.next();
                    chef.isFoodAvailable(orderedFood);
                }
                case 3 -> {
                    System.out.println("Existing...");
                }
                default -> {
                    System.out.println("Invalid option. Please type again.");
                    return;
                }
            }
        } while (opt != 3);

    }
    public void makePayment () {

        int opt;
        do {
            System.out.println("1. Debit card");
            System.out.println("2. Credit card");
            System.out.println("3. Cash");
            System.out.print("Select payment: ");
            opt = scanner.nextInt();
            if (opt != 1 && opt != 2 & opt != 3) {
                System.out.println("Invalid option. Please type again.");
            } else {
                System.out.println("Payment successfully accepted.");
            }
        } while (opt != 1 && opt != 2 && opt != 3);
    }


        private void printReceipt () {
            System.out.println("*** ABC Hotel ***");
            System.out.println();
            recep.finishReservation();
            recep.displayDates();
            manager.chooseRoom(selectedRoom);
            System.out.println();
            System.out.println("Thank you for choosing us. We hope you will have a good experience");
        }


    }

