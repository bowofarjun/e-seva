package in.ac.bitspilani.wilp.esevaapi.access;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class EsevaJWTTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final EsevaJWTTokenProvider eSevaJWTTokenProvider;

    public EsevaJWTTokenFilterConfigurer(EsevaJWTTokenProvider eSevaJWTTokenProvider) {
        this.eSevaJWTTokenProvider=eSevaJWTTokenProvider;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        EsevaJWTTokenFilter esevaJWTTokenFilter = new EsevaJWTTokenFilter(eSevaJWTTokenProvider);
        http.addFilterBefore(esevaJWTTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
