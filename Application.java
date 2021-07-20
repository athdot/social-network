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
    private Post post;
    private String localUsername;
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

    private final static String usernameSpaceCorrection = "Spaces and Commas are not Allowed";
    private final static String userPassLengthCorrection = "Username/Password is Too Short";

    //strings pertaining to profile actions
    private final static String currentPasswordPrompt = "Enter your current password: ";
    private final static String invalidPassword = "Password is incorrect";
    private final static String changedPassword = "Password Changed";
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

    Scanner scanner;
    Backend server;
    
    public Application() {
    	scanner = new Scanner(System.in);
    	server = new Backend();
    }
    
    public void login() { //control the login section of the program

        boolean validCredentials = false;
        System.out.println(welcome);

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

            boolean correctLogin = false;
            String username;
            String password;
            
            do {
                System.out.print(usernamePrompt);
                username = scanner.nextLine();
                System.out.print(passwordPrompt);
                password = scanner.nextLine();
            
                if (username.contains(" ") || username.contains(",")) {
                    System.out.println(usernameSpaceCorrection);
                } else if (password.length() == 0 || username.length() == 0) {
                    System.out.println(userPassLengthCorrection);
                } else {
                	correctLogin = true;
                }
            } while(!correctLogin);
            
            if (actionInt == 1) { //user chooses to sign in
                String worked = server.streamReader("login[" + username + "," + password + "]");
                
                if (worked.equals("false")) {
                	System.out.println(invalidAccount);
                } else {
                	validCredentials = true;
                	break;
                }
            } else { //if (actionInt == 2), where user chooses to create new account
                String worked = server.streamReader("createAccount[" + username + "," + password + "]");
                if (worked.equals("true")) {
                	System.out.println(accountCreated);
                	validCredentials = true;
                	break;
                } else {
                	System.out.println(usernameTakenMessage);
                }
            }
        } while (!validCredentials); //continue to prompt login screen until user provides valid credentials
    }

    // Deals with username and password
    public void yourProfile() {
        boolean goBack = false;

        //while still in the profile menu
        while (!goBack) {
            //display user's profile and show options
        	Account user = StreamParse.stringToAccount(server.streamReader("getProfile[" + localUsername +"]"));
        	
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
                
                System.out.println(newPasswordPrompt);
                String newPassword = scanner.nextLine();
                if (newPassword.length() == 0) {
                    System.out.println(userPassLengthCorrection);
                }

                String changeSuccess = "changePassword[" + password + "," + newPassword + "]";
                changeSuccess = server.streamReader(changeSuccess);
                
                if (changeSuccess.equals("false")) {
                    System.out.println(invalidPassword);
                } else {
                	System.out.println(changedPassword);
                }
            } else if (action == 2) { //change username
            	boolean correctLogin = false;
            	
            	do {
                	System.out.println(newUsernamePrompt);
                	String username = scanner.nextLine();
                
                	String taken = server.streamReader("changeUsername[" + username +"]");
                	if (taken.equals("false")) {
                		System.out.println(usernameTakenMessage);
                		continue;
                	}
                
                	if (username.contains(" ") || username.contains(",")) {
                    	System.out.println(usernameSpaceCorrection);
                	} else {
                		correctLogin = true;
                	}
            	} while(!correctLogin);

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
            } else if (action > 5 || action < 1) {
                System.out.println(actionCorrection);
            }
        } while (action > 5 || action < 1);

        if (action == 1) { //edit title
            System.out.println(newPostTitlePrompt);
            //post.editTitle(user.getUsername(),scanner.nextLine());

        } else if (action == 2) { //edit content
            System.out.println(newPostContentPrompt);
            //post.editComment(user.getUsername(),scanner.nextLine());

        } else if (action == 3) { // edit author name
            System.out.println("Enter new Author Name: ");
            //post.editAuthor(user.getUsername(),scanner.nextLine());

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
                //user.addPost(new Post(title, user.getUsername(), content,user)); //add it to the list of posts

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
                // TODO: those comments should change name to "received comments" and supposed to move the action 3!
                DataManagement dm = new DataManagement();
                ArrayList<Post> posts = dm.getUserPosts(user.getUsername());
                //find specific user's posts with comments
                /*for (int i = 0; i < dm.getUserPosts(user.getUsername()).size(); i++) {
                    System.out.println("Post " + (i + 1) + posts.get(i).toString() + "\n");
                    if (posts.get(i).getComments().get(i) != null) {  //if there are comments
                        for (int j = 0; j < posts.get(i).getComments().size(); j++) {
                            System.out.println("Comments " + (j + 1) + posts.get(i).getComments().get(j).toString());
                        }
                    }
                }*/
                //edit the comments
                int editCommentChoice = 0;
                int postChoice = 0;
                String commentEdited = "";
                do {
                    System.out.println("Select the post you want to edit your comment(s)");
                    postChoice = scanner.nextInt();  //minus one to get the true position
                    System.out.println(dm.getUserPosts(user.getUsername()).get(postChoice).toString());
                    System.out.println("Enter the number of comment you want to edit");
                    editCommentChoice = scanner.nextInt();
                    if (postChoice >= posts.size() || postChoice < -1) {
                        break;
                    }
                    scanner.nextLine();
                    commentEdited = scanner.nextLine();
                    //edit comments under the "Post" object
                    //dm.getUserPosts(user.getUsername()).get(postChoice).getComments().
                    //        get(editCommentChoice).editComment(dm.getUserPosts(user.getUsername()).get(postChoice),
                    //        user.getUsername(), commentEdited);
                    //save to database
                    dm.setPost(dm.getUserPosts(user.getUsername()).get(postChoice));
                } while (postChoice > posts.size() || postChoice < -1);
		    
		//TODO: the comment should be added under action 5--view all post

            } else if (action == 5) { //view other people's posts
                //TODO: @Mingrui will implement this soon

            } else if (action == 6) { //search for a specific user
                //ArrayList <Account> accounts = getAccounts(); //this will be a method to retrieve all users from
                System.out.println(searchRequest);
                String user = scanner.nextLine();

                Account correctUser;

                //for (int x = 0; x < accounts.size(); x++) {
                //    if (accounts.get(x).getUsername().equalsIgnoreCase(user)) {
                //        correctUser = accounts.get(x);
                //    }
                //}

                //if (correctUser == null) {//if no user is found
                //    System.out.println(userNotFound);
                //} else {
                //    System.out.println(userFound);
                //    viewUsersPosts(correctUser);
                //}

                //System.out.println(correctUser.toString());

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
