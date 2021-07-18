public class BackUpStuff {

    /**
     * back up 1: was thinking about how to sync file across multiple computers for project 5, but network IO will
     * probably have its own program and deliver up-to-date data from server to client. Below is code that was going to
     * store all
     */
/*
    private static String accountInfoFilename = "accountInfo.csv";
    private static String postInfoFilename = "postInfo.csv";
    private static String commentInfoFilename = "commentInfo.csv";

    //static arraylist variables for use across all instances of Application class
    //create lists containing information from each file for easier manipulation
    private static ArrayList<String> accountInfo = new ArrayList<>(); //store each line from AccountInfo.csv
    private static ArrayList<String> postInfo = new ArrayList<>(); //store each line from PostInfo.csv
    private static ArrayList<String> commentInfo = new ArrayList<>(); //store each line from CommentInfo.csv

    public void getInfo() { //retrieve information for Accounts, Posts, and Comments

        try {
            //if the files do not already exist, create them on user's computer
            //if they do exist, no harm done by opening in append mode and doing nothing
            File accountF = new File(accountInfoFilename);
            FileOutputStream accountFos = new FileOutputStream(accountF, true);
            accountFos.close();

            File postF = new File(postInfoFilename);
            FileOutputStream postFos = new FileOutputStream(postF, true);
            postFos.close();

            File commentF = new File(commentInfoFilename);
            FileOutputStream commentFos = new FileOutputStream(commentF, true);
            commentFos.close();

            //begin reading from each file, if there was nothing in them, respective ArrayList fields are empty
            //project 5 problem: how to ensure new computer has up-to-date data when data is decentralized?
            FileReader accountFr = new FileReader(accountF);
            BufferedReader accountBfr = new BufferedReader(accountFr);
            while (true) {
                String line = accountBfr.readLine();
                if (line == null)
                    break;
                accountInfo.add(line);
            }

            FileReader postFr = new FileReader(postF);
            BufferedReader postBfr = new BufferedReader(postFr);
            while (true) {
                String line = postBfr.readLine();
                if (line == null)
                    break;
                postInfo.add(line);
            }

            FileReader commentFr = new FileReader(commentF);
            BufferedReader commentBfr = new BufferedReader(commentFr);
            while (true) {
                String line = commentBfr.readLine();
                if (line == null)
                    break;
                commentInfo.add(line);
            }

        } catch (IOException ioException) { //program must not crash under any circumstances
            //this block should theoretically never be reached
            System.out.println(fileErrorMessage);
        }
    }
*/

    /**
     * paste next back up below, write documentation here
     */

}
