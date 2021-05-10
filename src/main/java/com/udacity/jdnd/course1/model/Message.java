package com.udacity.jdnd.course1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Message {
    @Id
    @Column(name = "messageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;
    @JoinColumn(name = "username")
    private String username;
    @Column(name = "messagetext", nullable = false)
    private String messageText;
}
