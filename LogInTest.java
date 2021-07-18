public class LogInTest {
    public static void main(String[] args) {
        LogIn log = new LogIn("lennox", "masterschamber", "gio@gmail.com");

        log.confirm("lennox", "porety", "gio@gmail.com");

    }
}

//Output was: "You may proceed!"
