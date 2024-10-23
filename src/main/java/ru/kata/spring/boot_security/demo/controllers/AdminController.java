package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("users", userServiceImpl.findAll());
        model.addAttribute("allRoles", roleServiceImpl.findAll());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user, @RequestParam("roles") List<Long> roleIds) {
        Set<Role> roles = roleServiceImpl.findByIds(roleIds);
        user.setRoles(roles);
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userServiceImpl.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleServiceImpl.findAll());
        return "edit_user";
    }

    @PostMapping("/update_user")
    public String updateUser(@ModelAttribute User user, @RequestParam Long id, Model model) {
        user.setId(id);
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }
}
