package com.udacity.jdnd.course1.service;

import com.udacity.jdnd.course1.controller.ChatController;
import com.udacity.jdnd.course1.forms.ChatMessage;
import com.udacity.jdnd.course1.mapper.MessageMapper;
import com.udacity.jdnd.course1.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public void addMessage(ChatMessage chatMessage) {
        Message message = new Message();
        message.setMessageText(chatMessage.getMessageText());
        message.setUsername(chatMessage.getUsername());
        messageMapper.insertMessage(message);
    }

    public List<ChatMessage> getMessages() {
        List<ChatMessage> chatMessages = new ArrayList<>();
        for (Message message : messageMapper.getMessages()) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setUsername(message.getUsername());
            chatMessage.setMessageText(message.getMessageText());
            chatMessage.setDatetime(message.getDatetime());
            chatMessages.add(chatMessage);
        }
        return chatMessages;
    }
}
