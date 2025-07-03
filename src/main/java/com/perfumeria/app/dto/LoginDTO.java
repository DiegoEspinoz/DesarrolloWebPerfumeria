package com.perfumeria.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String nickname;
    private String password;
}
