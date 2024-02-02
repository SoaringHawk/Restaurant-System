import java.util.Objects;
import java.util.Scanner;


public class Login {
    String userName;
    String password;
    Scanner scanner;

    Manager manager;
    Receptionist recep;

    Chef chef;

    Customer customer;

    User user;
    Builder myBuilder;

    public Login(Builder builder) {

        this.myBuilder = builder;

        //scanner = new Scanner(System.in);
        //manager = new Manager();
        //recep = new Receptionist();
        //chef = new Chef();
//        customer = new Customer();
        //user = new User();
    }


    //private static boolean isString(String input) {
    // return input.matches("[a-zA-Z]+");
    //}

    // Additional method to verify if the input is a 4-digit number
    //private static boolean isFourDigitNumber(String input) {
    //return input.matches("\\d{4}");
    //}

    // Dummy method, replace it with your actual verification logic


    public void SignIn() {
        myBuilder.Login();
    }


//    public boolean verifyCustomer(String userName, String password) {
//        String storedUserName = this.user.getCustomer_username();
//        String storedPassword = this.user.getCustomer_password();
//        return userName.equals(storedUserName) && password.equals(storedPassword);
//    }

//    public void loginManager() {
//        do {
//            System.out.print("enter username: ");
//            userName = scanner.nextLine();
//            System.out.print("enter password: ");
//            password = scanner.nextLine();
//            if (!verifyManager(userName, password)) {
//                System.out.println("Invalid username or password. Please type again.");
//            } else {
//                System.out.println("System loading...");
//                manager.viewMenuManager();
//            }
//        } while (!verifyManager(userName, password));
//    }

//    public boolean verifyManager(String userName, String password) {
//        String storedUserName = this.user.getManager_username();
//        String storedPassword = this.user.getManager_password();
//        return userName.equals(storedUserName) && password.equals(storedPassword);
//    }
//
//    public void loginChef() {
//        do {
//            System.out.print("enter username: ");
//            userName = scanner.nextLine();
//            System.out.print("enter password: ");
//            password = scanner.nextLine();
//            if (!verifyChef(userName, password)) {
//                System.out.println("Invalid username or password. Please type again.");
//            } else {
//                System.out.println("System loading...");
//                chef.viewMenuChef();
//            }
//
//        } while (!verifyChef(userName, password));
//    }
//
//    public boolean verifyChef(String userName, String password) {
//        String storedUserName = this.user.getChef_username();
//        String storedPassword = this.user.getChef_password();
//        return userName.equals(storedUserName) && password.equals(storedPassword);
//    }
//
//    public void loginReceptionist() {
//        do {
//            System.out.print("enter username: ");
//            userName = scanner.nextLine();
//            System.out.print("enter password: ");
//            password = scanner.nextLine();
//            if (!verifyReceptionist(userName, password)) {
//                System.out.println("Invalid username or password. Please type again.");
//                break;
//            } else {
//                System.out.println("System loading...");
//            }
//
//        } while (!verifyReceptionist(userName, password));
//    }
//
//    public boolean verifyReceptionist(String userName, String password) {
//        String storedUserName = this.user.getReceptionist_username();
//        String storedPassword = this.user.getReceptionist_password();
//        return userName.equals(storedUserName) && password.equals(storedPassword);
//    }
}
