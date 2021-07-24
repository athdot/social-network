import java.io.*;
import java.util.ArrayList;

/**
 * Data.java
 * Will handle all methods regarding writing and reading from files
 * Reading/writing methods can be referenced from here. EXPERIMENTING WITH 3 FILE SYSTEM
 *
 * @author N. Yao (experimental)
 * @version July 19, 2021
 */
public class Data {

    //all methods within Function.java must use these fields to get and set
    public static ArrayList<Account> accountArrayList; //fields: username, password, bio
    public static ArrayList<Post> postArrayList; //fields: title author content timestamp comments
    public static ArrayList<Comment> commentArrayList; //fields: author, content, timestamp

    private static final String accountInfoFilename = "accountInfo.csv"; //filenames
    private static final String postInfoFilename = "postInfo.csv";
    private static final String commentInfoFilename = "commentInfo.csv";

    private static final String fileExceptionError = "Error Using File, Try Again"; //notify if something goes wrong
    private static final String makeshiftComma = "!@#"; //this code will translate to a comma

    //because we are working with CSV files, only the commas separating each cell should exist
    private String addFormatting(String normal) { //writing to file: replace all commas with makeshiftComma "!@#"
        if (normal == null)
            return "";
        return normal.replace(",", makeshiftComma);
    }

    private String removeFormatting(String formatted) { //reading from file: replace all makeshiftComma "!@#" with ","
        if (formatted == null)
            return "";
        return formatted.replace(makeshiftComma, ",");
    }

    /**
     * idea: we have 6 primary methods: 2 methods for each of the 3 files
     * 1. a read method that creates an ArrayList copy of everything from the file
     * 2. a write method that completely overwrites the file with new information
     * Essentially, it's like a getter-setter for a file
     *
     * NOTE: one file being manipulated may require other 2 files also to be rewritten. E.g., If an account changes
     * usernames, we will have to change username across all files.
     *
     * FILE SYSTEM
     * A line in accountInfo.csv would be: "usernameHere,passwordHere,bioHere"
     * A line in postInfo.csv would be: "author/username,title,contentText,timestamp,"
     * A line in commentInfo.csv would be: "author/username,contentText,commentTimestamp,postTimestamp"
     */

    /**
     * Read the current contents of account file into accountArrayList.
     * A line in accountInfo.csv would be: "usernameHere,passwordHere,bioHere".
     */
    public static void getAccountInfo() {
        Data data = new Data();

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
                accountArrayList.add(new Account(info[0], data.removeFormatting(info[1]),
                        data.removeFormatting(info[2])));
                //idea: password and bio may have had commas. Because we are using CSV, commas that are part of the
                //password and bio (username has no commas) will have been replaced with "!@#". See removeFormatting
                //and addFormatting methods above
            }
        } catch (IOException ioException) {
            //this block should never be reached
            System.out.println(fileExceptionError);
        }
    }

    /**
     * Write the current contents of accountArrayList into the account file.
     * A line in accountInfo.csv would be: "usernameHere,passwordHere,bioHere".
     */
    public static void setAccountInfo() {
        Data data = new Data();

        try {
            File accountF = new File(accountInfoFilename);
            FileOutputStream accountFos = new FileOutputStream(accountF); //no append mode, overwrite current file
            PrintWriter accountPw = new PrintWriter(accountFos);

            for (Account current : accountArrayList) {
                //replace commas with makeshift comma using addFormatting. Password and bio may have commas
                accountPw.printf("%s,%s,%s\n", current.getUsername(), data.addFormatting(current.getPassword()),
                        data.addFormatting(current.getBio()));
            }

        } catch (IOException ioException) {
            //this block should never be reached
            System.out.println(fileExceptionError);
        }
    }

    /**
     * Read the current contents of post file into postArrayList.
     * A line in postInfo.csv would be: "author/username,title,contentText,timestamp,"
     */
    public static void getPostInfo() {
        Data data = new Data();

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
                postArrayList.add(new Post(info[0], data.removeFormatting(info[1]),
                        data.removeFormatting(info[2]), info[3]));
            }
        } catch (IOException ioException) {
            //this block should never be reached
            System.out.println(fileExceptionError);
        }
    }

    /**
     * Write the current contents of postArrayList into the post file.
     * A line in postInfo.csv would be: "author/username,title,contentText,timestamp,"
     */
    public static void setPostInfo() {
        Data data = new Data();

        try {
            File postF = new File(postInfoFilename);
            FileOutputStream postFos = new FileOutputStream(postF); //no append mode, overwrite current file
            PrintWriter postPw = new PrintWriter(postFos);

            for (Post current : postArrayList) {
                //replace commas with makeshift comma using addFormatting. title and content may have commas
                postPw.printf("%s,%s,%s,%s\n", current.getAuthor(), data.addFormatting(current.getTitle()),
                        data.addFormatting(current.getContent()), current.getTimestamp());
            }

        } catch (IOException ioException) {
            //this block should never be reached
            System.out.println(fileExceptionError);
        }
    }

    /**
     * Read the current contents of comment file into commentArrayList.
     * A line in commentInfo.csv would be: "author/username,contentText,commentTimestamp,postTimestamp"
     */
    public static void getCommentInfo() {
        Data data = new Data();

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
                commentArrayList.add(new Comment(info[0], data.removeFormatting(info[1]), info[2], info[3]));
            }
        } catch (IOException ioException) {
            //this block should never be reached
            System.out.println(fileExceptionError);
        }
    }

    /**
     * Write the current contents of commentArrayList into the comment file.
     * A line in commentInfo.csv would be: "author/username,contentText,commentTimestamp,postTimestamp"
     */
    public static void setCommentInfo() {
        Data data = new Data();

        try {
            File commentF = new File(commentInfoFilename);
            FileOutputStream commentFos = new FileOutputStream(commentF); //no append mode, overwrite current file
            PrintWriter commentPw = new PrintWriter(commentFos);

            for (Comment current : commentArrayList) {
                //replace commas with makeshift comma using addFormatting. content may have commas
                commentPw.printf("%s,%s,%s,%s\n", current.getAuthor(), data.addFormatting(current.getContent()),
                        current.getCommentTimestamp(), current.getPostTimestamp());
            }

        } catch (IOException ioException) {
            //this block should never be reached
            System.out.println(fileExceptionError);
        }
    }

    /**
     * Run this method when the server begins. NOTE: only run once, to instantiate the accountArrayList, postArrayList,
     * and commentArrayList. REASON: to get the saved app data from the files
     */
    public void start() {
        getAccountInfo();
        getPostInfo();
        getCommentInfo();
    }

    /**
     * Run this method when the server stops (when will server stop?) to write newly updated information to the files
     * before the program ends. REASON: to save new data. IDEA: start() -> while (true) { open socket end() } on a
     * separate thread. Another thread is ready for user console input and will take in
     */
    public void end() {
        setAccountInfo();
        setPostInfo();
        setCommentInfo();
    }
}
