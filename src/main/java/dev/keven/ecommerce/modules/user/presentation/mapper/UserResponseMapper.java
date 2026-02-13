package dev.keven.ecommerce.modules.user.presentation.mapper;

import dev.keven.ecommerce.modules.user.application.result.UserLoginResult;
import dev.keven.ecommerce.modules.user.application.result.UserRegisterResult;
import dev.keven.ecommerce.modules.user.presentation.dto.response.UserLoginResponse;
import dev.keven.ecommerce.modules.user.presentation.dto.response.UserRegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public static UserRegisterResponse toResponse(UserRegisterResult result) {
        return new UserRegisterResponse(
                result.firstName(),
                result.lastName(),
                result.email()
        );
    }

    public static UserLoginResponse toResponse(UserLoginResult result) {
        return new UserLoginResponse(
                result.token(),
                result.refreshToken()
        );
    }
}
