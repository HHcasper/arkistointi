package backend.arkistointi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/arkistolista", "/yritykset", "/api/arkistot", "/api/arkistot/**").permitAll()
                .requestMatchers(
                    "/delete/**",
                    "/edit/**",
                    "/lisaa",
                    "/tallenna",
                    "/lisaayritys",
                    "/tallennaYritys"
                ).hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/arkistolista", true)
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }
}