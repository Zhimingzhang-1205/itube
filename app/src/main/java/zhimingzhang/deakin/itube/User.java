package zhimingzhang.deakin.itube;

import java.util.ArrayList;

public class User{

    private int userID;
    private String username;
    private String password;
    private ArrayList<String> playlist;

    public User(String username, String password, ArrayList<String> playlist) {
        this.username = username;
        this.password = password;
        this.playlist = playlist;

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

    public ArrayList<String> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<String> playlist) {
        this.playlist = playlist;
    }

}
