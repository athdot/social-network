/**
 * Account - CS180 PJ04
 * This is a simple class to manage a user Account
 *
 * @author Charles Graham
 * @version 07/17/2021
 **/

public class Account {

    private String username; // Basic login information: user and pass
    private String password;
    private String bio; //A short blurb about the user

    //array list of comments and posts made by this account
    //EDIT: Do we even need this? We can get a list of posts and comments directly from the files
//    private ArrayList<Post> posts;
//    private ArrayList<Comment> comments; //NOTE: not the same as Comment ArrayList in Post.java
    
    public Account(String username, String password) { //used for when user creates account without bio initially
        //TODO: Check if user exists, and if they do output error?
        this.username = username;
        this.password = password; //will be encrypted from Application.java
        bio = ""; //user can set the bio later
        //posts and comments arrayList can be updated along the way
    }

    public Account(String username, String password, String bio) { //used for reading/writing files for Account info
        this.username = username;
        this.password = password;
        this.bio = bio; //remember the bio
        //posts and comments will be set through some other command
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        String formattedProfile = String.format("Username: %s\nBio: %s", username, bio);
        return formattedProfile;
    }
}