package app.messages.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {

    public Message() {};

    public Message(String text) {
        this.text = text;
        createdDate = new Date();
    }

    public Message(Integer id) {
        this.id = id;
        createdDate = new Date();
    }

    public Message(Integer id, String text, Date createdDate) {
        this.id = id;
        this.text = text;
        this.createdDate = createdDate;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "text", nullable = false, length = 128)
    private String text;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

}
