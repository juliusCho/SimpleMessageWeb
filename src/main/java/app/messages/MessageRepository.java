package app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageRepository {

    private final static Log log = LogFactory.getLog(MessageRepository.class);

//    private DataSource dataSource;
//
//    public MessageRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public Message simpleJavaJDBCSaveMessage(Message message) {
//        Connection con = DataSourceUtils.getConnection(dataSource);
//        try {
//            String insertSql = "INSERT INTO MESSAGES (`id`, `text`, `created_date`) VALUES (NULL, ?, ?)";
//            PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, message.getText());
//            ps.setTimestamp(2, new Timestamp(message.getCreatedDate().getTime()));
//            int rowsAffected = ps.executeUpdate();
//
//            if (rowsAffected > 0) {
//                ResultSet rs = ps.getGeneratedKeys();
//                if (rs.next()) {
//                    int id = rs.getInt(1);
//                    return new Message(id, message.getText(), message.getCreatedDate());
//                } else {
//                    log.error("Failted to retrieve id, No row in result set");
//                    return null;
//                }
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            log.error("Failed to save message", e);
//            try {
//                con.close();
//            } catch (SQLException se) {
//                log.error("Failed to close connection", se);
//            }
//        } finally {
//            DataSourceUtils.releaseConnection(con, dataSource);
//        }
//        return null;
//    }






//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//    }
//
//    public Message springJdbcSaveMessage(Message message) {
//        GeneratedKeyHolder holder = new GeneratedKeyHolder();
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("text", message.getText());
//        params.addValue("createdDate", message.getCreatedDate());
//        String insertSQL = "INSERT INTO MESSAGES (`id`, `text`, `created_date`) VALUES (NULL, :text, :createdDate)";
//        try {
//            this.namedParameterJdbcTemplate.update(insertSQL, params, holder);
//        } catch (DataAccessException e) {
//            log.error("Failed to save message", e);
//            return null;
//        }
//        return new Message(holder.getKey().intValue(), message.getText(), message.getCreatedDate());
//    }








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
