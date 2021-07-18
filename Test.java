public class Test {
    public static void main(String[] args) {
        String password = "Sandpaper";
        Account test = new Account("Jeff Bezos",password);
        System.out.println("Password Saved As: " + test.getPassword());
        Account test11 = new Account("Jeeff", "Sandpape");
        System.out.println("2nd test with 1 less char" + test11.getPassword());
        Post test1 = new Post("CEO", test.getUsername(), "CEO Entrepeneur, born in 1964, Jeffery; Jeffery Bezos");
        Post test2 = new Post("Test123", test.getUsername(), "This is a test");
        test.addPost(password, test1);
        test.addPost(password, test2);
        System.out.println(test.toString());
    }
}