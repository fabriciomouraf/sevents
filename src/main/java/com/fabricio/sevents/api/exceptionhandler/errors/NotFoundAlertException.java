package com.fabricio.sevents.api.exceptionhandler.errors;

import com.fabricio.sevents.api.exceptionhandler.enumeration.ErrorCodeEnum;
import com.fabricio.sevents.api.exceptionhandler.errors.generic.RequestAlertException;
import org.zalando.problem.Status;

@SuppressWarnings( "rawtypes")
public class NotFoundAlertException extends RequestAlertException {

     private static final long serialVersionUID = 1L;

     public NotFoundAlertException(String defaultMessage, Class entidade, ErrorCodeEnum error){
          super(defaultMessage, Status.NOT_FOUND, entidade, error);
     }
}