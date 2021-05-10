package com.udacity.jdnd.course1.forms;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessage {
    private String messageText;
    private String username;
}
