package app.messages;

import app.etc.SecuredPropertySource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MessageRepository {

    private final static Log log = LogFactory.getLog(MessageRepository.class);

    private DataSource dataSource;

    public MessageRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Message saveMessage(Message message) {
        Connection con = DataSourceUtils.getConnection(dataSource);
        try {
            String insertSql = "INSERT INTO MESSAGES (`id`, `text`, `created_date`) VALUES (NULL, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getText());
            ps.setTimestamp(2, new Timestamp(message.getCreatedDate().getTime()));
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return new Message(id, message.getText(), message.getCreatedDate());
                } else {
                    log.error("Failted to retrieve id, No row in result set");
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error("Failed to save message", e);
            try {
                con.close();
            } catch (SQLException se) {
                log.error("Failed to close connection", se);
            }
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
        return null;
    }

}
