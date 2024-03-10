package org.example;

public class User {

    private int id;
    private String username;
    private String password;
    private String displayName;
    private boolean isAdmin;

    public User(String username, String password, String displayName, boolean isAdmin) {
        this.id = 0;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.isAdmin = isAdmin;
    }

    public User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
