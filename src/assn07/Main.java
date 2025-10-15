package assn07;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> passwordManager = new PasswordManager<>();

        // your code below

        // infite loop to go back to "Enter master password"
        System.out.println("Enter Master Password");
        while (true) {
            String enteredPassword = scanner.nextLine().trim();
            if (passwordManager.checkMasterPassword(enteredPassword)) {
                break;
            }
            System.out.println("Enter Master Password"); // Repeat until correct
        }

        // loop to read and execute commands until "Exit" is entered
        while (true) {
            String command = scanner.nextLine().trim();

            switch (command) {
                case "New password": {
                    String website = scanner.nextLine().trim();
                    String password = scanner.nextLine().trim();
                    passwordManager.put(website, password);
                    System.out.println("New password added");
                    break;
                }

                case "Get password": {
                    String website = scanner.nextLine().trim();
                    String retrievedPassword = passwordManager.get(website);
                    if (retrievedPassword == null) {
                        System.out.println("Account does not exist");
                    } else {
                        System.out.println(retrievedPassword);
                    }
                    break;
                }

                case "Delete account": {
                    String website = scanner.nextLine().trim();
                    String removedPassword = passwordManager.remove(website);
                    if (removedPassword == null) {
                        System.out.println("Account does not exist");
                    } else {
                        System.out.println("Account deleted");
                    }
                    break;
                }

                case "Check duplicate password": {
                    String password = scanner.nextLine().trim();
                    List<String> duplicates = passwordManager.checkDuplicates(password);
                    if (duplicates.isEmpty()) {
                        System.out.println("No account uses that password");
                    } else {
                        System.out.println("Websites using that password:");
                        for (String website : duplicates) {
                            System.out.println(website);
                        }
                    }
                    break;
                }

                case "Get accounts": {
                    System.out.println("Your accounts:");
                    for (String website : passwordManager.keySet()) {
                        System.out.println(website);
                    }
                    break;
                }

                case "Generate random password": {
                    System.out.println("Enter password length:");
                    int length = Integer.parseInt(scanner.nextLine().trim());
                    if (length < 4) {
                        length = 4; // Ensure minimum length
                    }
                    String randomPassword = passwordManager.generatesafeRandomPassword(length);
                    System.out.println(randomPassword);
                    break;
                }

                case "Exit":
                    return;

                default:
                    System.out.println("Command not found");
            }
        }
    }
}


