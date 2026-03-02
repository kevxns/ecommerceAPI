package dev.keven.ecommerce.modules.user.presentation.controller;

import dev.keven.ecommerce.modules.user.application.usecase.RefreshTokenUseCase;
import dev.keven.ecommerce.modules.user.application.usecase.UserLoginUseCase;
import dev.keven.ecommerce.modules.user.application.usecase.UserRegisterUseCase;
import dev.keven.ecommerce.modules.user.presentation.dto.request.UserLoginRequest;
import dev.keven.ecommerce.modules.user.presentation.dto.request.UserRegisterRequest;
import dev.keven.ecommerce.modules.user.presentation.dto.response.UserLoginResponse;
import dev.keven.ecommerce.modules.user.presentation.dto.response.UserRegisterResponse;
import dev.keven.ecommerce.modules.user.presentation.mapper.UserRequestMapper;
import dev.keven.ecommerce.modules.user.presentation.mapper.UserResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Autenticacao e gestao de token")
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
    @Operation(summary = "Registrar usuario", description = "Cria um novo usuario com perfil CUSTOMER.")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Valid UserRegisterRequest request) {
        var result = userRegisterUseCase.execute(
                UserRequestMapper.toCommand(request)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseMapper.toResponse(result));
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Autentica um usuario e retorna access token e refresh token.")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest request) {
        var result = userLoginUseCase.execute(
                UserRequestMapper.toCommand(request)
        );
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseMapper.toResponse(result));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Atualizar token", description = "Gera um novo access token a partir do refresh token.")
    public ResponseEntity<UserLoginResponse> refresh(@RequestHeader("Authorization") String authorization) {
        String refreshToken = authorization.replace("Bearer ", "");
        UserLoginResponse response = refreshTokenUseCase.execute(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
