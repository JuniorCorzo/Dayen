package com.dayen.dayen.dao.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public record ExceptionResponse(
		@JsonProperty("message")
		String Message,
		@JsonProperty("status-code")
		HttpStatus code
) {
}
