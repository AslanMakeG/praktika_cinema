package com.praktika.cinema.service;

import com.praktika.cinema.entity.RoleEntity;
import com.praktika.cinema.entity.UserEntity;
import com.praktika.cinema.enums.ERole;
import com.praktika.cinema.exception.user.RoleNotFoundException;
import com.praktika.cinema.exception.user.UserAlreadyExistsException;
import com.praktika.cinema.exception.user.UserNotFoundException;
import com.praktika.cinema.repository.RoleRepo;
import com.praktika.cinema.repository.UserRepo;
import com.praktika.cinema.security.JwtUtils;
import com.praktika.cinema.security.Pojo.JwtResponse;
import com.praktika.cinema.security.Pojo.SingupRequest;
import com.praktika.cinema.security.Pojo.LoginRequest;
import com.praktika.cinema.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public UserEntity getUserByUsername(String username) throws UserNotFoundException {
        UserEntity user = userRepo.findByUsername(username).orElseThrow();
        if(user == null){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return user;
    }

    public UserEntity create(SingupRequest singupRequest) throws UserAlreadyExistsException, RoleNotFoundException { //изменить пароль на зашифрованный
        if(userRepo.existsByUsername(singupRequest.getUsername())){
            throw new UserAlreadyExistsException("Пользователь с таким никнеймом уже существует");
        }

        UserEntity user = new UserEntity();
        user.setUsername(singupRequest.getUsername());
        user.setFullName(singupRequest.getFullName());
        user.setPassword(passwordEncoder.encode(singupRequest.getPassword()));

        Set<String> reqRoles = singupRequest.getRoles();
        Set<RoleEntity> roles = new HashSet<>();

        if(reqRoles == null){
            RoleEntity userRole = roleRepo.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException("Роль USER не найдена"));
            roles.add(userRole);
        }
        else{
            List<String> reqRolesList = reqRoles.stream().toList();
            for (String s : reqRolesList) {
                RoleEntity userRole;
                switch (s) {
                    case "user":
                        userRole = roleRepo.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RoleNotFoundException("Роль USER не найдена"));
                        roles.add(userRole);
                        break;
                    case "admin":
                        userRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RoleNotFoundException("Роль ADMIN не найдена"));
                        roles.add(userRole);
                        break;
                    case "moderator":
                        userRole = roleRepo.findByName(ERole.ROLE_MOD)
                                .orElseThrow(() -> new RoleNotFoundException("Роль MODERATOR не найдена"));
                        roles.add(userRole);
                        break;
                    default:
                        userRole = roleRepo.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RoleNotFoundException("Роль USER не найдена"));
                        roles.add(userRole);
                        break;
                }
            }
        }

        user.setRoles(roles);
        return userRepo.save(user);
    }
    public JwtResponse auth(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).toList();


        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFullName(),
                roles);
    }
}
