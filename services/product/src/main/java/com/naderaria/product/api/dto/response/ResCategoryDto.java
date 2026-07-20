package com.naderaria.product.api.dto.response;

public record ResCategoryDto(Long id, String name, String description, int sortOrder, boolean active) {
}