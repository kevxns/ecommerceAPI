package dev.keven.ecommerce.modules.user.presentation.mapper;

import dev.keven.ecommerce.modules.user.application.command.UserLoginCommand;
import dev.keven.ecommerce.modules.user.application.command.UserRegisterCommand;
import dev.keven.ecommerce.modules.user.presentation.dto.request.UserLoginRequest;
import dev.keven.ecommerce.modules.user.presentation.dto.request.UserRegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {

    public static UserRegisterCommand toCommand(UserRegisterRequest request) {
        return new UserRegisterCommand(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password(),
                request.roles()
        );
    }

    public static UserLoginCommand toCommand(UserLoginRequest request) {
        return new UserLoginCommand(
                request.email(),
                request.password()
        );
    }
}
