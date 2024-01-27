package com.fabricio.sevents.api.exceptionhandler.errors;

import com.fabricio.sevents.api.exceptionhandler.enumeration.ErrorCodeEnum;
import com.fabricio.sevents.api.exceptionhandler.errors.generic.RequestAlertException;
import org.zalando.problem.Status;

@SuppressWarnings( "rawtypes")
public class BadRequestAlertException extends RequestAlertException {

     private static final long serialVersionUID = 1L;

     public BadRequestAlertException(String defaultMessage, Class entidade, ErrorCodeEnum error){
          super(defaultMessage, Status.BAD_REQUEST, entidade, error);
     }

     public BadRequestAlertException(String defaultMessage, String entidade, String error){
          super(defaultMessage, Status.BAD_REQUEST, entidade, error);
     }
}