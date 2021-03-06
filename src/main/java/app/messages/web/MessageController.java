package app.messages.web;

import app.messages.model.Message;
import app.messages.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService service;

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("message", "hello");
        return "welcome";
    }

    @GetMapping("/messages")
    public String index() {
        return "index";
    }

    @GetMapping("/api/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok(service.getMessageList());
    }

    @PostMapping("/api/messages")
    @ResponseBody
    public ResponseEntity<Message> saveMessage(@RequestBody MessageData data) {
        Message saved = service.save(data.getText());
        if (saved == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/api/delete")
    @ResponseBody
    public ResponseEntity<Message> deleteMessage(@RequestParam Integer id) {
        Message message = service.delete(id);
        if (message == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(message);
    }

}
