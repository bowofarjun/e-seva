package in.ac.bitspilani.wilp.esevaapi.access;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class EsevaJWTTokenProvider {

    @Value("${security.jwt.token.secret-key:eseva-jwt-secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:900000}")
    private long validityInMilliseconds = 900000; // 15 minutes expiry

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Autowired
    private EsevaUserDetails esevaUserDetails;

    public String createToken(String userId, String roleName, String sessionId) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("auth", roleName);
        claims.put("session-id", sessionId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    public Authentication getAuthentication(String token) {

        String userId=getUserId(token);
        String roleName=getSessionId(token);
        String sessionId=getRoleName(token);

        String finalToken = userId+":"+roleName+":"+sessionId;
        UserDetails userDetails = esevaUserDetails.loadUserByUsername(finalToken);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getSessionId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("session-id").toString();
    }

    public String getRoleName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("auth").toString();
    }

    public String resolveToken(HttpServletRequest req) {
        Optional<String> bearerToken;
        try {
            bearerToken = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equalsIgnoreCase("Authorization")).map(cookie -> cookie.getValue()).findFirst();
        }
        catch(Exception ex)
        {
            return null;
        }

        if (bearerToken != null && bearerToken.get()!=null) {
            return bearerToken.get();
        }
        return null;
    }

    public boolean validateToken(String token) {

        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return true;

    }
}
