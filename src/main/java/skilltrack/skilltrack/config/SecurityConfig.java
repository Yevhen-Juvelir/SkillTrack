package skilltrack.skilltrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ОТКЛЮЧАЕМ CSRF ДЛЯ ВСЕХ ЗАПРОСОВ
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // РАЗРЕШАЕМ БЕЗ АВТОРИЗАЦИИ:
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers("/users/login").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // Для API endpoints тоже разрешаем (если нужно)
                        .requestMatchers("/api/**").permitAll()

                        // Все остальные страницы требуют авторизации:
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/users/login")
                        .loginProcessingUrl("/users/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/users/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/users/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }
}