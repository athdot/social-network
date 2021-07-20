import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * DataManagement - Project 4, Social Network App
 * Handles all data calls, setters and getters. Create an instance of
 * this if you need a profile or anything
 *
 * @author Group 8, C. H. Graham, ...
 * @version July 19, 2021
 */

public class DataManagement {
    //For me to remember whats going on
    //All blocks are String[]
    //Every block is stored in a ArrayList<String[]>
	
    //A few misc test cases
    //public static void main(String[] args) {
        //DataManagement hehe = new DataManagement();
        //Post post1 = new Post("Car","baba","I like cars bruh come on");
        //hehe.setPost(post1);
        //hehe.setPost(new Post("diamonds","baba","MINE DIAM,ONDS"));
        //hehe.setPost(new Post("TEST","baba","hey bro"));
        //post1.addComment(new Comment("babadook","this is a test of comments"));
        //hehe.setPost(post1);
        //ArrayList<Post> g = hehe.getUserPosts("babadook");
        //for (int i = 0; i < g.size(); i++) {
        // 	System.out.println(g.get(i).toString());
        //}
        //hehe.deletePost(post1);
        //g = hehe.getRecentPosts(0,10);
        //for (int i = 0; i < g.size(); i++) {
        //	System.out.println(g.get(i).toString());
        //}
        //=-=-=- Testcase 2
        //Account temp = new Account("Chad",CryptoHash.getHash("thedad"));
        //temp.setBio("Hey boo");
        //hehe.setAccount(temp);
        //Account test1 = new Account("Charles",CryptoHash.getHash("password"));
        //hehe.setAccount(test1);
        //System.out.println(hehe.getAccount("Charles").toString());
        //test1.setBio("Test123");
        //hehe.setAccount(test1);
        //System.out.println(hehe.getAccount("Charles").toString());
        //hehe.deleteAccount("Charles");
    //}
    
    //This method deletes a post in the file system, if it exists
    public void deletePost(Post post) {
    	ArrayList<String[]> postList = readFile("post.csv");
    	postList.remove(getPost(postList, post.getTitle(), post.getAuthor()));
    	writeFile("post.csv", postList);
    }

    //This method can either edit a post, or create a new one
    public void setPost(Post post) {
        //Post file is organized as follows:
        //Post
        //[Title]
        //[User], [Timestamp], [Message]
        //[User], [Timestamp], [Message]
    	ArrayList<String[]> postList = readFile("post.csv");
    	int postExists = getPost(postList, post.getTitle(), post.getAuthor());
    	
    	if (postExists == -1) {
    		postList.add(0, post.toFile());
    	} else {
    		postList.remove(postExists);
    		//Set this index to portExists if we want chats to stay behind
    		postList.add(0, post.toFile());
    	}
    	
        writeFile("post.csv", postList);
    }
    
    //This method is used to get a post's index in the file
    private int getPost(ArrayList<String[]> postList, String title, String author) {
    	
    	for (int i = 0; i < postList.size(); i++) {
    		String[] block = postList.get(i);
    		if (block[1].equals(title) && block[2].split(",")[0].equals(author)) {
    			return i;
    		}
    	}
    	
    	return -1;
    }
    
    //This method turns a post 'block' into a post object, essentially parsing it
    public Post toPost(String[] postInput) {
    	//Creates a post from text
    	String[] content = postInput[2].split(",");
    	String textContent = content[2];
    	for (int i = 3; i < content.length; i++) {
    		textContent += "," + content[i];
    	}
    	Post out = new Post(postInput[1], content[0], textContent);
    	out.setTimeStamp(getDate(content[1]));
    	ArrayList<Comment> comments = new ArrayList<Comment>();
    	
    	for (int i = 3; i < postInput.length; i++) {
    		content = postInput[i].split(",");
        	textContent = content[2];
        	for (int j = 3; j < content.length; j++) {
        		textContent += "," + content[j];
        	}
        	comments.add(new Comment(content[0], textContent, getDate(content[1])));
    	}
    	
    	out.setComments(comments);
    	
    	return out;
    }
    
