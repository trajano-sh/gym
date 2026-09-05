package dev.trajano.mastersys.core.exception.handler;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        Instant timestamp,
        Integer status,
        String error,
        List<String> messages
) {
}
