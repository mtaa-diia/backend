package com.doklad.api.config;

import com.doklad.api.customers.services.MyUserDetailService;
import com.doklad.api.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final MyUserDetailService userDetailService;


    public JWTFilter() {
        this.jwtUtil = null;
        this.userDetailService = null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                String authorizationHeader = request.getHeader("Authorization");
                StringBuilder token = null;
                String username = null;

                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    token = new StringBuilder(authorizationHeader.substring(7));
                    username = jwtUtil.validateToken(token.toString());
                    System.out.println(token);

                    if (token.isEmpty())
                        response.sendError(401, "Token is empty");
                    else {
                        try {
                            UserDetails userDetails = userDetailService.loadUserByUsername(username);

                            UsernamePasswordAuthenticationToken authenticationToken =
                                    new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                            if( SecurityContextHolder.getContext().getAuthentication() == null)
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                        } catch (Exception e) {
                            response.sendError(401, "Token is invalid");
                        }
                    }
                }else
                    System.out.println("No token found");

                filterChain.doFilter(request, response);
    }

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, MyUserDetailService userDetailService) {
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }

}
