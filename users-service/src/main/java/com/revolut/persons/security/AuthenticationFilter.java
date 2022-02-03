package com.revolut.persons.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.persons.dto.UserDto;
import com.revolut.persons.model.LoginRequestModel;
import com.revolut.persons.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;

    public AuthenticationFilter(UserService userService, AuthenticationManager manager) {
        super(manager);
        this.userService = userService;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequestModel credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        UserDto userDto = userService.getUserDetailsByEmail(username);
        String token = Jwts.builder()
                .setSubject(userDto.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .signWith(SignatureAlgorithm.HS512, "12fsdaf23fasdfsafgawe45d")
                .compact();
        response.addHeader("token", token);
        response.addHeader("userId", userDto.getUserId());
    }

}
