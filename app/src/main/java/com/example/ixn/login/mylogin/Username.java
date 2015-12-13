package com.example.ixn.login.mylogin;

/**
 * Created by dev-team-ixn on 12/13/15.
 */
public class Username {
    private String username;
    private String password;

    public Username() {
        // TODO Auto-generated constructor stub
    }

    public Username (String username, String password){
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
