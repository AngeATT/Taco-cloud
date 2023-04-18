package angeATT.Tacocloud.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UtilisateurRepository userRepo) {
        return username -> {
            Utilisateur utilisateur = userRepo.findByUsername(username);
            if (utilisateur != null) return utilisateur;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/login","/registration").permitAll()
                                .requestMatchers("/orders","/design").hasRole("USER")
                                .requestMatchers(HttpMethod.POST,"/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                                .requestMatchers(HttpMethod.DELETE,"/api/ingredients").hasAuthority("SCOPE_deleteIngredients")
                                .requestMatchers("/","/**").authenticated()
                        //  .requestMatchers("/login","/home","/registration","/").permitAll()
                                 )
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design", true)
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .oauth2Login(
                        oauth2Login ->
                                oauth2Login.loginPage("/oauth2/authorization/taco-admin-client"))
                .oauth2Client(withDefaults())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .csrf().disable(); //desactiver la protection contre csrf pour accéder à h2

        http.headers().frameOptions().disable(); //h2 se lance dans une frame donc on va desactiver X-frame options dans spring security
        return http.build();

    }
}



















