package com.example.baitapquanlyuser.services;

import com.example.baitapquanlyuser.entities.TblUser;
import com.example.baitapquanlyuser.entities.UserRole;
import com.example.baitapquanlyuser.repositories.TblUserRepository;
import com.example.baitapquanlyuser.repositorycustom.TblUserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
import java.util.Set;

@Service
public class TblUserService {

    @Autowired
    TblUserRepository tblUserRepository;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

       TblUser tblUser = tblUserRepository.findByUserName(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(tblUser.getUserRole());

        return buildUserForAuthentication(tblUser, authorities);

    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(TblUser tblUser,
                                            List<GrantedAuthority> authorities) {
        return new User(tblUser.getLoginName(), tblUser.getPassword(),
                true, true, true, true, authorities);
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

}
