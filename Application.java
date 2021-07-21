import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Application - Project 4, Social Network App
 * Front end of the application, brings together all classes. Makes contact with backend classes Backend,
 * DataManagement, and StreamParse. Multiple methods to run the app interface are included below.
 *
 * OTHER NOTES: Logic that is too large to contain easily in mainMenu() are split off into their own methods on the
 * server. For easy navigation, collapse methods and work on the one you need. FUTURE
 *
 * @author Group 8
 * @version July 17, 2021
 */

public class Application {

    private Account user; //remember the user who is signed in to this instance of the app
    private Post post;
    private Comment task;
    private String localUsername;
    private boolean quit = false; //becomes true if user enters 0 for action. Program terminates

    private final static String welcome = "" +
            "+--------------------------------------------------+\n" +
            "| Welcome to Group 8's Social Network Application! |\n" +
            "|                                                  |\n" +
            "| Written by:                                      |\n" +
            "| Charles Graham, Nathan Yao, Mingrui Xia,         |\n" +
            "| Jasmine Maduafokwa, and Sami Heathcote           |\n" +
            "|                                                  |\n" +
            "| Copyright: We have no money for a copyright      |\n" +
            "|                                                  |\n" +
            "| NOTE: Enter 0 as an action to QUIT.              |\n" +
            "+--------------------------------------------------+";
    private final static String chooseAction = "Choose an Action:\n";
    private final static String loginPage = "\n" + chooseAction +
            "+--------------------------------------------------+\n" +
            "| LOGIN PAGE                                       |\n" +
            "| 1. Sign In                                       |\n" +
            "| 2. Create New Account                            |\n" +
            "+--------------------------------------------------+";
    private final static String mainMenu = "\n" + chooseAction +
            "+--------------------------------------------------+\n" +
            "| MAIN MENU                                        |\n" +
            "| 1. Your Profile                                  |\n" +
            "| 2. Create Post                                   |\n" +
            "| 3. View Your Posts                               |\n" +
            "| 4. View Your Comments                            |\n" +
            "| 5. View All Posts                                |\n" +
            "| 6. Search User                                   |\n" +
            "| 7. Logout                                        |\n" +
            "+--------------------------------------------------+";
    private final static String yourProfile = "\n" + chooseAction +
            "+--------------------------------------------------+\n" +
            "| YOUR PROFILE                                     |\n" +
            "| 1. Change Bio                                    |\n" +
            "| 2. Change Username                               |\n" +
            "| 3. Change Password                               |\n" +
            "| 4. Delete Account                                |\n" +
            "| 5. Back                                          |\n" +
            "+--------------------------------------------------+";
    private final static String editPost = "\n" + chooseAction +
            "+--------------------------------------------------+\n" +
            "| EDIT POST                                        |\n" +
            "| 1. Edit Title                                    |\n" +
            "| 2. Edit Content                                  |\n" +
            "| 3. Add a Comment                                 |\n" +
            "| 4. Export Post                                   |\n" +
            "| 5. Delete Post                                   |\n" +
            "| 6. Back                                          |\n" +
            "+--------------------------------------------------+";
    private final static String createNewPost = "\n" + chooseAction +
            "+--------------------------------------------------+\n" +
            "| CREATE POST                                      |\n" +
            "| 1. Write New Post                                |\n" +
            "| 2. Import Post from CSV                          |\n" +
            "| 3. Back                                          |\n" +
            "+--------------------------------------------------+";
    private final static String viewCommentOptions = "\n" + chooseAction +
            "+--------------------------------------------------+\n" +
            "| EDIT POST                                        |\n" +
            "| 1. Add Comment                                   |\n" +
            "| 2. Edit Comment                                  |\n" +
            "| 3. Delete Comment                                |\n" +
            "| 4. Export as CSV                                 |\n" +
            "| 5. Back                                          |\n" +
            "+--------------------------------------------------+";
    private final static String viewUserOptions = "\n" + chooseAction +
            "+--------------------------------------------------+\n" +
            "| VIEW                                             |\n" +
            "| 1. View Profile                                  |\n" +
            "| 2. View Posts                                    |\n" +
            "| 3. View Comments                                 |\n" +
            "| 4. Back                                          |\n" +
            "+--------------------------------------------------+";
    private final static String allPostOptions = "\n" + chooseAction +
    		"+--------------------------------------------------+\n" +
    		"| OPTIONS                                          |\n" +
    		"| 1. Redisplay Page                                |\n" +
    		"| 2. View Post                                     |\n" +
    		"| 3. Load Next 5 Posts                             |\n" +
    		"| 4. Load Last 5 Posts                             |\n" +
    		"| 5. Display All Posts                             |\n" +
    		"| 6. Back                                          |\n" +
    		"+--------------------------------------------------+";
    private final static String totalOptions = "\n" + chooseAction +
    		"+--------------------------------------------------+\n" +
    		"| OPTIONS                                          |\n" +
    		"| 1. Redisplay Page                                |\n" +
    		"| 2. View Post                                     |\n" +
    		"| 3. Back                                          |\n" +
    		"+--------------------------------------------------+";

