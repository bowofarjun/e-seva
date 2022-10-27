package in.ac.bitspilani.wilp.esevaapi.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EsevaJWTTokenProvider eSevaJWTTokenProvider;
    private static final Integer PASS_STRENGTH=12;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/language/**").permitAll()
                .antMatchers("/role/**").permitAll()
                .antMatchers("/service/**").permitAll()
                .antMatchers("/status/**").permitAll()
                .antMatchers("/user/sign-in/**").permitAll()
                .antMatchers("/user/sign-up/**").permitAll()
                //Authenticate rest endpoints
                .anyRequest().authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.apply(new EsevaJWTTokenFilterConfigurer(eSevaJWTTokenProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(PASS_STRENGTH);
    }

}
