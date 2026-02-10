package dev.keven.ecommerce.modules.user.presentation.controller;

import dev.keven.ecommerce.modules.user.application.usecase.RefreshTokenUseCase;
import dev.keven.ecommerce.modules.user.application.usecase.UserLoginUseCase;
import dev.keven.ecommerce.modules.user.application.usecase.UserRegisterUseCase;
import dev.keven.ecommerce.modules.user.presentation.dto.request.UserLoginRequest;
import dev.keven.ecommerce.modules.user.presentation.dto.request.UserRegisterRequest;
import dev.keven.ecommerce.modules.user.presentation.dto.response.UserLoginResponse;
import dev.keven.ecommerce.modules.user.presentation.dto.response.UserRegisterResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserLoginUseCase userLoginUseCase;
    private final UserRegisterUseCase userRegisterUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    public AuthController(UserLoginUseCase userLoginUseCase, UserRegisterUseCase userRegisterUseCase
    , RefreshTokenUseCase refreshTokenUseCase) {
        this.userLoginUseCase = userLoginUseCase;
        this.userRegisterUseCase = userRegisterUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Valid UserRegisterRequest request) {
        UserRegisterResponse response = userRegisterUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest request) {
        UserLoginResponse response = userLoginUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserLoginResponse> refresh(@RequestHeader("Authorization") String authorization) {
        String refreshToken = authorization.replace("Bearer ", "");
        UserLoginResponse response = refreshTokenUseCase.execute(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
