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
    private String timestamp; //timestamp of comment
    private String postTitle; //acts as marker for which post the comment belongs

    //overloaded: use this when commenting on a post
    public Comment(String author, String content, String postTitle) {
        //used for the creation of a new post, because timestamp is yet to be assigned
        this.author = author;
        this.content = content;
        timestamp = new Date().toString(); //java.util.Date is only used to generate the timestamp
        //for the remainder of its lifespan, a timestamp exists as a string.
        this.postTitle = postTitle;
    }

    //overloaded: use this when reading from file
    public Comment(String author, String content, String timestamp, String postTitle) {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.postTitle = postTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getCommentTimestamp() {
        return timestamp;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public String toString() {
        String formattedComment = String.format("%s on %s Commented:\n" +
                "%s", author, timestamp, content);
        return formattedComment;
    }
}
