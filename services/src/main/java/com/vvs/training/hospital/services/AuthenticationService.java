package com.vvs.training.hospital.services;

import java.util.Map;

import com.vvs.training.hospital.services.exceptions.AutorisationException;

public interface AuthenticationService {

    Map<String, Long> validateUserPassword(String username, String password);
}
