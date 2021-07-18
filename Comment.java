import java.util.Date;

/**
 * Application - Project 4, Social Network App
 * Handles overall functionality of application, brings together Account, Comment, and Post
 *
 * @author Group 8, N. Yao, ...
 * @version July 17, 2021
 */

public class Comment {

    private Account author; //author who made the comment
    private String content; //content of the comment
    private Date timestamp; //timestamp of comment

    public Comment(Account author, String content) {
        this.author = author;
        this.content = content;
        timestamp = new Date();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String toString() {
        String output = "";
        output += "Author: " + author + "\n";
        output += "Posted: " + timestamp.toString() + "\n";
        output += ">> " + content + "\n";
        return output;
    }
}
