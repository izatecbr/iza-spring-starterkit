package com.iza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class IzaApplicationRunner implements ApplicationRunner {
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //System.out.println(encoder.encode("IzaTec@2024"));
    }
}
