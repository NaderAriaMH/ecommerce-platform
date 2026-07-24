package com.naderaria.product.web.controller.dto.response;

public record ResCategoryDto(Long id, String name, String description, int sortOrder, boolean active) {
}