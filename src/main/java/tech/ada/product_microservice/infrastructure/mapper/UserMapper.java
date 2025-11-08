package tech.ada.product_microservice.infrastructure.mapper;

import org.springframework.stereotype.Component;
import tech.ada.product_microservice.application.domain.User;
import tech.ada.product_microservice.infrastructure.adapter.out.repository.entity.UserEntity;
import tech.ada.product_microservice.infrastructure.dto.UserDTO;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userEntity;
    }

    public User toDomain(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public User toDomain(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }

}
