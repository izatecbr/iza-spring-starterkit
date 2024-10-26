package com.iza.modules.access.auth;

import com.iza.core.domain.Login;
import com.iza.core.infra.http.Response;
import com.iza.core.infra.http.ResponseFactory;
import com.iza.modules.access.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthWebservice {
    @Autowired
    private UserService service;
    @PostMapping("login")
    public Response login(@RequestBody Login login){
        return ResponseFactory.ok(service.autenticate(login),"Login realizado com sucesso!");
    }
}
