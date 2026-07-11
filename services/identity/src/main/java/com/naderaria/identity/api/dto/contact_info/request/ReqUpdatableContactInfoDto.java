package com.naderaria.identity.api.dto.contact_info.request;

public record ReqUpdatableContactInfoDto(Long id, String phoneNumber, String cellNumber, String email) {
}
