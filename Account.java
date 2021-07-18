import java.util.ArrayList;
import java.util.Date; 

/**
 * Account - CS180 PJ04
 * This is a simple class to manage a user Account
 * @author Charles Graham
 * @version 07/17/2021
 *
 **/

public class Account {
    // Basic login information
    private String username;
    private String password;

    //A short blurb about the user 
    private String bio;

    //array list of comments and posts made by this account
    private ArrayList<Post> posts;
    private ArrayList<Comment> comments;
    
    public Account(String username, String password) {
        //TODO: Check if user exists, and if they do output error?
        this.username = username;
        this.password = password; //will be encrypted from Application.java
        posts = new ArrayList<Post> ();
        bio = ""; //user can set the bio later
        saveAccount();
    }

    public Account(String accountInfo) {
        //TODO: Code later, once we have code to save a Account

    }

    public void saveAccount() { //save account to file
        //TODO: Code file mgmt stuff
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String toString() { //Outputs a user Account as a printable string
        String lineBreak = "=-=-=-=-=-=-=-=-=-=-=--=-=";
        String output = lineBreak + "\n\n";
        output += "Username: " + username + "\n\n";
        output += "Bio: " + bio + "\n\n";

        for (int i = 0; i < posts.size(); i++) { //display a list of posts created by the user
            output += posts.get(i).toString() + "\n";
        }

        for (int i = 0; i < posts.size(); i++) { //display a list of comments created by the user
            output += posts.get(i).toString() + "\n";
        }

        output += lineBreak + "\n";
        return output;
    }

    //TODO: Remove me, for testing
    public String getPassword() {
        return password;
    }

/*    //Make sure the password matches through the encryption
    public boolean verifyPass(String input) {
        input = cryptoHashFunction(input);
        return input.equals(password);
    }*/

    //Set user bio, user can set their bio
    public void setBio(String bio) {
        this.bio = bio;
    }

    //Add a post to the account (move this into Application.java, since it's a basic function?)
    public void addPost(Post post) {
        posts.add(post); //newest posts for this account go the back
/*            Date newPostTime = post.getTimeStamp();
            boolean added = false; //if user is adding a post, it would be the most recent???
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
            }*/
    }

    public void deletePost(Post post) {
        //thinking about putting this in Application
        //ALSO: addPost, deleteAccount and editPost
    }

    //Set the username
    public void setUsername(String username) {
        this.username = username;
    }

    //Set the password (password should be encrypted in Application.java)
    public void setPassword(String password) {
        this.password = password;
    }
}