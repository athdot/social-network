import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
            :: 4. View Your Comments                       ::
            :: 5. View All Posts                           ::
            :: 6. Search User                              ::
            :: 7. Logout                                   ::
            :::::::::::::::::::::::::::::::::::::::::::::::::""";
    private final static String viewProfile = "\n" + chooseAction + """
            :::::::::::::::::::::::::::::::::::::::::::::::::
            :: 1. Change Bio                               ::
            :: 2. Change Username                          ::
            :: 3. Change Password                          ::
            :: 4. Back                                     ::
            :::::::::::::::::::::::::::::::::::::::::::::::::""";
    private final static String editPost = "\n" + chooseAction + """
            :::::::::::::::::::::::::::::::::::::::::::::::::
            :: 1. Edit Title                               ::
            :: 2. Edit Content                             ::
            :: 3. Delete Post                              ::
            :: 4. Back                                     ::
            :::::::::::::::::::::::::::::::::::::::::::::::::""";

    private final static String actionCorrection = "Invalid Action";
    private final static String invalidAccount = "Username/Password is Wrong";
    private final static String fileErrorMessage = "Something Went Wrong";
    private final static String usernameTakenMessage = "Username is Taken";
    private final static String accountCreated = "Account Created";

    private final static String usernamePrompt = "Your Username: ";
    private final static String passwordPrompt = "Your Password: ";

    private final static String usernameSpaceCorrection = "Usernames Shouldn't Have Spaces";
    private final static String userPassLengthCorrection = "Username/Password is Too Short";

    //strings pertaining to profile actions
    private final static String currentPasswordPrompt = "Enter your current password: ";
    private final static String invalidPassword = "Password is incorrect";
    private final static String newPasswordPrompt = "Enter your new password: ";
    private final static String newUsernamePrompt = "Enter your new username";
    private final static String newBioPrompt = "Enter your new bio: ";

    //strings pertaining to post creation
    private final static String postTitlePrompt = "Enter a Post Title: ";
    private final static String postContentPrompt = "Enter the Post's Message: ";

    //strings pertaining to post editing/deletion
    private final static String postChoicePrompt = "Enter the number of the post you would like to edit"
        + "or enter 0 to exit: ";

    private final static String logout = "Logging out...";
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
                    //creates the file in case it doesn't exist
                    //even though there wouldn't be any existing accounts, we dodge FileNotFoundException
                    File f = new File(filename);
                    FileOutputStream fos = new FileOutputStream(f, true);
                    fos.close();

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
                            //assign Account to current instance of Application
                            user = new Account(username, cryptoHashFunction(password));
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
                    //creates the file in case it doesn't exist
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
                } else if (password.length() == 0 || username.length() == 0) {
                    System.out.println(userPassLengthCorrection);
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

    public void view_changeProfile() {

        boolean backToMain = false;
        Scanner scanner = new Scanner(System.in);
        String filename = "accountInfo.csv"; //we want to use this file when interacting with accounts

        //while still in the profile menu
        while (!backToMain) {
            //display user's profile and show options
            System.out.println(user.toString());
            System.out.println(viewProfile);

            int choice = scanner.nextInt();
            scanner.nextLine();

            //if it's an invalid choice, say so
            if (choice > 4 || choice < 1) {
                System.out.println(actionCorrection);
            } else if (choice == 4) {
                backToMain = true;
            } else if (choice == 3) { //change password
                //prompt for current password
                System.out.println(currentPasswordPrompt);
                String password = scanner.nextLine();

                if (cryptoHashFunction(password).equals(user.getPassword())) {
                    //prompt for new password
                    System.out.println(newPasswordPrompt);
                    password = scanner.nextLine();
                    if (password.length() == 0) {
                        System.out.println(userPassLengthCorrection);
                    }
                    user.setPassword(password);
                } else {
                    System.out.println(invalidPassword);
                }
            } else if (choice == 2) { //change username
                System.out.println(newUsernamePrompt);
                //check if username is taken
                boolean usernameIsTaken = false;
                String username = scanner.nextLine();

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
                            break;
                        }
                    }
                    bfr.close();

                } catch (IOException ioException) {
                    //Program must not crash, program should never get to this block
                    System.out.println(fileErrorMessage);
                }

                //catch username problems
                if (username.contains(" ")) {
                    System.out.println(usernameSpaceCorrection);
                } else if (usernameIsTaken) {
                    System.out.println(usernameTakenMessage);
                }

                //update username
                user.setUsername(username);
                //TODO: update username instances in files - will this be a method in account class?

            } else { //change bio
                System.out.println(newBioPrompt);
                user.setBio(scanner.nextLine());
            }
        }
    }

    public void editPost(Post post) {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(editPost);

            choice = scanner.nextInt();

            if (choice > 4 || choice < 1) {
                System.out.println(actionCorrection);
            }
        } while  (choice > 4 || choice < 1);

        //TODO: do stuff based on user's choice
    }

    public void mainScreen() {
        Scanner scanner = new Scanner(System.in);
        boolean exiting = false;

        while (!exiting) {
            //print options
            System.out.println(mainMenu);

            //get choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            //act on choice
            if (choice == 1) { //view and change profile
                view_changeProfile();
            } else if (choice == 2) { //create post
                System.out.println(postTitlePrompt); //get title
                String title = scanner.nextLine();
                System.out.println(postContentPrompt); //get message
                String content = scanner.nextLine();
                user.addPost(new Post(title, user, content)); //add it to the list of posts
            } else if (choice == 3) { //view and edit your posts
                //display posts from this user with numbers beside them
                ArrayList<Post> posts = user.getPosts();
                for (int x = 0; x < posts.size(); x++) {
                    System.out.println(Integer.toString(x + 1) + posts.get(x).toString());
                    System.out.println(" ");
                }

                //display option to edit a post and get input
                int postChoice;
                do {
                    System.out.println(postChoicePrompt);
                    postChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (postChoice > posts.size() || postChoice < 0) {
                        System.out.println(actionCorrection);
                    }
                } while (postChoice > posts.size() || postChoice < 0);

                if (postChoice != 0) {
                    editPost(posts.get(postChoice - 1));
                }
            } else if (choice == 4) { //view and edit all your comments
                //TODO
            } else if (choice == 5) { //view other people's posts
                //TODO
            } else if (choice == 6) { //search for a specific user
                //TODO
            } else if (choice == 7) { //logout
                exiting = true;
                System.out.println(logout);
                System.out.println(exit);
            } else if (choice == 0) { //exit
                exiting = true;
                System.out.println(exit);
            } else { //anything else is not a valid input
                System.out.println(actionCorrection);
            }
        }
    }

    public void start() { //control the flow of the user experience
        login();
        mainScreen();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.start(); //run the program
    }
}
