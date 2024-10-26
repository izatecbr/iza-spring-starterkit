package com.iza.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class Session {
    private String login;
    private String token;
    private List<String> roles;
}
