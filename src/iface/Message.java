
package iface;

public class Message {
    User sender;
    String msg;

    public Message(User sender, String msg) {
        this.sender = sender;
        this.msg = msg;
    }

    public Message() {
    }

    public String getMsg() {
        return msg;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }  
}
