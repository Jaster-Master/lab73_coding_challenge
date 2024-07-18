package net.zecher.backend.service;

import net.zecher.backend.dto.AuthDto;
import net.zecher.backend.model.User;
import net.zecher.backend.repo.UserRepo;
import net.zecher.backend.security.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(AuthDto authDto) {
        var salt = BCrypt.gensalt();
        var hash = BCrypt.hashpw(authDto.getPassword(), salt);
        var user = new User();
        user.setUserName(authDto.getUserName());
        user.setPasswordHash(hash);
        user.setSalt(salt);
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByUserName(username);
        return new UserInfo(user);
    }
}
