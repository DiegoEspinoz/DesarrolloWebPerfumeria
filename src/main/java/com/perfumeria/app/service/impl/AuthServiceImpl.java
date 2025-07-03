package com.perfumeria.app.service.impl;

import com.perfumeria.app.repository.AdministradorRepository;
import com.perfumeria.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public boolean login(String nickname, String password) {
        return administradorRepository.findByNombreAndContraseña(nickname, password).isPresent();
    }
}
