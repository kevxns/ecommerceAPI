package dev.keven.ecommerce.common.config;

import dev.keven.ecommerce.modules.user.application.usecase.UserLoginUseCase;
import dev.keven.ecommerce.modules.user.application.usecase.UserRegisterUseCase;
import dev.keven.ecommerce.modules.user.application.gateway.UserGateway;
import dev.keven.ecommerce.modules.user.infrastructure.adapter.UserAdapter;
import dev.keven.ecommerce.modules.user.infrastructure.persistence.repository.UserRepository;
import dev.keven.ecommerce.security.auth.AuthService;
import dev.keven.ecommerce.security.hash.PasswordHashService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    UserRegisterUseCase userRegisterUseCase(UserGateway userGateway, PasswordHashService passwordHashService) {
        return new UserRegisterUseCase(userGateway, passwordHashService);
    }

    @Bean
    UserLoginUseCase userLoginUseCase(UserGateway userGateway, PasswordHashService passwordHashService, AuthService authService) {
        return new UserLoginUseCase(userGateway, passwordHashService, authService);
    }

    @Bean
    PasswordHashService passwordHashService(BCryptPasswordEncoder encoder) {
        return new PasswordHashService(encoder);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository) {
        return new UserAdapter(userRepository);
    }
}
