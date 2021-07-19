import java.util.Date;

/**
 * Application - Project 4, Social Network App
 * Handles overall functionality of application, brings together Account, Comment, and Post
 *
 * @author Group 8, N. Yao, ...
 * @version July 17, 2021
 */

public class Comment {

    private Post author; //author who made the comment
    private String content; //content of the comment
    private Date timestamp; //timestamp of comment

    public Comment(Post author, String content) {
        this.author = author;
        this.content = content;
        timestamp = new Date();
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    // Edit however you like
    public void editComment(Post editor, String author, String newContent) {
        String regex = "(.*)" + editor.getContent() + "(.*)";

        try {
            if (editor.getAuthor().equals(author)) {
                String editComment = editor.getContent().replaceAll(regex, newContent);
                editor.setContent(editComment);
                System.out.println(editor.getContent());;
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Author not found! Comment wasn't able to edit");
        } finally {
            System.out.print("");
        }
        }
    // Edit however you like
    public void editTitle(Post editor, String author, String newTitle) {
        String regex = "(.*)" + editor.getTitle() + "(.*)";

        try {
            if (editor.getAuthor().equals(author)) {
                String editTitle = editor.getTitle().replaceAll(regex, newTitle);
                editor.setTitle(editTitle);
                System.out.println(editor.getTitle());
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Title not found! Title wasn't able to edit");
        } finally {
            System.out.print("");
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
