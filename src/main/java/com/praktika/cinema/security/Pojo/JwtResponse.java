package com.praktika.cinema.security.Pojo;

import com.praktika.cinema.entity.RoleEntity;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type  = "Bearer";
    private Long id;
    private String fullName;
    private String username;
    private List<String> roles;

    public JwtResponse(String token, Long id, String fullName, String username, List<String> roles) {
        this.token = token;
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
