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
     
    // Edit however you like
    public void editComment(Post editor, String content, String newComment) {
        String regex = "(.*)" + content + "(.*)";

        try {
            if (editor.getAuthor().equals(editor.getAuthor())) {
                String edit = content.replaceAll(regex, newComment);
                System.out.println(edit);
            }
        } catch(Exception e) {
            System.out.println("Author not found! Comment wasn't able to edit");
        } finally {
            System.out.println("Try-catch done!");
        }
        }

    public String toString() {
        String output = "";
        output += "Author: " + author + "\n";
        output += "Posted: " + timestamp.toString() + "\n";
        output += ">> " + content + "\n";
        return output;
    }
}
