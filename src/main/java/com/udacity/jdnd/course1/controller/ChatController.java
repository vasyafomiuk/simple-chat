package com.udacity.jdnd.course1.controller;

import com.udacity.jdnd.course1.forms.ChatForm;
import com.udacity.jdnd.course1.forms.ChatMessage;
import com.udacity.jdnd.course1.model.User;
import com.udacity.jdnd.course1.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Locale;

@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/chat")
    public String getChatHome(@ModelAttribute("chat") ChatForm chat, Model model) {
        model.addAttribute("chat", new ChatForm());
        model.addAttribute("chatMessages", messageService.getMessages());
        return "chat";
    }

    @PostMapping("/chat")
    public String postChat(@ModelAttribute("chat") ChatForm chat, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("chat", new ChatForm());
        if (chat.getType() != null) {
            ChatMessage chatMessage = new ChatMessage();
            String text = chat.getText();
            if (chat.getType().equals("Shout")) {
                text = text.toUpperCase(Locale.ROOT);
            } else if (chat.getType().equals("Whisper")) {
                text = text.toLowerCase(Locale.ROOT);
            }
            chatMessage.setUsername(user.getUsername());
            chatMessage.setMessageText(text);
            messageService.addMessage(chatMessage);
            model.addAttribute("chatMessages", messageService.getMessages());
        }
        return "chat";

    }
}
