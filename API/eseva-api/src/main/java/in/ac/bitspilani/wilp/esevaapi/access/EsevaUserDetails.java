package in.ac.bitspilani.wilp.esevaapi.access;

import com.microsoft.sqlserver.jdbc.StringUtils;
import in.ac.bitspilani.wilp.esevaapi.repository.SessionRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static in.ac.bitspilani.wilp.esevaapi.util.EsevaUtil.validateNullStringInMap;

@Service
@RequiredArgsConstructor
public class EsevaUserDetails implements UserDetailsService {

    @Autowired
    private SessionRespository sessionRespository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        String[] tokenArray = token.split(":");
        if(tokenArray.length!=3)
        {
            throw new UsernameNotFoundException("INVALID ACCESS TOKEN RECIEVED");
        }

        String userId=tokenArray[0];
        String sessionId=tokenArray[1];
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority("ROLE_"+tokenArray[2]);

        Map<String,?> map = sessionRespository.VALIDATE_USER_SESSION(userId, sessionId);

        UserDetails userDetails;

        Integer errorCode=Integer.parseInt(validateNullStringInMap(map.get("errorCode")));
        String errorMessage = validateNullStringInMap(map.get("errorMessage"));
        if(errorCode==0 && errorMessage==null)
        {
            String StatusName = validateNullStringInMap(map.get("StatusName"));
            if(!StringUtils.isEmpty(StatusName) && StatusName.equalsIgnoreCase("ACTIVE"))
            {
                userDetails = User
                        .withUsername(userId)
                        .password("NO_NEEDED")
                        .authorities(simpleGrantedAuthority)
                        .accountExpired(false)
                        .accountLocked(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .build();
            }
            else
            {
                throw new UsernameNotFoundException("EITHER SESSION IS INACTIVE OR USERNAME DOESN'T EXIST");
            }
        }
        else
        {
            throw new UsernameNotFoundException("UNEXPECTED ERROR OCCURRED");
        }

        return userDetails;
    }
}
