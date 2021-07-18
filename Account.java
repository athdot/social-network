import java.util.ArrayList;
import java.util.Date; 

/**
 * Account - CS180 PJ04
 *
 * This is a simple class to manage a user Account
 *
 * @author C. H. Graham
 *
 * @version 07/17/2021
 *
 **/

public class Account {
    // Basic login information
    private String username;
    private String password;

    //A short blurb about the user 
    private String bio;

    //A list of all the users posts
    private ArrayList<Post> posts;

    //Lists of all following and followed users
    private ArrayList<String> following;
    private ArrayList<String> followers;

    private static final String lineBreak = "=-=-=-=-=-=-=-=-=-=-=--=-=";
    
    public Account(String username, String password) {
        //TODO: Check if user exists, and if they do output error?
        this.username = username;
        this.password = cryptoHashFunction(password);
        posts = new ArrayList<Post> ();
        following = new ArrayList<String>();
        followers = new ArrayList<String>();
        bio = "";
        saveAccount();
    }

    public Account(String accountInfo) {
        //TODO: Code later, once we have code to save a Account

    }

    //This function takes a password and encrypts it, just for funsies
    private String cryptoHashFunction(String password) {
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

    //Saves Account to a file
    public void saveAccount() {
        //TODO: Code file mgmt stuff
    }

    //Returns people followed by the user
    public ArrayList<String> getFollowing() {
        return following;
    }

    //Return the users name
    public String getUsername() {
        return username;
    }

    //Return the followers of the user
    public ArrayList<String> getFollowers() {
        return followers;
    }

    //Get a users posts
    public ArrayList<Post> getPosts() {
        return posts;
    }

    //Outputs a user Account as a printable string
    public String toString() {
        String output = lineBreak + "\n\n";
        output += "Username: " + username + "\n\n";
        output += "Bio: " + bio + "\n\n";
        output += "Followers: " + followers.size() + "\n\n";
        output += "Following: " + following.size() + "\n\n";
        
        //Output posts
        for (int i = 0; i < posts.size(); i++) {
            output += posts.get(i).toString() + "\n";
        }

        output += lineBreak + "\n";
        return output;
    }

    //TODO: Remove me, for testing
    public String getPassword() {
        return password;
    }

    //Check a password with this user
    private boolean verifyPass(String input) {
        input = cryptoHashFunction(input);
        return input.equals(password);
    }

    //Setter methods
    //Set user bio
    public void setBio(String password, String newBio) {
        if (verifyPass(password)) {
            bio = newBio;
        }
    }

    //Add a post
    public void addPost(String password, Post post) {
        if (verifyPass(password)) {
            //Add post in arraylist behind newer posts
            Date newPostTime = post.getTimeStamp();

            boolean added = false;
            for (int i = 0; i < posts.size(); i++) {
                Date timeStamp = posts.get(i).getTimeStamp();
                //Returns an array formatted like this:
                //int[year,day,hour,minute]

                if (newPostTime.compareTo(timeStamp) > 0) {
                    posts.add(i, post);
                    i = posts.size();
                    added = true;
                    continue;
                }
            }

            if (!added) {
                posts.add(post);
            }
        }
    }

    //Delete a user post
    public void deletePost(String password, Post post) {
        if (verifyPass(password)) {
            posts.remove(post);
        }
    }

    //Delete a user post
    public void deletePost(String password, int index) {
        if (verifyPass(password)) {
            posts.remove(index);
        }
    }

    //Follow a Account
    public void followAccount(String password, String Account) {
        if (verifyPass(password)) {
            following.add(Account);
        }
    }

    //Unfollow a Account
    public void unfollowAccount(String password, String Account) {
        if (verifyPass(password)) {
            following.remove(Account);
        }
    }

    //Deletes this Account, the posts and everything are removed
    public void deleteAccount(String password) {
        if (verifyPass(password)) {
            //TODO: Delete user Account file
        }
    }

    //Modify a post
    public void editPost(String password, Post post, Post edits) {
        if (verifyPass(password)) {
            deletePost(password, post);
            addPost(password, edits);
        }
    }

    //Set the username
    public void setUsername(String password, String newUser) {
        if (verifyPass(password)) {
            username = newUser;
        }
    }

    //Set the password
    public void setPassword(String password, String newPassword) {
        if (verifyPass(password)) {
            password = cryptoHashFunction(newPassword);
        }
    }
}