package dev.keven.ecommerce.modules.user.presentation.mapper;

import dev.keven.ecommerce.modules.user.domain.User;
import dev.keven.ecommerce.modules.user.presentation.dto.response.UserRegisterResponse;

public class UserDTOMapper {

    public UserDTOMapper() {}

    public static UserRegisterResponse toDTO(User user) {
        return new UserRegisterResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
