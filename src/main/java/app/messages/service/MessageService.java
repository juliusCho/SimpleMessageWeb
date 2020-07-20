package app.messages.service;

import app.messages.repository.MessageRepository;
import app.messages.model.Message;
import app.messages.security.SecurityCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    private MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Message> getMessageList() {
        return repository.getMessageList();
    }

    @SecurityCheck
    @Transactional
    public Message save(String text) {
//        Message message = repository.saveMessage(new Message(text));
//        log.debug("New message[id={}] saved", message.getId());
//        this.updateStatistics();
        return repository.saveMessage(new Message(text));
    }

    private void updatceStatistics() {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }

    @SecurityCheck
    @Transactional
    public Message delete(Integer id) {
        return repository.deleteMessage(new Message(id));
    }

}
