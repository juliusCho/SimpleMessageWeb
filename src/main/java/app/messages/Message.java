package app.messages;

import java.util.Date;

public class Message {

    public Message(String text) {
        this.text = text;
        createdDate = new Date();
    }

    public Message(Integer id, String text, Date createdDate) {
        this.id = id;
        this.text = text;
        this.createdDate = createdDate;
    }


    private Integer id;

    public Integer getId() {
        return id;
    }

    private String text;


    public String getText() {
        return text;
    }

    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

}
