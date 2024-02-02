import java.sql.*;
import java.util.*;

public class Chef implements Builder{

    Account account;
    Scanner scanner = new Scanner(System.in);

    Chef(){
        this.account = new Account();
        foodAvailable = new HashSet<>();

    }

    public Map<String, String> getUser() {
        Map<String, String> userMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\uer\\Desktop\\SQLite\\mydatabase.db")) {
            String query = "SELECT username, password FROM chef";
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
                viewMenuChef();
            }

        } while (!Verify(userName, password));
    }
    private Set<String> foodAvailable;



    public void viewMenuChef() {
        Scanner scanner = new Scanner(System.in);
        int opt;
        do {
            System.out.println("1. Display food menu");
            System.out.println("2. Update food ready to be served");
            System.out.println("3. Exit");
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    displayFoodMenu();
                    break;
                case 2:
                    updateFoodAvailable();
                    break;
                case 3:
                    System.out.println("Existing...");
                    break;
                default:
                    System.out.println("Invalid option. Please type again");
            }
        }while(opt != 3);
    }
    public void displayFoodMenu(){
        System.out.println("Available Food Menu: ");
        for(String food : foodAvailable){
            System.out.println(food);
        }
    }
    String food;
    private void updateFoodAvailable(){
        String opt;

        do {
            System.out.print("input food ready to be served: ");
            food = scanner.next();

            if (!food.equalsIgnoreCase("done")) {
                foodAvailable.add(food);
                System.out.println(food + " added to the available food items.");
            }
            System.out.print("Do you wish to add more dishes? (y/n)");
            opt = scanner.next();
        } while (!food.equalsIgnoreCase("done")  && Objects.equals(opt, "y"));
        System.out.println("Menu updated successfully");
    }

    public void isFoodAvailable(String orderedFood){
        if (foodAvailable.contains(food)){
            System.out.println("food is ordered");
        }else {
            System.out.println("Sorry, we do not offer today.");
        }
    }
}
