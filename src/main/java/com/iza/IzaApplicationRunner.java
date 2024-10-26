package com.iza;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class IzaApplicationRunner implements ApplicationRunner {
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //System.out.println(encoder.encode("IzaTec@2024"));
        //System.out.println(new String(Base64.getEncoder().encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded())));
    }
}
