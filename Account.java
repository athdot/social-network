import java.util.*;

/**
 * Account - CS180 PJ04
 * This is a simple class to manage a user Account
 * @author Charles Graham
 * @version 07/17/2021
 *
 **/

public class Account {
    // Basic login information
    private String username;
    private String password;

    //A short blurb about the user 
    private String bio;
    private Post post;
    private static String[] names = {"carter","language","socrates","love","orange","taper",
            "london","trouble","sandpaper","stables","ocean","river","elephant","juice",
            "varsace","blankets","velvet","castaways","strange","things","cards","beyonce",
            "polar","zeus","create","pool","canvas","feather","titties","milkbox","triangle",
            "redherring","leonardo","deadpool","schoolishard","masterschamber","mindpower",
            "lordofthekings","willofd","goodmorning","testsubject","prickledpear"};

    //array list of comments and posts made by this account
    private ArrayList<Post> posts;
    private ArrayList<Comment> comments;

    private List<Account> accountInfo;
    private String profileFile = "profile.csv";

    
    public Account(String username, String password) {
        //TODO: Check if user exists, and if they do output error?
        this.username = username;
        this.password = password; //will be encrypted from Application.java
        posts = new ArrayList<Post> ();
        bio = ""; //user can set the bio later
        saveAccount();
    }

    public Account(String accountInfo) {
        //TODO: Code later, once we have code to save a Account
	Arrays.asList(getUsername(), getPassword());
        try {
            FileWriter fw = new FileWriter(profileFile);
            for (Account rowValue : this.accountInfo) {
                fw.append(String.join(",", (CharSequence) rowValue));
                fw.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAccount() { //save account to file
        //TODO: Code file mgmt stuff
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String toString() { //Outputs a user Account as a printable string
        String lineBreak = "=-=-=-=-=-=-=-=-=-=-=--=-=";
        String output = lineBreak + "\n\n";
        output += "Username: " + username + "\n\n";
        output += "Bio: " + bio + "\n\n";

        for (int i = 0; i < posts.size(); i++) { //display a list of posts created by the user
            output += posts.get(i).toString() + "\n";
        }

        for (int i = 0; i < posts.size(); i++) { //display a list of comments created by the user
            output += posts.get(i).toString() + "\n";
        }

        output += lineBreak + "\n";
        return output;
    }
    
   //toFile function
    public String[] toFile() {
    	ArrayList<String> output = new ArrayList<String>();
    	output.add("Profile");
    	output.add(username);
    	output.add(password);
    	output.add("bio:" + bio); //We need to have bio: here, if its empty, parse issues
    	return output.toArray(new String[0]);
    }
    
   // Generates a random username or password
    public void computerGenerateName(String select) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        if (select.equals(getUsername())) {
            System.out.println("Select 1 to generate a unique username or" +
                    " select any other number to keep name: ");
        } else if (select.equals(getPassword())) {
            System.out.println("Select 1 to generate a unique password or" +
                    " select any other number to keep name: ");
        }

        int generate = scan.nextInt();
        scan.nextLine();

        try {
            if (generate != 1 && select != "") {
                System.out.println(select);
            } else if (generate == 1) {
                // Formulate a randomized username unique to user
                String characters = "!/$%^&*#@+=";

                // Generate a random integer from 0 - 3000000
                int randomize = rand.nextInt(3000000);

                int randomize2 = rand.nextInt(characters.length());
                int randomize3 = rand.nextInt(characters.length());
                int randomize4 = rand.nextInt(characters.length());

                // Loop through private static names list
                String name = names[(int) (Math.random() * names.length)];

                char randChar = characters.charAt(randomize2);
                char randChar2 = characters.charAt(randomize3);
                char randChar3 = characters.charAt(randomize4);

                // Create unique username
                if (select.equals(getUsername())) {
                    setUsername("user" + randomize + randChar);
                    System.out.println(getUsername());
                    // Create unique password
                } else if (select.equals(getPassword())) {
                    setPassword(name + randomize + randChar);
                    System.out.println(getPassword());
                }
            } else if (generate != 1 && select == "") {
                if (select.equals(getUsername())) {
                    System.out.println("Create new username");
                    select = scan.nextLine();
                    setUsername(select);
                } else if (select.equals(getPassword())) {
                    System.out.println("Create new password");
                    select = scan.nextLine();
                    setPassword(select);
                }
                while (select.contains(" ") || usernameIsTaken) {
                    if (select.contains(" ")) {
                        System.out.println("Usernames Shouldn't Have Spaces");
                        System.out.println("Try Again");
                        select = scan.nextLine();
                        if (select.equals(getUsername())) {
                            setUsername(select);
                        } else if (select.equals(getPassword())) {
                            setPassword(select);
                        }
                    } else if (usernameIsTaken == true) {
                        System.out.println("Username is Taken");
                        System.out.println("Try Again");
                        select = scan.nextLine();
                        if (select.equals(getUsername())) {
                            setUsername(select);
                        } else if (select.equals(getPassword())) {
                            setPassword(select);
                        }
                    }
                } 
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Unable to randomize!");
        }
    }
	
    //TODO: Remove me, for testing
    public String getPassword() {
    	return password;
    }
    
    public boolean correctPassword(String input) {
        return password.equals(input);
    }

/*    //Make sure the password matches through the encryption
    public boolean verifyPass(String input) {
        input = cryptoHashFunction(input);
        return input.equals(password);
    }*/

    //Set user bio, user can set their bio
    public void setBio(String bio) {
        this.bio = bio;
    }

    //Add a post to the account (move this into Application.java, since it's a basic function?)
    public void addPost(Post post) {
        posts.add(post); //newest posts for this account go the back
/*            Date newPostTime = post.getTimeStamp();
            boolean added = false; //if user is adding a post, it would be the most recent???
            for (int i = 0; i < posts.size(); i++) {
                Date timeStamp = posts.get(i).getTimeStamp();
                //Returns an array formatted like this:
                //int[year,day,hour,minute]

                if (newPostTime.compareTo(timeStamp) > 0) {
                    posts.add(i, post);
                    i = posts.size();
                    added = true;
                    continue;
                }
            }

            if (!added) {
                posts.add(post);
            }*/
    }

    public void deletePost(Post post) {
        //thinking about putting this in Application
        //ALSO: addPost, deleteAccount and editPost
    }

    //Set the username
    public void setUsername(String username) {
        this.username = username;
    }

    //Set the password (password should be encrypted in Application.java)
    public void setPassword(String password) {
        this.password = password;
    }
}
