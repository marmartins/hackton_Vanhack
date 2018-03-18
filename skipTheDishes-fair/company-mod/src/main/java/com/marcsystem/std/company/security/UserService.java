package com.marcsystem.std.company.security;

import com.marcsystem.std.company.model.AppUser;
import com.marcsystem.std.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service("userService")
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {

        AppUser appUser = userRepository.findByUserName(userName);
        if (isNull(appUser)) throw new UsernameNotFoundException(userName);
        else {
            return new User(appUser.getUserName(), appUser.getPwd(), true, true, true, true, getGrantedAuthorities(appUser));
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(AppUser appUser) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(appUser.getRoles()));
        return authorities;
    }
}
