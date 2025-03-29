package com.myproject.agrolink.config;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class FirebaseAuthenticationFilter extends OncePerRequestFilter {
@Override
protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {

    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader("Authorization");
    
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        String token = authorizationHeader.substring(7);

        try {
        FirebaseToken firebaseToken =
        FirebaseAuth.getInstance().verifyIdToken(token);

        System.out.println(firebaseToken);

        // Extract user information from the claims
        String userId = firebaseToken.getUid();
        String userEmail = firebaseToken.getEmail();
        String userName = firebaseToken.getName();

        // You can also access custom claims if you have defined any
        // String customClaim = (String)
        firebaseToken.getClaims().get("custom_claim_name");

        // Now you can use the extracted user information as needed
        System.out.println("User ID: " + userId);
        System.out.println("User Email: " + userEmail);
        System.out.println("User Name: " + userName);

        response.setStatus(HttpServletResponse.SC_OK);
        filterChain.doFilter(request, response);
        } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
}
