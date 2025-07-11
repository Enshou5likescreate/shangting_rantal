package com.atguigu.lease.common.utils;

import com.atguigu.lease.common.exception.LeaseException;
import com.atguigu.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    //ミリ秒単位なので、ここは1000(一秒)*60（1分）*60（１時間）*24（１日）*30（1ヶ月）になります。
    private static long tokenExpiration = 60 * 60 * 24 * 1000L*30;
    //signの暗号化アルゴリズム
    private static SecretKey secretKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());
    public static String createToken(Long userId, String username){


        String jwt = Jwts.builder().
                setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)).
                setSubject("LOGIN_USER").
                claim("userId", userId).
                claim("username", username).
                signWith(secretKey, SignatureAlgorithm.HS256).
                compact();

        return jwt;
    }

    public static Claims parseToken(String token){
        if (token == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        try {
            JwtParser jwtParser = Jwts.parser().setSigningKey(secretKey).build();
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (ExpiredJwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

    public static void main(String[] args) {
        System.out.println(createToken(5L,"18777804236"));
    }
}
