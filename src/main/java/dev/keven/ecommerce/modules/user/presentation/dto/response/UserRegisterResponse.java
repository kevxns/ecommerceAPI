package dev.keven.ecommerce.modules.user.presentation.dto.response;

public record UserRegisterResponse(
        String firstName,
        String lastName,
        String email
) {
}
