package com.naderaria.identity.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.identity.api.dto.authentication.request.ReqLoginDto;
import com.naderaria.identity.api.dto.authentication.respone.ResTokenDto;
import com.naderaria.identity.api.dto.user.request.ReqUserDto;
import com.naderaria.identity.api.dto.user.request.ReqUserUpdatableDto;
import com.naderaria.identity.api.dto.user.response.ResUpdatableUserDto;
import com.naderaria.identity.api.dto.user.response.ResUserDto;
import com.naderaria.identity.api.dto.user.response.ResUserPageItemDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;

public interface UserService {

    ResTokenDto login(ReqLoginDto request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    PageResponse<ResUserPageItemDto> getUsers(PaginationDto paginationDto);

    ResUserDto getProfile(Long id);

    ResUpdatableUserDto register(ReqUserDto reqUserDto);

    void update(Long id,ReqUserUpdatableDto reqUserUpdatableDto);

    void delete(Long id);

}