    //This method gets a toString representaion of a date object, and converts back
    private Date getDate(String input) {
    	try {
			return (Date) (new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(input));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return new Date();
    }
    
    //This method returns a list of the most recent posts from the index
    //onward, excluding some if the actual post count is less than the index
    public ArrayList<Post> getRecentPosts(int startIndex, int endIndex) {
    	ArrayList<String[]> recentPosts = readFile("post.csv");
    	ArrayList<Post> postList = new ArrayList<Post>();
    	
    	for (int i = startIndex; i < endIndex; i++ ) {
    		if (i >= recentPosts.size()) {
    			break;
    		}
    		postList.add(toPost(recentPosts.get(i)));
    	}
    	
    	return postList;
    }
    
    //Gets all posts a user has participated in at all
    public ArrayList<Post> getUserPosts(String user) {
    	ArrayList<String[]> recentPosts = readFile("post.csv");
    	ArrayList<Post> postList = new ArrayList<Post>();
    	
    	for (int i = 0; i < recentPosts.size(); i++) {
    		String[] block = recentPosts.get(i);
    		//Check main author
    		if (block[2].split(",")[0].equals(user)) {
    			postList.add(toPost(block));
    		} else {
    			for (int j = 3; j < block.length; j++) {
    				if (block[j].split(",")[0].equals(user)) {
    	    			postList.add(toPost(block));
    	    			break;
    	    		}
    			}
    		}
    	}
    	
    	return postList;
    }
    
    //This method turns a post 'block' into a post object, essentially parsing it
    public Account toAccount(String[] accountInput) {
    	Account output = new Account(accountInput[1], accountInput[2]);
    	output.setBio(accountInput[3].replace("bio:",""));
    	return output;
    }
    
    public Account getAccount(String accountName) {
    	ArrayList<String[]> profileList = readFile("profile.csv");
    	return toAccount(profileList.get(getAccountIndex(profileList, accountName)));
    }
    
    //This method gets the index in the file of an account
    private int getAccountIndex(ArrayList<String[]> accountList, String account) {
    	
    	for (int i = 0; i < accountList.size(); i++) {
    		String[] block = accountList.get(i);
    		if (block[1].equals(account)) {
    			return i;
    		}
    	}
    	
    	return -1;
    }
    
    //This method can be used to both edit and create a new account
    public void setAccount(Account account) {
    	//An account object block will look like this:
    	//Profile
    	//[Username]
    	//[Password]
    	//[Bio]
    	ArrayList<String[]> profileList = readFile("profile.csv");
    	int postExists = getAccountIndex(profileList, account.getUsername());
    	
    	if (postExists == -1) {
    		profileList.add(0, account.toFile());
    	} else {
    		profileList.remove(postExists);
    		//Set this index to portExists if we want chats to stay behind
    		profileList.add(0, account.toFile());
    	}
    	
        writeFile("profile.csv", profileList);
    }

    //This function deletes an account from the database
    public void deleteAccount(String accountName) {
    	ArrayList<String[]> profileList = readFile("profile.csv");
    	profileList.remove(getAccountIndex(profileList, accountName));
    	writeFile("profile.csv", profileList);
    }
    
    //This method reads a file and takes all object blocks and turns them into a list
    private ArrayList<String[]> readFile(String fileName) {
        //Search an ammount of blocks
        ArrayList<String[]> fileLines = new ArrayList<String[]>();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
            	return fileLines;
            }
            BufferedReader bfr = new BufferedReader(new FileReader(file));
            String currentLine;
            
            ArrayList<String> block = new ArrayList<String>();
            
            while((currentLine = bfr.readLine()) != null){
                //Unsure of if this will work or not
            	if (currentLine.equals("")) {
            		String[] ammt = block.toArray(new String[0]);
            		if (ammt.length > -1) {
            			fileLines.add(ammt);
            			block = new ArrayList<String>();
            		}
            	} else {
            		block.add(currentLine);
            	}
            }
            fileLines.add(block.toArray(new String[0]));
        } catch(Exception e) {
            System.out.println("File Access Failed");
        }

        return fileLines;
    }

    //This method writes a list of object blocks to a file
    private void writeFile(String fileName, ArrayList<String[]> fileLines) {
    	BufferedWriter bfw = null;
    	
        try {
            File file = new File(fileName);
            if (!file.exists()) {
	            file.createNewFile();
	        }
            bfw = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < fileLines.size(); i++) {
            	if (i > 0) {
            		bfw.write("\n");
            	}
            	for (int j = 0; j < fileLines.get(i).length; j++) {
            		bfw.write(fileLines.get(i)[j] + "\n");
            	}
            }
        } catch(Exception e) {
            System.out.println("File Write Failed");
        } finally {
        	try {
        	    if(bfw != null)
        		    bfw.close();
	        } catch(Exception ex){
	            System.out.println("Error in closing the BufferedWriter"+ex);
	        }
        }
    }
}