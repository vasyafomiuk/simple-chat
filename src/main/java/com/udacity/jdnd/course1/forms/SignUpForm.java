package com.udacity.jdnd.course1.forms;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
