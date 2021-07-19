import java.util.ArrayList;
import java.util.Date;

/**
 * Post - CS180 PJ04
 * A class representative of a social network post
 *
 * @author C. H. Graham
 * @version 07/17/2021
 **/

public class Post {

    private String author; //author who made the post
    private String title; //title of the post
    private String content; //body content of the post
    private String timestamp; //timestamp of when post was created (also acts as comment ID on the post)

//    private ArrayList<Comment> comments; //a list of comments on THIS post
    //EDIT: Do we even need this array? We can get a list of comments on this post directly from file.

    //overloaded: use this when a new post is created by user
    public Post(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        timestamp = new Date().toString(); //java.util.Date is only used to generate the timestamp
        //for the remainder of its lifespan, a timestamp exists as a string.
    }

    //overloaded: use this when reading from file
    public Post(String author, String title, String content, String timestamp) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        //there's no setTimestamp because timestamp should not be changed
        //trying to keep things simple: use timestamp acts as an ID that helps find comments on the post
        //it's SUPER unlikely that 2 posts are created on the exact same millisecond for a network app of this size
        return timestamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //Display message
    public String toString() {
        String formattedComment = String.format("%s on %s Posted:\n" +
                "%s", author, timestamp.toString(), content);
        return formattedComment;
    }
}