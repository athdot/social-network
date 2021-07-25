import java.io.IOException;

/**
 * Function.java
 * Will handle all necessary backend file manipulation to achieve required features.
 *
 * Methods in this class (subclass of Data.java) perform: account creation, login verification, change password, change
 * bio, change username, account deletion, post creation, change post title, edit post content, post deletion,
 * list all posts (newest first), import post as CSV, export post as CSV, create comment, edit comment content, delete
 * comment, search for user, get posts of a specific user, get comments made by a user.
 *
 * Idea for client-server model (PJ05): Let there be a server class which has access to Function. The client will
 * send the server codes, telling it what to do. Keep server running during this process, listening for different codes
 * and executing different actions on Function
 *
 * Server should send appropriate information back to the client, allowing GUI screens to display appropriate strings.
 * Another class should handle
 *
 * @author N. Yao (experimental)
 * @version Jul 24, 2021
 */
public class Function extends Data {

    /**
     * This method stores a new account into the account database. Static to prevent race conditions.
     * @param username - username of new account
     * @param password - password of new account
     */
    public synchronized static void createAccount(String username, String password) {
        accountArrayList.add(new Account(username, password));
    }

    /**
     * Use this method when the user tries to sign in. If the user signs in correctly, run getUser() to return all
     * information related to the user that has signed in.
     * @param username - attempted username credential
     * @param password - attempted password credential
     * @return true/false; true if username and password match an existing account
     */
    public synchronized static boolean signIn(String username, String password) {
        //for every account in the file, try to match username and password. Return true if credentials are correct
        for (Account account : accountArrayList) {
            if (username.equalsIgnoreCase(account.getUsername()) && password.equals(account.getPassword()))
                return true;
        }
        return false;
    }

    /**
     * Run this if the user logs in correctly. Instantiate an account object to remember all information of user who
     * has logged in.
     * @param username - username of the user that logged in correctly
     * @return Account - storing all Account information (username, password, bio) of user who logged in
     */
    public synchronized static Account getUser(String username) {
        for (Account account : accountArrayList) {
            if (account.getUsername().equalsIgnoreCase(username))
                return account;
        }
        return null; //this statement should never be reached
    }

    /**
     * Change the password of the an account. Identify the user by current username.
     * @param username - username of the user who is changing password
     * @param newPassword - new password of the user
     */
    public synchronized static void changePassword(String username, String newPassword) {
        for (Account account : accountArrayList) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                account.setPassword(newPassword);
                break;
            }
        }
    }

    /**
     * Change the biography of an account. Identify the user by current username.
     * @param username - username of the user who is changing biography
     * @param newBio - new biography of the user
     */
    public synchronized static void changeBiography(String username, String newBio) {
        for (Account account : accountArrayList) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                account.setBio(newBio);
                break;
            }
        }
    }

    /**
     * Change the username of an account. Identify the user by current username
     * @param username - username of the user who is changing biography
     * @param newUsername - new biography of the user
     */
    public synchronized static void changeUsername(String username, String newUsername) {
        for (Account account : accountArrayList) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                account.setUsername(newUsername);

                //change current username to new username in posts database
                for (Post post : postArrayList) {
                    if (post.getAuthor().equalsIgnoreCase(username))
                        post.setAuthor(newUsername);
                }

                //change current username to new username in comments database
                for (Comment comment: commentArrayList) {
                    if (comment.getAuthor().equalsIgnoreCase(username))
                        comment.setAuthor(newUsername);
                }

                break;
            }
        }
    }

    /**
     * delete an account. Identify user by current username
     * @param username - username of specified account to be deleted
     */
    public synchronized static void deleteAccount(String username) {
        for (Account account : accountArrayList) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                accountArrayList.remove(account); //remove account from current ArrayList
                break;
            }
        }
    }

    /**
     * create a post for the current user
     * @param username - username of the specified user who's creating a post
     * @param title - title of post
     * @param content - content of post
     */
    public synchronized static void createPost(String username, String title, String content) {
        postArrayList.add(new Post(username, title, content)); //NOTE: newest posts are at the back of the array
    }

    /**
     * change a post title
     * @param title - current title of the post (acts as ID for the post specified)
     * @param newTitle - new title for the post
     */
    public synchronized static void changePostTitle(String title, String newTitle) {
        //NOTE: two+ posts will not be able to have the same title, as title will be be a post's ID
        for (Post post : postArrayList) {
            if (post.getTitle().equals(title)) {
                post.setTitle(newTitle);
                break;
            }
        }
    }

    /**
     * change a post's content
     * @param title - current title of the post (acts as ID for the post specified)
     * @param newContent - new content for the post
     */
    public synchronized static void changePostContent(String title, String newContent) {
        for (Post post: postArrayList) {
            if (post.getTitle().equals(title)) {
                post.setContent(newContent);
                break;
            }
        }
    }

    /**
     * delete a post with specified title
     * @param title - current title of the post (acts as ID for the post specified)
     */
    public synchronized static void deletePost(String title) {
        for (Post post : postArrayList) {
            if (post.getTitle().equals(title)) {
                postArrayList.remove(post);
                break;
            }
        }
    }

    /**
     * list all posts, newest first, regardless of user. LOGIC: newest posts are at the end of postArrayList
     */
    public synchronized static String allPosts() {
        String allPosts = "";
        int postNumber = 0; //give each post a number, so user can choose to comment on it
        for (int i = postArrayList.size(); i > 0; i--) { //newest posts are at the back of the list
            allPosts += "#" + ++postNumber + " " + postArrayList.get(i - 1).toString() + "\n\n";
        }
        return allPosts;
    }

    /**
     * import a post as CSV, given the filename of the CSV
     * @param filename - filename containing the post to be imported; post must be on first line
     */
    public synchronized static void importPost(String filename) {
        try {
            Post post = readPost(filename);
            postArrayList.add(post);
        } catch (IOException | NullPointerException exception) {
            System.out.println(FILE_ERROR);
        }
    }

}