    //string constants for login section
    private final static String actionCorrection = "Invalid Action";
    private final static String invalidAccount = "Username/Password is Wrong";
    private final static String fileErrorMessage = "Something Went Wrong";
    private final static String usernameTakenMessage = "Username is Taken";
    private final static String accountCreated = "Account Created";

    private final static String usernamePrompt = "Your Username: ";
    private final static String passwordPrompt = "Your Password: ";

    private final static String usernameSpaceCorrection = "Spaces and Commas are not Allowed in Username";
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
    private final static String onePostName = "2 of Your Posts Cannot Have the Same Name!";
    private final static String createComment = "Enter your Comment: ";

    //strings pertaining to post editing/deletion
    private final static String postChoicePrompt = "Enter the number of the post you would like to edit"
        + "\nEnter 0 to return to the main screen: ";
    private final static String newPostTitlePrompt = "Enter a new Post Title: ";
    private final static String newPostContentPrompt = "Enter the Post's new Message: ";
    private final static String deletionConfirmation = "Are you sure you would like to delete this post? (Y/N): ";

    //strings pertaining to comment info
    private final static String commentDelete = "What comment would you like to delete?";
    private final static String commentEdit = "What comment would you like to edit?";

    //strings pertaining to search users
    private final static String searchRequest = "Enter the username of the user you want to view or enter " +
            "-1 to return to the main screen: ";
    private final static String userNotFound = "There is no user with that username.";
    private final static String userFound = "Search successful. User found.";
    private final static String addComment = "Choose the number of the post you would like to comment on";

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
        System.out.println(welcome);

