import java.util.Date; 

/**
 * Post - CS180 PJ04
 *
 * A class representative of a social network post
 *
 * @author C. H. Graham
 *
 * @version 07/17/2021
 *
 **/

public class Post {
    private String title;
    private String author;
    private String content;
    private Date timeStamp;
    //private ArrayList<Message> lowerMessages //For later

    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
        timeStamp = new Date();
    }

    //return author of post
    public String getAuthor() { return author; }

    //Return the time the message was sent
    public Date getTimeStamp() {
        return timeStamp;
    }

    //Get content
    public String getContent() {
        return content;
    }

    //Get title
    public String getTitle() {
        return title;
    }

    //change title
    public void setTitle(String title) { this.title = title; }

    //change content
    public void setContent(String content) { this.content = content; }

    //Display message
    public String toString() {
        String output = "";
        output += "-- " + title + " --\n";
        output += "Author: " + author + "\n";
        output += "Posted: " + timeStamp.toString() + "\n";
        output += ">> " + content + "\n";
        return output;
    }
}