package dev.keven.ecommerce.modules.user.application.usecase;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.keven.ecommerce.common.exception.UserNotFoundException;
import dev.keven.ecommerce.modules.user.application.gateway.UserGateway;
import dev.keven.ecommerce.modules.user.domain.User;
import dev.keven.ecommerce.modules.user.presentation.dto.response.UserLoginResponse;
import dev.keven.ecommerce.security.jwt.JwtProvider;

public class RefreshTokenUseCase {

    private final UserGateway userGateway;
    private final JwtProvider jwtProvider;

    public RefreshTokenUseCase(UserGateway userGateway, JwtProvider jwtProvider) {
        this.userGateway = userGateway;
        this.jwtProvider = jwtProvider;
    }

    public UserLoginResponse execute(String refreshToken) {

        DecodedJWT decode = jwtProvider.verifyRefreshToken(refreshToken);

        String email = decode.getSubject();

        User user = userGateway.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        String newAccessToken = jwtProvider.generateToken(user);

        return new UserLoginResponse(newAccessToken, refreshToken);
    }
}
