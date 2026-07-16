package com.naderaria.common_security.dto;

public record CurrentPermissionDto(String operation, String targetType, String title) {
}