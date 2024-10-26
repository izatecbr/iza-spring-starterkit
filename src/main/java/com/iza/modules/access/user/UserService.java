package com.iza.modules.access.user;

import com.iza.core.domain.Login;
import com.iza.core.domain.Session;
import com.iza.core.infra.business.InvalidLoginException;
import com.iza.core.infra.security.JwtManager;
import com.iza.modules.access.profile.ProfileEntity;
import com.iza.modules.access.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PasswordEncoder encoder;
    public Session autenticate(Login login){
        UserEntity user = repository.findById(login.getUsername()).orElse(null);
        if(user!=null){
            boolean senhaValida = encoder.matches(login.getPassword(), user.getPassword());
            if (!senhaValida)
                throw new InvalidLoginException();
            Session session = new Session();
            session.setLogin(login.getUsername());
            ProfileEntity profile = profileRepository.findById(user.getProfile()).orElse(null);
            session.setRoles(profile.getRoles());
            LocalDateTime expiration = LocalDateTime.now().plusHours(4);
            String name = user.getLogin();
            String token = JwtManager.create(name, expiration, JwtManager.SECRET_KEY, Map.of(
                    "empresa", 1,
                    "plano", "GOLD"
            ), profile.getRoles());
            session.setToken(token);
            return session;
        } else
            throw new InvalidLoginException();

    }
}
