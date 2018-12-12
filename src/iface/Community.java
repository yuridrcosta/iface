
package iface;

import java.util.ArrayList;

public class Community {
    
    String name;
    ArrayList<User> adms = new ArrayList<User>();
    ArrayList<User> members = new ArrayList<User>();
    ArrayList<Message> messages = new ArrayList<Message>();

    public Community(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

   
    
    
}
