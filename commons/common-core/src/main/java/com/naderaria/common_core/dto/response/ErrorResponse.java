package com.naderaria.common_core.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Instant timestamp;

    private String code;

    private String message;

    private int status;

    private String path;

    private List<ErrorMessage> errorMessages;

    private String description;

    public ErrorResponse(String code, String message, int status, String path, String description) {
        setTimestamp(Instant.now());
        setCode(code);
        setMessage(message);
        setStatus(status);
        setPath(path);
        setDescription(description);
    }

}
