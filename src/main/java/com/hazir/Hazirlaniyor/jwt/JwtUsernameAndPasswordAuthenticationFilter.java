package com.hazir.Hazirlaniyor.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.hazir.Hazirlaniyor.core.utillities.jwtResponse.JwtResponse;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessResult;
import com.hazir.Hazirlaniyor.entity.concretes.Product;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.security.Key;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      JwtConfig jwtConfig,
                                                      SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
   @Override
     public void successfulAuthentication(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 FilterChain chain,
                                                 Authentication authResult) throws IOException {
	     java.sql.Date expirationDate = java.sql.Date.valueOf (LocalDate.now ().plusDays (jwtConfig.getTokenExpirationAfterDays ()));
	     String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities()) // the body
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
         response.addHeader(jwtConfig.getAuthorizationHeader(),
                       jwtConfig.getTokenPrefix() + token);
	   response.setContentType("application/json");
	   response.setCharacterEncoding("UTF-8");
	   String[] array = new String[]{token, String.valueOf (expirationDate)};
	   response.getWriter().write(
		//	 
	   "{\"token" +"\":\"" + token+ "\",\"tokenExpiration" +"\":\"" +expirationDate + "\"}"

	   );


   }


    public JwtResponse SuccessToken(String token,Date expirationDate){
    	return new JwtResponse(token,expirationDate);
    }
}
