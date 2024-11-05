package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAll();

    Set<Role> findByIds(List<Long> ids);

//    Role findByName(String roleName);
}
