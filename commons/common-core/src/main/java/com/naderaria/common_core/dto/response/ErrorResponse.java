package com.naderaria.common_core.dto.response;


import java.util.List;

public record ErrorResponse(List<ErrorMessage> errorMessages) {}
