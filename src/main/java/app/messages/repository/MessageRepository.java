package app.messages.repository;

import app.messages.model.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageRepository {

    private SessionFactory sessionFactory;

    public MessageRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Message> getMessageList() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Message";
        Query<Message> query = session.createQuery(hql, Message.class);
        return query.list();
    }

    public Message saveMessage(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.save(message);
        return message;
    }

    public Message deleteMessage(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(message);
        return message;
    }

}
