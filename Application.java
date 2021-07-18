import java.io.*;
import java.util.Scanner;
import java.util.stream.StreamSupport;

/**
 * Application - Project 4, Social Network App
 * Handles overall functionality of application, brings together Account, Comment, and Post
 *
 * @author Group 8, N. Yao, ...
 * @version July 17, 2021
 */

public class Application {

    private Account user; //remember the user who is signed in to this instance of the app

    private final static String welcome = """
            :::::::::::::::::::::::::::::::::::::::::::::::::
            :: Welcome to our Social Network Application!  ::
            :: TO QUIT: Enter "0" as an action             ::
            :::::::::::::::::::::::::::::::::::::::::::::::::""";
    private final static String chooseAction = "Choose an Action:\n";
    private final static String loginPage = "\n" + chooseAction + """
            :::::::::::::::::::::::::::::::::::::::::::::::::
            :: 1. Sign In                                  ::
            :: 2. Create New Account                       ::
            :::::::::::::::::::::::::::::::::::::::::::::::::""";
    private final static String mainMenu = "\n" + chooseAction + """
            :::::::::::::::::::::::::::::::::::::::::::::::::
            :: 1. View Profile                             ::
            :: 2. Create New Post                          ::
            :: 3. View Your Posts                          ::
            :: 4. View All Posts                           ::
            :: 5. Search User                              ::
            :: 6. Logout                                   ::
            :::::::::::::::::::::::::::::::::::::::::::::::::""";
    private final static String viewProfile = "\n" + chooseAction + """
            :::::::::::::::::::::::::::::::::::::::::::::::::
            :: 1. Change Bio                               ::
            :: 2. Change Username                          ::
            :: 3. Change Password                          ::
            :: 4. Back                                     ::
            :::::::::::::::::::::::::::::::::::::::::::::::::""";

    private final static String actionCorrection = "Invalid Action";
    private final static String invalidAccount = "Username/Password is Invalid";
    private final static String fileErrorMessage = "Something Went Wrong";
    private final static String usernameTakenMessage = "Username is Taken";
    private final static String accountCreated = "Account Created";

    private final static String usernamePrompt = "Your Username: ";
    private final static String passwordPrompt = "Your Password: ";

    private final static String usernameSpaceCorrection = "Usernames Shouldn't Have Spaces";
    private final static String passwordLengthCorrection = "Password is Too Short";

    private final static String exit = "Exiting...";
    //note: change all input to be String so program doesn't break if non-int is entered? (?do later)
    //nvm, instructions say application should not crash under any circumstances

    //This function takes a password and encrypts it, just for funsies
    public String cryptoHashFunction(String password) {
        //Will always be 32 digits, add digits
        if (password.length() > 32) {
            password = password.substring(0, 32);
        } else {
            for (int i = password.length(); i <= 32; i++) {
                password = "0" + password;
            }
        }

        int divisor =  0;
        for (int i = 0; i < password.length(); i++) {
            divisor += (int) password.charAt(i);
        }

        String output = "";
        // char 32 to 126 range
        for (int i = 0; i < password.length(); i ++) {
            output += (char) ((((int) password.charAt(i)) * 1000 * i / divisor) % 93 + 33);
        }

        return output;
    }

    public void login() { //control the login section of the program

        boolean validCredentials = false;
        String filename = "accountInfo.csv"; //we want to use this file while signing in/creating new account
        System.out.println(welcome);
        Scanner scanner = new Scanner(System.in);

        do {
            String actionStr; //an 'a' will stand for action to be done, stores initial input for action choice
            int actionInt = 0; //user must enter an integer choice, store that value from actionStr in here

            do {
                System.out.println(loginPage);
                actionStr = scanner.nextLine();
                try {
                    actionInt = Integer.parseInt(actionStr);
                } catch (NumberFormatException numberFormatException) {
                    System.out.println(actionCorrection);
                    continue; //skip the current iteration of the loop
                }
                if (actionInt == 0) { //user enters 0 to exit the program (see app() method)
                    System.out.println(exit);
                    return;
                } else if (actionInt < 1 || actionInt > 2) { //there were 2 choices (other than exiting) for the login page
                    System.out.println(actionCorrection);
                }
            } while (actionInt < 1 || actionInt > 2);

            if (actionInt == 1) { //user chooses to sign in

                System.out.print(usernamePrompt);
                String username = scanner.nextLine();
                System.out.print(passwordPrompt);
                String password = scanner.nextLine();

                try {
                    File f = new File(filename);
                    FileReader fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);

                    while (true) {
                        String line = bfr.readLine();
                        if (line == null) {
                            //if there are no matches after searching all accounts from the file
                            System.out.println(invalidAccount);
                            break;
                        }
                        //create array of length 2, contains username, then encrypted password
                        String[] userPass = line.split(" ");
                        if (userPass[0].equalsIgnoreCase(username)
                                && userPass[1].equals(cryptoHashFunction(password))) {
                            validCredentials = true;
                            user = new Account(username); //assign Account to current instance of Application
                            break;
                        }
                    }
                    bfr.close();

                } catch (IOException ioException) { //program must not crash under any circumstances
                    System.out.println(fileErrorMessage);
                }

            } else { //if (actionInt == 2), where user chooses to create new account

                boolean usernameIsTaken = false;
                System.out.print(usernamePrompt);
                String username = scanner.nextLine();
                System.out.print(passwordPrompt);
                String password = scanner.nextLine();

                try { //make sure that the username has not already been taken (case insensitive)
                    //create the file in case it doesn't exist
                    //even though there wouldn't be any existing accounts, we dodge FileNotFoundException
                    File f = new File(filename);
                    FileOutputStream fos = new FileOutputStream(f, true);
                    fos.close();

                    FileReader fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);

                    while (true) {
                        String line = bfr.readLine();
                        if (line == null) {
                            //found no accounts with the same username
                            usernameIsTaken = false;
                            break;
                        }
                        //create array of length 2, contains username, then encrypted password
                        String[] userPass = line.split(" ");
                        if (userPass[0].equalsIgnoreCase(username)) {
                            usernameIsTaken = true;
                            System.out.println(usernameTakenMessage);
                            break;
                        }
                    }
                    bfr.close();

                } catch (IOException ioException) {
                    //Program must not crash, program should never get to this block
                    System.out.println(fileErrorMessage);
                }

                if (username.contains(" ")) {
                    System.out.println(usernameSpaceCorrection);
                } else if (usernameIsTaken) {
                    System.out.println(usernameTakenMessage);
                } else if (password.length() == 0) {
                    System.out.println(passwordLengthCorrection);
                } else {
                    try { //only if username and password are valid, then write them to the file
                        File f = new File(filename);
                        FileOutputStream fos = new FileOutputStream(f, true);
                        PrintWriter pw = new PrintWriter(fos);

                        pw.println(username + " " + cryptoHashFunction(password)); //write new account to file
                        System.out.println(accountCreated);
                        pw.close();

                    } catch (FileNotFoundException fileNotFoundException) {
                        //this should never be reached, java would create file in write mode
                        System.out.println(fileErrorMessage);
                    }
                }
            }
        } while (!validCredentials); //continue to prompt login screen until user provides valid credentials
    }

    public void start() { //control the flow of the user experience
        login();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.start(); //run the program
    }
}
