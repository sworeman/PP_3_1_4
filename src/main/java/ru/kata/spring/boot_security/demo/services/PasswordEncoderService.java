package ru.kata.spring.boot_security.demo.services;

public interface PasswordEncoderService {
    String encode(CharSequence rawPassword);
}
