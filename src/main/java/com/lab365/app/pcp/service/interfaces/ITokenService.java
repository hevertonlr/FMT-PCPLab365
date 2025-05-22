package com.lab365.app.pcp.service.interfaces;

import com.lab365.app.pcp.controller.dto.request.LoginRequest;
import com.lab365.app.pcp.controller.dto.response.LoginResponseDTO;

public interface ITokenService {
    LoginResponseDTO getToken(LoginRequest request);
}
