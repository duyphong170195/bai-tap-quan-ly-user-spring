package com.example.baitapquanlyuser.services;

import com.example.baitapquanlyuser.entities.TblUser;
import com.example.baitapquanlyuser.entities.UserRole;
import com.example.baitapquanlyuser.properties.MessageByLocaleService;
import com.example.baitapquanlyuser.repositories.TblUserRepository;
import com.example.baitapquanlyuser.repositorycustom.TblUserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service("tblUserService")
public class TblUserService implements UserDetailsService /*AuthenticationProvider*/ {

    @Autowired
    TblUserRepository tblUserRepository;

    @Autowired
    MessageByLocaleService messageErrorProperties;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        TblUser tblUser = tblUserRepository.findByUserName(username);
        if(tblUser != null) {
            List<GrantedAuthority> authorities =
                    buildUserAuthority(tblUser.getUserRole());
            User user = buildUserForAuthentication(tblUser, authorities);
            return user;
        }
        return null;
    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(TblUser tblUser,
                                            List<GrantedAuthority> authorities) {
        return new User(tblUser.getLoginName(), tblUser.getPassword(),
                tblUser.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<>(setAuths);

        return Result;
    }

//    @Override
//    @Transactional(readOnly=true)
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        Object credentials = authentication.getCredentials();
//        System.out.println("credentials class: " + credentials.getClass());
//        if (!(credentials instanceof String)) {
//            return null;
//        }
//        String password = credentials.toString();
//        if(username.isEmpty() || username == null || password.isEmpty() || password == null) {
//            throw new BadCredentialsException("Authentication failed");
//        }
//        TblUser tblUser = tblUserRepository.findByUserName(username);
//
//        PasswordEncoder passwordEncoder = passwordEncoder();
//        if(tblUser == null || passwordEncoder.matches(password, tblUser.getPassword()) == false ) {
//            throw new BadCredentialsException("username or password are wrong");
//        }
//
//        List<GrantedAuthority> grantedAuthorities = buildUserAuthority(tblUser.getUserRole());
//        Authentication auth = new
//                UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
//        return auth;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//        @Bean
//    public PasswordEncoder passwordEncoder(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }
}
