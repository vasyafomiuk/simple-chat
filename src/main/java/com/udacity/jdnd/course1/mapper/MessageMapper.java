package com.udacity.jdnd.course1.mapper;

import com.udacity.jdnd.course1.forms.ChatMessage;
import com.udacity.jdnd.course1.model.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("SELECT * FROM messages WHERE messageid = ${messageId}")
    Message getMessage(int messageId);

    @Select("SELECT * FROM messages")
    List<Message> getMessages();

    @Insert("INSERT INTO messages (username, messagetext, datetime) " +
            "VALUES ('${username}', '${messageText}', current_timestamp)")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insertMessage(Message message);

    @Delete("DELETE FROM messages WHERE messageid = ${messageId}")
    void deleteMessage(int messageId);
}
