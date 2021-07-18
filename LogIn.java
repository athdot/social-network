public class LogIn {
    private String password;
    private String username;
    private String email;
    private boolean valid;

    public LogIn(String password, String username, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.valid = isValid();
    }

    public boolean isValid() {
        return true;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Checks if password and username/email entered matches the account's password and
    // username/email
    // Wasn't sure if I should throw an exception here 
    public void confirm(String newPassword, String newUser, String newEmail) {
        if (password.equals(newPassword) || username.equals(newUser) ||
                email.equals(newEmail)) {
            isValid();
        } else {
            valid = false;
        }
    }

}
