public class Test {
    public static void main(String[] args) {

        String password1 = "Sandpaper";
        Account account1 = new Account("Jeff Bezos", password1);
        System.out.println("Password 1 Saved As: " + account1.getPassword());

        String password2 = "Sandpape"; //have 1 less character than password1
        Account account2 = new Account("Jeeff", password2);
        System.out.println("Password 2 Saved As: " + account2.getPassword());

        Post post1 = new Post("test post 1", account1.getUsername(), "CEO Entrepeneur, born in 1964, Jeffery; Jeffery Bezos");
        Post post2 = new Post("test post 2", account2.getUsername(), "This is a test post, and you're reading the post content");
        account1.addPost(post1);
        account1.addPost(post2);

        System.out.println(account1);
    }
}