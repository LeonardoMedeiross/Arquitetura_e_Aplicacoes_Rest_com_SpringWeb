package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indica que esta classe é uma classe de configuração do Spring
@EnableWebSecurity // Habilita a segurança web
public class SecurityConfig {

    // Método para configurar a cadeia de filtros de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configura as regras de segurança para requisições HTTP
        http
                .csrf(csrf -> csrf.disable()) // Desabilita a proteção contra CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Requer autenticação para qualquer requisição
                )
                .httpBasic(Customizer.withDefaults()); // Configura a autenticação básica
        return http.build(); // Retorna a configuração construída
    }

    // Método que define um serviço de detalhes de usuário em memória
    @Bean
    public UserDetailsService userDetailsService() {
        // Cria um usuário com nome de usuário, senha e roles
        UserDetails user = User.withDefaultPasswordEncoder() // Define o encoder de senha padrão
                .username("Leonardo") // Define o nome de usuário
                .password("123456") // Define a senha ({noop} na frente da senha  indica que não haverá codificação)
                .roles("USER") // Define as roles do usuário
                .build(); // Constrói o objeto UserDetails
        // Retorna um gerenciador de usuários em memória com o usuário criado
        return new InMemoryUserDetailsManager(user);
    }
}
