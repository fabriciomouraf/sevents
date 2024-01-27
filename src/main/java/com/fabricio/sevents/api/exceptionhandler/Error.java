package com.fabricio.sevents.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Error {
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssX")
	private OffsetDateTime timestamp;
	private Integer status;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private List<Field> fields;
	
	@Getter
	@Builder
	static class Field{
		private String name;
		private String userMessage;
	}
}
