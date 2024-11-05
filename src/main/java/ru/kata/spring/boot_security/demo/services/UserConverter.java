package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setAge(user.getAge());
        userDTO.setRoles(user.getRoles().stream()
                .map(Role::getId)  // Извлекаем только ID ролей
                .collect(Collectors.toSet()));
        return userDTO;
    }

    public static User toUser(UserDTO userDTO, RoleService roleService) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setAge(userDTO.getAge());
        user.setPassword(userDTO.getPassword());

        List<Long> roleIds = new ArrayList<>(userDTO.getRoles());

        Set<Role> roles = roleService.findByIds(roleIds);
        user.setRoles(roles);

        return user;
    }
}

