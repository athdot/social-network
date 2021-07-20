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
    
    public void editComment(Post editor, String author, String newContent) {
        //
        String regex = "(.*)" + editor.getContent() + "(.*)";

        try {
            if (editor.getAuthor().equals(author)) {
                String editComment = editor.getContent().replaceAll(regex, newContent);
                editor.setContent(editComment);
                System.out.println(editor.getContent());
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Author not found! Comment wasn't able to edit");
        } finally {
            System.out.print("");
        }
        }

    public void editAuthor(Post editor, String author, String newAuthor) {
        // Encases old author name to be replaced with the new author
        String regex = "(.*)" + editor.getAuthor() + "(.*)";

        try {
            // Makes sure that only author can edit
            if (editor.getAuthor().equals(author)) {
                // Replace original author name with the new author name;
                String editAuthor = editor.getAuthor().replaceAll(regex, newAuthor);
                editor.setAuthor(editAuthor);
                System.out.println(editor.getAuthor());
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Author not found! Author wasn't able to edit");
        } finally {
            System.out.print("");
        }
    }
    

    public void editTitle(Post editor, String author, String newTitle) {
        // Encases old title to be replaced with the new title
        String regex = "(.*)" + editor.getTitle() + "(.*)";

        try {
            // Makes sure that only author can edit
            if (editor.getAuthor().equals(author)) {
                // Replaces old title with new title
                String editTitle = editor.getTitle().replaceAll(regex, newTitle);
                editor.setTitle(editTitle);
                System.out.println(editor.getTitle());
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Author not found! Title wasn't able to edit");
        } finally {
            System.out.print("");
        }
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
