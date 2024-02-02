import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;
public class Receptionist implements Builder{

    Account account;
    Scanner scanner = new Scanner(System.in);

    Receptionist(){
        this.account = new Account();
        dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    }

    public Map<String, String> getUser() {
        Map<String, String> userMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\uer\\Desktop\\SQLite\\mydatabase.db")) {
            String query = "SELECT username, password FROM receptionist";
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
                break;
            } else {
                System.out.println("System loading...");
            }

        } while (!Verify(userName, password));
    }
    public LocalDate arrivalDate;
    public LocalDate leavingDate;
    private DateTimeFormatter dateFormat;

    private LocalDateTime reservationTime;

    Manager manager = new Manager();





    public void askDates() {
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Please provide your arrival date (yyyy/MM/dd):");
                String arrivalInput = scanner.nextLine();
                arrivalDate = LocalDate.parse(arrivalInput, dateFormat);

                System.out.println("Please provide your leaving date (yyyy/MM/dd):");
                String leavingInput = scanner.nextLine();
                leavingDate = LocalDate.parse(leavingInput, dateFormat);

                // Check if the dates are in order
                if (arrivalDate.isBefore(leavingDate)) {
                    validInput = true;
                } else {
                    System.out.println("Leaving date should be after the arrival date. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy/MM/dd format.");
            }
        }
    }
    public void finishReservation() {
        reservationTime = LocalDateTime.now();
        System.out.println("Reservation Time: " + reservationTime);
    }

    public void displayDates() {
        System.out.println("Arrival Date: " + arrivalDate.format(dateFormat));
        System.out.println("Leaving Date: " + leavingDate.format(dateFormat));
    }
}