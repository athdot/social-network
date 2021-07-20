import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
    private Comment edit;
    private Post post;
    private boolean quit = false; //becomes true if user enters 0 for action. Program terminates

    private final static String accountFilename = "accountInfo.csv"; //file that remembers accounts

    private final static String welcome = """
            +--------------------------------------------------+
            | Welcome to Group 8's Social Network Application! |
            | NOTE: Enter 0 as an action to QUIT.              |
            +--------------------------------------------------+""";
    private final static String chooseAction = "Choose an Action:\n";
    private final static String loginPage = "\n" + chooseAction + """
            +--------------------------------------------------+
            | LOGIN PAGE                                       |
            | 1. Sign In                                       |
            | 2. Create New Account                            |
            +--------------------------------------------------+""";
    private final static String mainMenu = "\n" + chooseAction + """
            +--------------------------------------------------+
            | MAIN MENU                                        |
            | 1. Your Profile                                  |
            | 2. Create New Post                               |
            | 3. View Your Posts                               |
            | 4. View Your Comments                            |
            | 5. View All Posts                                |
            | 6. Search User                                   |
            | 7. Logout                                        |
            +--------------------------------------------------+""";
    private final static String yourProfile = "\n" + chooseAction + """
            +--------------------------------------------------+
            | YOUR PROFILE                                     |
            | 1. Change Bio                                    |
            | 2. Change Username                               |
            | 3. Change Password                               |
            | 4. Back                                          |
            +--------------------------------------------------+""";
    private final static String editPost = "\n" + chooseAction + """
            +--------------------------------------------------+
            | EDIT POST                                        |
            | 1. Edit Title                                    |
            | 2. Edit Content                                  |
            | 3. Delete Post                                   |
            | 4. Back                                          |
            +--------------------------------------------------+""";
    private final static String viewUserOptions = "\n" + chooseAction + """
            +--------------------------------------------------+
            | VIEW USER                                        |
            | 1. View Profile                                  |
            | 2. View Posts                                    |
            | 3. Back                                          |
            +--------------------------------------------------+""";

    //string constants for login section
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
        + " or enter -1 to return to the main screen: ";
    private final static String newPostTitlePrompt = "Enter a new Post Title: ";
    private final static String newPostContentPrompt = "Enter the Post's new Message: ";
    private final static String deletionConfirmation = "Are you sure you would like to delete this post? (Y/N): ";

    //strings pertaining to search users
    private final static String searchRequest = "Enter the username of the user you want to view: ";
    private final static String userNotFound = "There is no user with that username.";
    private final static String userFound = "Search successful. User found.";

    private final static String logout = "Logging Out...";
    private final static String exit = "Exiting...";
    //note: change all input to be String so program doesn't break if non-int is entered? (?do later)
    //nvm, instructions say application should not crash under any circumstances

  
    public void login() { //control the login section of the program

        boolean validCredentials = false;
        System.out.println(welcome);
        Scanner scanner = new Scanner(System.in);

        do {
            String actionStr; //an 'a' will stand for action to be done, stores initial input for action action
            int actionInt = 0; //user must enter an integer action, store that value from actionStr in here
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
                    quit = true; //quit is a variable that notify the start() method to end the program
                    return;
                } else if (actionInt < 1 || actionInt > 2) {
                    //there were 2 choices (other than 0 for loggedOut) for the login page
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
                    File f = new File(accountFilename);
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
                                && userPass[1].equals(CryptoHash.getHash(password))) {
                            validCredentials = true;
                            //assign Account to current instance of Application
                            user = new Account(username, CryptoHash.getHash(password));
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
                    File f = new File(accountFilename);
                    FileOutputStream fos = new FileOutputStream(f, true);
                    fos.close();

                    FileReader fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);

                    while (true) {
                        String line = bfr.readLine();
                        if (line == null) {
                            //found no accounts with the same username
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
                        File f = new File(accountFilename);
                        FileOutputStream fos = new FileOutputStream(f, true);
                        PrintWriter pw = new PrintWriter(fos);

                        pw.println(username + " " + CryptoHash.getHash(password)); //write new account to file
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

    // Deals with username and password
    public void yourProfile() {

        boolean goBack = false;
        Scanner scanner = new Scanner(System.in);

        //while still in the profile menu
        while (!goBack) {
            //display user's profile and show options
            System.out.println(user.toString());
            System.out.println(yourProfile);

            int action = scanner.nextInt();
            scanner.nextLine();

            //if it's an invalid action, say so
            if (action < 1 || action > 6) {
                System.out.println(actionCorrection);
            } else if (action == 4) {
                goBack = true;
            } else if (action == 3) { //change password
                //prompt for current password
                System.out.println(currentPasswordPrompt);
                String password = scanner.nextLine();

                if (CryptoHash.getHash(password).equals(user.getPassword())) {
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

            } else if (action == 2) { //change username
                System.out.println(newUsernamePrompt);
                //check if username is taken
                boolean usernameIsTaken = false;
                String username = scanner.nextLine();

                try { //make sure that the username has not already been taken (case insensitive)
                    //create the file in case it doesn't exist
                    //even though there wouldn't be any existing accounts, we dodge FileNotFoundException
                    File f = new File(accountFilename);
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
            // User can choose to computer generate a username
            } else if (action == 5) {
                user.computerGenerateName(user.getUsername());
            // User can choose to computer generate a password
            } else if (action == 6) {
                user.computerGenerateName(user.getPassword());
            } else { //change bio
                System.out.println(newBioPrompt);
                user.setBio(scanner.nextLine());
            }
        }
    }

    public void editPost(Post post) {
        Scanner scanner = new Scanner(System.in);
        int action = 0; //default to zero to prevent
        do {
            System.out.println(editPost);
            try {
                action = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(actionCorrection);
                continue;
            }
            if (action == 0) { //anytime the user enters a 0 for an action, quit
                quit = true;
                System.out.println(exit);
                return;
            } else if (action > 4 || action < 1) {
                System.out.println(actionCorrection);
            }
        } while (action > 4 || action < 1);

        if (action == 1) { //edit title
            System.out.println(newPostTitlePrompt);
            edit.editTitle(post,post.getAuthor(),scanner.nextLine());

        } else if (action == 2) { //edit content
            System.out.println(newPostContentPrompt);
            edit.editComment(post,post.getAuthor(),scanner.nextLine());

        } else if (action == 3) { // edit author name
            System.out.println("Enter new Author Name: ");
            edit.editComment(post,post.getAuthor(),scanner.nextLine());

        } else if (action == 4) { //delete post
            System.out.println(deletionConfirmation);
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                user.getPosts().remove(post);
            }
        }
    }
    public void viewUsersPosts(Account user) {
        Scanner scanner = new Scanner(System.in);
        int action = 0; //default to zero to prevent
        do {
            System.out.println(viewUserOptions);
            try {
                action = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(actionCorrection);
                continue;
            }
            if (action == 0) { //anytime the user enters a 0 for an action, quit
                quit = true;
                System.out.println(exit);
                return;
            } else if (action > 4 || action < 1) {
                System.out.println(actionCorrection);
            }
        } while (action > 3 || action < 1);

        if (action == 1) {
            System.out.println(user.toString());
        } else if (action == 2) {
            ArrayList<Post> posts = user.getPosts();
            for (int x = 0; x < posts.size(); x++) {
                System.out.println(posts.get(x).toString());
            }
        }
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedOut = false;

        while (!loggedOut) {
            System.out.println(mainMenu);

            int action = 0; //default to zero to prevent IDE errors
            do {
                try {
                    action = scanner.nextInt();
                    scanner.nextLine(); //remove \n from pipeline
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println(actionCorrection);
                    continue;
                }
                if (action == 0) {
                    System.out.println(exit);
                    quit = true;
                    return; //return to start() method, start method will end the program since field quit = true
                } else if (action < 1 || action > 7) {
                    //there are 7 options to choose from (other than 0 for quit
                    System.out.println(actionCorrection);
                }
            } while (action < 1 || action > 7); //action 0 would have executed returns statement

            if (action == 1) { //go to your profile page
                yourProfile();

            } else if (action == 2) { //create post
                System.out.println(postTitlePrompt); //get title
                String title = scanner.nextLine();
                System.out.println(postContentPrompt); //get message
                String content = scanner.nextLine();
                user.addPost(new Post(title, user.getUsername(), content)); //add it to the list of posts

            } else if (action == 3) { //view and edit your posts
                //display posts from this user with numbers beside them
                ArrayList<Post> posts = user.getPosts();
                for (int x = 0; x < posts.size(); x++) {
                    System.out.println("Post " + (x + 1) + posts.get(x).toString() + "\n");
                }

                //display option to edit a post and get input
                int postChoice = 0; //default to zero to prevent ide errors
                do {
                    System.out.println(postChoicePrompt);
                    try {
                        postChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException inputMismatchException) {
                        System.out.println();
                    }
                    if (postChoice > posts.size() || postChoice < -1) {
                        System.out.println(actionCorrection);
                    } else if (postChoice == 0) {
                        System.out.println(exit);
                        quit = true;
                        return; //return to start() method, start method will end the program since field quit = true
                    }
                } while (postChoice > posts.size() || postChoice < -1);

                if (postChoice > 0) {
                    editPost(posts.get(postChoice - 1));
                }

            } else if (action == 4) { //view and edit all your comments
                //TODO
		for (int i = 0; i < user.getPosts().size(); i++) {
                    ArrayList<Comment> comments = user.getPosts().get(i).getComments();
                    System.out.println("Post " + (i + 1) + "\n" + comments.get(i).toString() + "\n");
                }
		//TODO: the comment should be added under action 5--view all post

            } else if (action == 5) { //view other people's posts
                //TODO

            } else if (action == 6) { //search for a specific user
                ArrayList <Account> accounts = getAccounts(); //this will be a method to retrieve all users from
                System.out.println(searchRequest);
                String user = scanner.nextLine();

                Account correctUser;

                for (int x = 0; x < accounts.size(); x++) {
                    if (accounts.get(x).getUsername().equalsIgnoreCase(user)) {
                        correctUser = accounts.get(x);
                    }
                }

                if (correctUser == null) {//if no user is found
                    System.out.println(userNotFound);
                } else {
                    System.out.println(userFound);
                    viewUsersPosts(correctUser);
                }

                System.out.println(correctUser.toString());

            } else if (action == 7) { //logout
                loggedOut = true;
                System.out.println(logout);

            } else { //anything else is not a valid input
                //(this block should never be executed because of input validation above). Just for good standards
                System.out.println(actionCorrection);
            }
        }
    }

    public void start() { //control the flow of the user experience. Future run() method for threads?
        do {
            login();
            if (!quit)
                mainMenu();
        } while (!quit);
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.start(); //run the program
    }
}
