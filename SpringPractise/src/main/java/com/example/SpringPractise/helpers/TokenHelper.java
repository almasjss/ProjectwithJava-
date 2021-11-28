package com.example.SpringPractise.helpers;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class TokenHelper {
    public final static long EXPIRATION_TIME = 864_000_000;
    public final static String SECRET = "Key word";

    public static String getToken(String login) {
        return Jwts.builder().setSubject(login)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public static String getLoginByToken(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }
}

