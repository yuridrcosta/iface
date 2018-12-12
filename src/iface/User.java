/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iface;

import java.util.ArrayList;

public class User {
    
    boolean active = true;
    String login;
    private String password;
    String name;
    ArrayList<User> friends = new ArrayList<User>();
    ArrayList<User> invitations = new ArrayList<User>();
    ArrayList<Message> messages = new ArrayList<Message>();
    ArrayList<Message> notRead = new ArrayList<Message>();
    ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    ArrayList<Community> communities = new ArrayList<Community>();
    ArrayList<Community> ownCmm = new ArrayList<Community>();

    public String getLogin() {
        return login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }    

    public boolean isActive() {
        return active;
    }     
}
