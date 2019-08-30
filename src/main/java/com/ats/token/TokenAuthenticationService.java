package com.ats.token;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
 
@Service
@Transactional
public class TokenAuthenticationService {
     
     final long EXPIRATIONTIME = 864_000_000; // 10 days
     
     final String SECRET = "ThisIsASecret";
 
    public  String addAuthentication(String username) {
    	System.out.println("addAuthentication username : " + username);
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
       
        System.out.println("addAuthentication JWT String : " + JWT);
        return JWT;
    }
 
     
}
