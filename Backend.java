import java.io.*;
import java.util.ArrayList;

/**
 * Will handle all methods regarding writing and reading from files
 * Reading/writing methods can be referenced from here. Testing the 2 filesystem
 *
 * @author N. Yao (experimental)
 * @version July 19, 2021
 */
public class Backend {

    private static ArrayList<Account> accountArrayList; //fields: username, password, bio
    private static ArrayList<Post> postArrayList; //fields: title author content timestamp comments
    private static ArrayList<Comment> commentArrayList; //fields: author, content, timestamp

    private static final String accountInfoFilename = "accountInfo.csv"; //filenames
    private static final String postInfoFilename = "postInfo.csv";
    private static final String commentInfoFilename = "commentInfo.csv";

    private static final String fileExceptionError = "Error Opening File, Try Again"; //notify if something goes wrong
    private static final String makeshiftComma = "!@#"; //this code will translate to a comma

    //because we are working with CSV files, only the commas separating each cell should exist
    public String addFormatting(String normal) { //writing to file: replace all commas with makeshiftComma "!@#"
        String formatted = normal.replace(",", makeshiftComma);
        return formatted;
    }

    public String removeFormatting(String formatted) { //reading from file: replace all makeshiftComma "!@#" with ","
        String normal = formatted.replace(makeshiftComma, ",");
        return normal;
    }

    /**
     * idea: we have 6 primary methods: 2 methods for each of the 3 files
     * 1. a read method that creates an ArrayList copy of everything from the file
     * 2. a write method that completely overwrites the file with new information
     * Essentially, it's like a getter-setter for a file
     *
     * NOTE: one file being manipulated may require other 2 files also to be rewritten. E.g., If an account changes
     * usernames, we will have to change username across all files.
     * @return
     */
    public ArrayList<Account> getAccountInfo() {
        //a line in accountInfo.csv would be: "usernameHere,passwordHere,bioHere"

        try {
            //if the files do not already exist, create them on user's computer
            //if they do exist, no harm done by opening in append mode and doing nothing
            File accountF = new File(accountInfoFilename);
            FileOutputStream accountFos = new FileOutputStream(accountF, true);
            accountFos.close();

            //begin reading from each file, if there was nothing in them, respective ArrayList fields are empty
            FileReader accountFr = new FileReader(accountF);
            BufferedReader accountBfr = new BufferedReader(accountFr);
            while (true) {
                String line = accountBfr.readLine();
                if (line == null)
                    break;
                String[] info = line.split(",");
                //todo: make sure username has no commas (or special characters?)
                accountArrayList.add(new Account(info[0], removeFormatting(info[1]), removeFormatting(info[2])));
                //idea: since it's hard to store Comment and Post array onto file, create external method that matches
                //all available posts and comments with author (username), and initialize afterwards that way
            }
        } catch (IOException ioException) {
            //this block should never be reached
            System.out.println(fileExceptionError);
        }
        return accountArrayList;
    }

    public ArrayList<Post> getPostInfo() {
        //a line in postInfo.csv would be: "author/username,title,contentText,timestamp,"

        try {
            File postF = new File(postInfoFilename);
            FileOutputStream postFos = new FileOutputStream(postF, true);
            postFos.close();

            FileReader postFr = new FileReader(postF);
            BufferedReader postBfr = new BufferedReader(postFr);
            while (true) {
                String line = postBfr.readLine();
                if (line == null)
                    break;
                String[] info = line.split(",");
                postArrayList.add(new Post(info[0], removeFormatting(info[1]), removeFormatting(info[2]), info[3]));
            }
        } catch (IOException ioException) {
            //this block should never be reached
            System.out.println(fileExceptionError);
        }
        return postArrayList;
    }

    public ArrayList<Comment> getCommentInfo() {
        //a line in commentInfo.csv would be: "author/username,contentText,commentTimestamp,postTimestamp"

        try {
            File commentF = new File(commentInfoFilename);
            FileOutputStream commentFos = new FileOutputStream(commentF, true);
            commentFos.close();

            FileReader commentFr = new FileReader(commentF);
            BufferedReader commentBfr = new BufferedReader(commentFr);
            while (true) {
                String line = commentBfr.readLine();
                if (line == null)
                    break;
                String[] info = line.split(",");
                commentArrayList.add(new Comment(info[0], removeFormatting(info[1]), info[2], info[3]));
            }
        } catch (IOException ioException) {
            //this block should never be reached
            System.out.println(fileExceptionError);
        }
        return commentArrayList;
    }

    public void setAccountInfo() {
    }

    public void setPostInfo() {
    }

    public void setCommentInfo() {
    }
}
