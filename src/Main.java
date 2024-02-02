import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Customer user = new Customer();
        Chef chef = new Chef();
        Manager manager = new Manager();
        Receptionist receptionist = new Receptionist();

        do {
            System.out.print("Select (GUEST/MANAGER/CHEF/RECEPTIONIST): ");
            String ans = scanner.nextLine();

            if (Objects.equals(ans, "GUEST")) {
                System.out.print("Choose 2 to login or 1 to register: ");
                ans = scanner.nextLine();

                if (Integer.parseInt(ans) == 1) {
                    System.out.print("Type your username and password in this format: username-password ");

                    String credentials = scanner.nextLine();
                    String[] credential = credentials.split("-");

                    user.account.setUsername(credential[0]);
                    user.account.setPassword(credential[1]);
                } else {
                    Login login = new Login(user);

                    login.SignIn();
                    // Add logic for GUEST actions after successful login
                }

            } else if (Objects.equals(ans, "MANAGER")) {
                System.out.print("choose 2 to login or 1 to register");
                ans = scanner.nextLine();
                if(Integer.parseInt(ans) == 1){
                    System.out.print("Type your username and password in this format: username-password ");

                    ans = scanner.nextLine();
                    String[] credential = ans.split("-");

                    manager.account.setUsername(credential[0]);
                    manager.account.setPassword(credential[1]);
                }else{
                    Login login = new Login(manager);
                    login.SignIn();
                }
            } else if (Objects.equals(ans, "CHEF")) {
                System.out.print("choose 2 to login or 1 to register");
                ans = scanner.nextLine();
                if(Integer.parseInt(ans) == 1){
                    System.out.print("Type your username and password in this format: username-password ");

                    ans = scanner.nextLine();
                    String[] credential = ans.split("-");

                    chef.account.setUsername(credential[0]);
                    chef.account.setPassword(credential[1]);
                }else{
                    Login login = new Login(chef);
                    login.SignIn();
                }
            } else if (Objects.equals(ans, "RECEPTIONIST")) {
                System.out.print("choose 2 to login or 1 to register");
                ans = scanner.nextLine();
                if(Integer.parseInt(ans) == 1){
                    System.out.print("Type your username and password in this format: username-password");

                    ans = scanner.nextLine();
                    String[] credential = ans.split("-");

                    receptionist.account.setUsername(credential[0]);
                    receptionist.account.setPassword(credential[1]);
                }else{
                    Login login = new Login(receptionist);
                    login.SignIn();
                }
            } else {
                System.out.println("Invalid option. Please type again.");
            }
        } while (true); // Adjust the condition based on your exit criteria
    }
}
