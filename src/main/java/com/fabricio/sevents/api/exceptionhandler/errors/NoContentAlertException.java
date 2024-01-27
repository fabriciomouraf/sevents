package com.fabricio.sevents.api.exceptionhandler.errors;

import com.fabricio.sevents.api.exceptionhandler.enumeration.ErrorCodeEnum;
import com.fabricio.sevents.api.exceptionhandler.errors.generic.RequestAlertException;
import org.zalando.problem.Status;

@SuppressWarnings( "rawtypes")
public class NoContentAlertException extends RequestAlertException {

	private static final long serialVersionUID = 1L;

	public NoContentAlertException(String defaultMessage, Class entidade, ErrorCodeEnum error) {

		super(defaultMessage, Status.NO_CONTENT, entidade, error);

	}

}