        do {
            int actionInt = 0; //user must enter an integer action, store that value from actionStr in here
            do {
                System.out.println(loginPage);
                try {
                    actionInt = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException numberFormatException) {
                    System.out.println(actionCorrection);
                    continue; //skip the current iteration of the loop
                } catch (NoSuchElementException noSuchElementException) { //catch this for JUnit testing debug
                    System.out.println("WHY");
                }
                if (actionInt == 0) { //user enters 0 to exit the program (see app() method)
                    System.out.println(exit);
                    server.streamReader("logout");
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
                	user = new Account(username.toLowerCase(), password);
                	//current user signed in
                }
            } while(!correctLogin);

            if (actionInt == 1) { //user chooses to sign in
            	String worked = "login[" + username.toLowerCase() + "," + password + "]";
                worked = server.streamReader(worked);

                if (worked.equals("false")) {
                	System.out.println(invalidAccount);
                } else {
                	localUsername = username;
                	break;
                }
            } else { //if (actionInt == 2), where user chooses to create new account
            	String worked = "createAccount[" + username.toLowerCase() + "," + password + "]";
                worked = server.streamReader(worked);
                if (worked.equals("true")) {
                	System.out.println(accountCreated);
                	localUsername = username;
                	break;
                } else {
                	System.out.println(usernameTakenMessage);
                }
            }
        } while (true); //continue to prompt login screen until user provides valid credentials
    }

    // Deals with username and password
    public boolean yourProfile() {
        boolean goBack = false;

        //while still in the profile menu
        while (!goBack) {
            //display user's profile and show options
        	Account user = StreamParse.stringToAccount(server.streamReader("getProfile[" + localUsername +"]"));

            System.out.println(user.toString());
            System.out.println(yourProfile);

            boolean validAction = false;
            int action = 0;
            do {
                try {
                    action = Integer.parseInt(scanner.nextLine());
                    validAction = true;
                } catch (NumberFormatException numberFormatException) {
                    System.out.println(actionCorrection);
                }
            } while (!validAction);

            //if it's an invalid action, say so
            if (action < 0 || action > 5) {
                System.out.println(actionCorrection);
            } else if (action == 5) {
                goBack = true;
            } else if (action == 4) {
            	System.out.println("Are you sure you want to delete account " + localUsername + "? (Y/N)");
            	String thing = scanner.nextLine();
            	if (thing.toUpperCase().equals("Y")) {
            		System.out.println("Deleting account...");
            		server.streamReader("deleteAccount");
            		return true;
            	}
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

                    String taken = server.streamReader("changeUsername[" + username.toLowerCase() + "]");
                    if (taken.equals("false")) {
                        System.out.println(usernameTakenMessage);
                        continue;
                    } else {
                        localUsername = username;
                    }

                    if (username.contains(" ") || username.contains(",")) {
                        System.out.println(usernameSpaceCorrection);
                    } else {
                        correctLogin = true;
                    }
                } while (!correctLogin);

//            // User can choose to computer generate a username //why is this here?
//            } else if (action == 5) {
//                user.computerGenerateName(user.getUsername());
//            // User can choose to computer generate a password
//            } else if (action == 6) {
//                user.computerGenerateName(user.getPassword());

            } else if (action == 0) {
                quit = true; //program will exit

            } else if (action == 1) { //change bio
                System.out.println(newBioPrompt);
                server.streamReader("changeBio[" + scanner.nextLine() +"]");
            }
        }

        return false;
    }

    public void editPost(Post post) {
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
            } else if (action > 6 || action < 1) {
                System.out.println(actionCorrection);
            }
        } while (action > 6 || action < 1);

        if (action == 1) { //edit title
            System.out.println(newPostTitlePrompt);
            //post.editTitle(user.getUsername(),scanner.nextLine());
            boolean goodTitle = false;
            String newTitle;
            do {
            	newTitle = scanner.nextLine().replace(",","123COMMA_REP321");
            	String parsedTitle = post.getTitle().replace(",","123COMMA_REP321");
            	String input = "editTitle[" + post.getTitle() + "," + post.getAuthor() + "," + newTitle + "]";
            	input = server.streamReader(input);

            	goodTitle = input.equals("true");
            	if (!goodTitle) {
            		System.out.println(onePostName);
            	}
            } while(!goodTitle);

            String postUpdate = server.streamReader("getPost[" + newTitle + "," + post.getAuthor() + "]");
            post = StreamParse.stringToPost(postUpdate);

        } else if (action == 2) { //edit content
            System.out.println(newPostContentPrompt);
            //post.editComment(user.getUsername(),scanner.nextLine());
            String content = scanner.nextLine();
            server.streamReader("editPost[" + post.getTitle() + "," + post.getAuthor() + "," + content + "]");
        } else if (action == 3) { //add comment
        	System.out.println(createComment);
        	String newComment = scanner.nextLine();
        	server.streamReader("addComment[" + post.getTitle() + ","+ post.getAuthor() + "," + newComment + "]");
        } else if (action == 4) {
        	System.out.println("What do you wish to name the file?");
        	try {
        		exportAsCsv(scanner.nextLine(), post);
        	} catch (Exception e) {
        		System.out.println("Error: Saving to file failed...");
        	}
        } else if (action == 5) { //delete post
            System.out.println(deletionConfirmation);
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                server.streamReader("deletePost[" + post.getTitle() +  "," + post.getAuthor() + "]");
            }
        }
    }

    public void editComment(Post post) {
        int action = 0; //default to zero to prevent
        do {
            System.out.println(viewCommentOptions);
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

        if (action == 1) { //Add comment
        	System.out.println(createComment);
        	String call = scanner.nextLine().replace("\n","");
        	call = "addComment[" + post.getTitle() + "," + post.getAuthor() + "," + call + "]";
        	System.out.println("here");
        	server.streamReader(call);
        } else if (action == 2) { //Edit comment
        	ArrayList<Comment> comments = post.getComments();
            ArrayList<Comment> yourComment = new ArrayList<Comment>();

            for (int i = 0; i < comments.size(); i++ ) {
            	if (comments.get(i).getAuthor().equals(localUsername)) {
            		yourComment.add(comments.get(i));
            	}
            }
            System.out.println("Your Comments: ");
            for (int x = 0; x < yourComment.size(); x++) {
            	System.out.println("Comment " + (x +  1) + ": \n");
            	System.out.println("| Post: " + post.getTitle());
                System.out.println(yourComment.get(x).toString());
            }
            System.out.println("Select a Comment to Edit: ");
            String del = scanner.nextLine();

            try {
            	int opt = Integer.parseInt(del);
            	System.out.println("What would you like to change the comment to?");
            	String newComment = scanner.nextLine();
            	if (newComment.length() < 1) {
            		newComment = " ";
            	}
            	String call = "editComment[" + post.getTitle() + "," + post.getAuthor();
            	call += "," + (opt - 1) + "," +  newComment + "]";
            	server.streamReader(call);
            	System.out.println("Edited Comment!");
            } catch (Exception e) {
            	System.out.println("Not a valid number!");
            }
        } else if (action == 3) { //Delete comment
            //DONE: add option to view user comments and make action 4 to go back
            ArrayList<Comment> comments = post.getComments();
            ArrayList<Comment> yourComment = new ArrayList<Comment>();

            for (int i = 0; i < comments.size(); i++ ) {
            	if (comments.get(i).getAuthor().equals(localUsername)) {
            		yourComment.add(comments.get(i));
            	}
            }
            System.out.println("Your Comments: ");
            for (int x = 0; x < yourComment.size(); x++) {
            	System.out.println("Comment " + (x +  1) + ": \n");
            	System.out.println("| Post: " + post.getTitle());
                System.out.println(yourComment.get(x).toString());
            }
            System.out.println("Select a Comment to Delete: ");
            String del = scanner.nextLine();
            try {
            	int opt = Integer.parseInt(del);
            	String call = "deleteComment[" + post.getTitle() + "," + post.getAuthor();
            	call += "," + (opt - 1) + "]";
            	server.streamReader(call);
            	System.out.println("Deleted Comment!");
            } catch (Exception e) {
            	System.out.println("Not a valid number!");
            }
        } else if (action == 4) {
        	System.out.println("\nWhat do you wish to name the file?");
        	try {
        		exportAsCsv(scanner.nextLine(), post);
        	} catch (Exception e) {
        		System.out.println("Error: Saving to file failed...");
        	}
        } //action 5 will end this current method
    }

    public void createPost() {
        System.out.println(createNewPost);
        boolean validAction = false;
        int action = 0;
        do {
            try {
                action = Integer.parseInt(scanner.nextLine());
                validAction = true;
            } catch (NumberFormatException numberFormatException) {
                System.out.println(actionCorrection);
            }
        } while (!validAction);

        if (action == 1) { //write a new post
            boolean postSuccess;
            do {
                System.out.println(postTitlePrompt); //get title
                String title = scanner.nextLine();
                System.out.println(postContentPrompt); //get message
                String content = scanner.nextLine();

                //Cannot create multiple posts of the same name
                if (title.length() == 0) {
                	title = "Blank Post";
                }
                if (content.length() == 0) {
                	content = "Blank Content";
                }
                title = title.replace(",","123COMMA_REP321");
                String worked = server.streamReader("post[" + title + "," + content +"]");
                postSuccess = worked.equals("true");

                if (!postSuccess) {
                    System.out.println(onePostName);
                }
                //user.addPost(new Post(title, user.getUsername(), content,user)); //add it to the list of posts
            } while (!postSuccess);
        } else if (action == 2) {
            DataManagement dm = new DataManagement();
            System.out.println("Enter name of CSV file to import (including the extension)");
            String importFilename = scanner.nextLine();

            //methods in mind: readFile, getPost, toPost, writeFile
            ArrayList<String[]> importBlock = dm.readFile(importFilename);
            ArrayList<String[]> existing = dm.readFile("post.csv");

//            for (int i = 0; i < importBlock.get(0).length; i++) { //length is 4 (has 1 comment, otherwise 3)
//                System.out.println(importBlock.get(0)[i]); //DEBUGGING
//            }
//            System.out.println(user.getUsername());

            try {
                if (importBlock.get(0)[2].split(",")[0].equals(user.getUsername())) {
                    //make sure that the username of the imported post matches currently signed-in user.
                    //you can't import a post for someone else.
                    System.out.println("Importing...");
                    existing.add(0, importBlock.get(0));
                    dm.writeFile("post.csv", existing);
                } else {
                    System.out.println("This post cannot be imported");
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("File Access Failed");
            }
        }

    }

    public void viewUsersPosts(Account user) {
        int action = 0; //default to zero to prevent
        DataManagement dm = new DataManagement();
        do {
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
            } while (action > 4 || action < 1);

            if (action == 1) {
                System.out.println(user.toString());
            } else if (action == 2) {
                ArrayList<Post> posts = dm.getUserPosts(user.getUsername().toLowerCase());
                int x;
                for (x = 0; x < posts.size(); x++) {
                    System.out.println("Post: " + (x + 1) + posts.get(x).toString());
                }
                if (x == 0) {
                	System.out.println("This user has no posts...");
                	continue;
                }

                //ask if the user wants to comment on a post
                //System.out.println("Would you like to comment on a post? (Y/N)");
                //String input;
                //do {
                //    input = scanner.nextLine();

                //    if (!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n"))) {
                //        System.out.println("Please enter Y or N");
                //    }
                //} while (!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")));

                //if (input.equalsIgnoreCase("n")) {
                //    continue;
                //}
                //display option to comment on a post and get input
                //int postChoice = 0; //default to zero to prevent ide errors
                //do {
                //   if (posts.size() == 0) {
                //        System.out.println("There are no posts to comment on!");
                //        break;
                //    }
                //    System.out.println(addComment);
                //    try {
                //        postChoice = scanner.nextInt();
                //        scanner.nextLine();
                //    } catch (InputMismatchException inputMismatchException) {
                //        System.out.println();
                //    }
                //    if (postChoice > posts.size() || postChoice < -1) {
                //        System.out.println(actionCorrection);
                //    } else if (postChoice == 0) {
                //        System.out.println(exit);
                //        quit = true;
                //        return; //return to start() method, start method will end the program since field quit = true
                //    }
                //} while (postChoice > posts.size() || postChoice < -1);

                //if (postChoice > 0) {
                //    String newComment = "";

                //    String[] emojis = new String[0];

                //    System.out.println("Would you like to enter a comment? (Y/N)");

                //    String statement = scanner.nextLine();

                //    while (statement.equalsIgnoreCase("y")) {
			    // Makes a drop-down menu of emojis with numbers next to them
                //        task.emojis(emojis);

                //        System.out.println(createComment);

                //        String yourComment = scanner.nextLine();

                //        newComment += yourComment;

                //        System.out.println("Emoji? Press 1: ");

                //        //DONE: make sure this has input validation
                //        int emojiChoice = 0; //default to 0 preventing IDE compile errors
                //        boolean validInput = false;
                //        do {
                //            try {
                //                emojiChoice = scanner.nextInt();
                //                scanner.nextLine();
                //                validInput = true;
                //            } catch (InputMismatchException inputMismatchException) {
                //                System.out.println(actionCorrection);
                //            }
                //        } while (!validInput);

                //        while (emojiChoice == 1) {
                //            if (emojiChoice == 1) {
				// Function includes a scanner in which each number corresponds to an emoji
				// Emoji picked is added to empty string along with comment
//                                newComment += task.emojiSelection(emojis);

                //                System.out.println("Select 1 for another emoji or pick any other number " +
                //                        "to exit");

                                //DONE: make sure this has input validation
                //                boolean validInput2 = false;
                //                do {
                //                    try {
                //                        emojiChoice = scanner.nextInt();
                //                        scanner.nextLine();
                //                        validInput2 = true;
                //                    } catch (InputMismatchException inputMismatchException) {
                //                        System.out.println(actionCorrection);
                //                    }
                //                } while (!validInput2);
                //            }
                //        System.out.println("Would you like to continue comment (Y) or exit (N)? ");
                //        statement = scanner.nextLine();

                //    }
                //    server.streamReader("addComment[" + posts.get(postChoice - 1).getTitle() + ","
                //           + posts.get(postChoice - 1).getAuthor() + "," + newComment + "]");
                //}
                //}

            } else if (action == 3) {
            	String stream = "getUserComments[" + localUsername.toLowerCase() + "]";
                ArrayList<Post> posts = StreamParse.stringToPosts(server.streamReader(stream));

                for (int x = 0; x < posts.size(); x++) {
                    System.out.println("Post " + (x + 1) + posts.get(x).toString() + "\n");
                }
            } else if (action == 4) {
                break;
            }
        } while (action != 4);
    }

    public void mainMenu() {
        boolean loggedOut = false;
        DataManagement dm = new DataManagement();

        while (!loggedOut && !quit) {
            System.out.println(mainMenu); //main menu is too large to replay if invalid action message is displayed

            int action = 0; //default to zero to prevent IDE errors
            do {
            	 String actionTemp = scanner.nextLine();
                try {
                	action = Integer.parseInt(actionTemp);
                } catch (Exception e) {
                    System.out.println(actionCorrection);
                    action = 0;
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
                boolean hardExit = yourProfile();
                if (quit) {
                    System.out.println(exit);
                    return;
                } else if (hardExit) {
                	loggedOut = true;
                }
            } else if (action == 2) { //create post
                createPost();

            } else if (action == 3) { //view and edit your posts
                //display posts from this user with numbers beside them
            	String getPosts = "getUserPosts[" + localUsername.toLowerCase() + "]";
                String stream = server.streamReader(getPosts);
                ArrayList<Post> posts = StreamParse.stringToPosts(stream);
                for (int x = 0; x < posts.size(); x++) {
                    System.out.println("Post " + (x + 1) + posts.get(x).toString() + "\n");
                }

                //display option to edit a post and get input
                int postChoice = 0; //default to zero to prevent ide errors
                do {
                	if (posts.size() == 0) {
                		System.out.println("You have no posts!");
                		break;
                	}
                    System.out.println(postChoicePrompt);
                    try {
                        postChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException inputMismatchException) {
                        System.out.println(actionCorrection);
                        scanner.nextLine();
                    }
                    if (postChoice > posts.size() || postChoice < 0) {
                        System.out.println(actionCorrection);
                    } else {
                    	break;
                    }
                } while (postChoice < posts.size() || postChoice > 0);

                if (postChoice > 0) {
                    editPost(posts.get(postChoice - 1));
                }

            } else if (action == 4) { //view and edit all your comments
                // TODO: those commentted out stuffs would should the user's all posts with comments
            	String stream = "getUserComments[" + user.getUsername().toLowerCase() + "]";
                ArrayList<Post> posts = StreamParse.stringToPosts(server.streamReader(stream));
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

                for (int x = 0; x < posts.size(); x++) {
                    System.out.println("Post " + (x + 1) + posts.get(x).toString() + "\n");
                }

                //display option to edit a post and get input
                int postChoice = 0; //default to zero to prevent ide errors
                do {
                	if (posts.size() == 0) {
                		System.out.println("You have no comments!");
                		break;
                	}
                	System.out.println("Select the post you want to edit your comment(s)\n" +
                            "Enter -1 to return to main menu, 0 to quit program");
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
                    editComment(posts.get(postChoice - 1));
                }

            } else if (action == 5) {  //view all people's posts
                //print all the post from most recent
                String postAuthor = "";
                int postIndex = 0;
                int postScale = 5;

                boolean exitProcess = false;
                boolean dontShow = false;

                do {
                	String postList = "getRecentPosts[" + postIndex + "," + (postIndex + postScale) + "]";
                    postList = server.streamReader(postList);
                    ArrayList<Post> posts = StreamParse.stringToPosts(postList);
                    if (!dontShow) {
                    	for (int i = 0; i < posts.size(); i++) {
                        	System.out.println("Post: " + (postIndex + i + 1) + posts.get(i).toString());
                    	}
                    	dontShow = false;
                    }
                    dontShow = false;

                	do {
                		//Options
                		if (postScale == 5) {
                			System.out.println(allPostOptions);
                		} else {
                			System.out.println(totalOptions);
                		}
                		postAuthor = scanner.nextLine();
                		try {

                		} catch (Exception e) {
                			System.out.println("Not a proper option!");
                		}
                	} while (dm.getUserPosts(postAuthor) == null);
                	//show that user's post
                	//add comments

                	if (postAuthor.equals("1")) {
                		continue;
                	} else if (postAuthor.equals("2")) {
                		//Load a certain post

                		int postChoice = 0; //default to zero to prevent ide errors
                        do {
                        	if (posts.size() == 0) {
                        		System.out.println("User has no posts!");
                        		break;
                        	}
                            System.out.println(postChoicePrompt);
                            try {
                                postChoice = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException inputMismatchException) {
                                System.out.println();
                            }
                            if (postChoice - postIndex > posts.size() || postChoice - postIndex < -1) {
                                System.out.println(actionCorrection);
                            } else if (postChoice - postIndex == 0) {
                                postChoice = -1;
                                break; //return to start() method, start method will end the program since field quit = true
                            }
                        } while (postChoice > posts.size() || postChoice < -1);

                        if (postChoice > 0) {
                            postChoice -= (postIndex + 1);
                            if (postChoice < 0 || postChoice > posts.size()) {
                            	System.out.println("Not a valid post!");
                            } else {
                            	System.out.println("Post: " + posts.get(postChoice).toString());

                            	// Add comment options
                            	editComment(posts.get(postChoice));
                            }
                        }
                        continue;
                	}

                	if (postScale != 5) {
                		if (postAuthor.equals("3")) {
                			postScale = 5;
                			postIndex = 0;
                			continue;
                		}
                		System.out.println(actionCorrection);
                		continue;
                	}


                	if (postAuthor.equals("3")) {
                		String postList2 = "getRecentPosts[" + (postIndex + postScale);
                		postList2 += "," + (postIndex + postScale * 2) + "]";
                        postList = server.streamReader(postList2);
                        ArrayList<Post> posts2 = StreamParse.stringToPosts(postList);
                		if (posts2.size() > 0) {
                			postIndex += postScale;
                		} else {
                			System.out.println("No further posts...");
                			dontShow = true;
                		}
                	} else if (postAuthor.equals("4")) {
                		String postList2 = "getRecentPosts[" + (postIndex - postScale);
                		postList2 += "," + postIndex + "]";
                        postList = server.streamReader(postList2);
                        ArrayList<Post> posts2 = StreamParse.stringToPosts(postList);
                		if (posts2.size() > 0) {
                			postIndex -= postScale;
                		} else {
                			System.out.println("No further posts...");
                			dontShow = true;
                		}
                	} else if (postAuthor.equals("5")) {
                		//Display all posts
                		postScale = -1;
                		postIndex = 0;
                		continue;
                	} else if (postAuthor.equals("6")) {
                		exitProcess = false;
                    	break;
                	} else {
                    	System.out.println(actionCorrection);
                	}
                } while (!exitProcess);

            } else if (action == 6) { //search for a specific user
                String username;
                Account correctUser = null;

                do {
                    System.out.println(searchRequest);
                    username = scanner.nextLine().toLowerCase();
                    if (username.contains("-1")) {
                        break;
                    } else if (username.equalsIgnoreCase("0")) {
                        System.out.println(exit);
                        quit = true;
                        return;
                    }
                    if (dm.accountExists(username)) {
                        correctUser = dm.getAccount(username);
                        System.out.println(userFound);
                    } else {
                        System.out.println(userNotFound);
                    }

                } while (correctUser == null);

                if (correctUser != null) {
                    viewUsersPosts(correctUser);
                }


            } else if (action == 7) { //logout
                loggedOut = true;
                server.streamReader("logout");
                System.out.println(logout);

            } else { //anything else is not a valid input
                //(this block should never be executed because of input validation above). Just for good standards
                System.out.println(actionCorrection);
            }
        }
    }

    private void exportAsCsv(String filename, Post post) {
    	if (filename.indexOf(".csv") == -1) {
    		filename += ".csv";
    	}
    	System.out.println("Exporting '" + post.getTitle() + "' as " + filename + " ...");
    	DataManagement temp = new DataManagement();
    	ArrayList<String[]> export = new ArrayList<String[]>();
    	export.add(post.toFile());
    	temp.writeFile(filename, export);
    	System.out.println("Operation complete");
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
        try {
        	app.start(); //run the program
        } catch (Exception e) {
        	//Catch any other misc errors
        	System.out.println("\n\nAn Error Occured\n\n");
        	e.printStackTrace();
        	app.start();
        }
    }
}
