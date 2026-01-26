package dev.keven.ecommerce.modules.user.infrastructure.adapter;

import dev.keven.ecommerce.modules.user.domain.User;
import dev.keven.ecommerce.modules.user.application.gateway.UserGateway;
import dev.keven.ecommerce.modules.user.infrastructure.persistence.entity.UserEntity;
import dev.keven.ecommerce.modules.user.infrastructure.persistence.repository.UserRepository;
import dev.keven.ecommerce.modules.user.presentation.mapper.UserEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAdapter implements UserGateway {

    private final UserRepository userRepository;

    public UserAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserEntityMapper::toDomain);
    }

    @Override
    public User save(User user) {

        UserEntity userEntity = UserEntityMapper.toEntity(user);

        UserEntity saved = userRepository.save(userEntity);

        return UserEntityMapper.toDomain(saved);
    }
}
