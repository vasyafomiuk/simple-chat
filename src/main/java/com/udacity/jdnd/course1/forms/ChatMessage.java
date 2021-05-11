package com.udacity.jdnd.course1.forms;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessage {
    private String messageText;
    private String username;
    private Timestamp datetime;
}
