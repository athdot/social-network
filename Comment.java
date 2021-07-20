import java.util.Date;

/**
 * Application - Project 4, Social Network App
 * Handles overall functionality of application, brings together Account, Comment, and Post
 *
 * @author Group 8, N. Yao, ...
 * @version July 17, 2021
 */

public class Comment {

    private String author; //author who made the comment
    private String content; //content of the comment
    private Date timestamp; //timestamp of comment

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
        timestamp = new Date();
    }
    
    public Comment(String author, String content, Date timestamp) {
    	this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    
    public String toFile() {
    	String output = "";
    	output += author + "," + timestamp.toString() + "," + content;
    	return output;
    }

    public String toString() {
        String output = "";
        output += "Author: " + author + "\n";
        output += "Posted: " + timestamp.toString() + "\n";
        output += ">> " + content + "\n";
        return output;
    }
}
