package com.fabricio.sevents.api.exceptionhandler.errors.generic;

import com.fabricio.sevents.api.exceptionhandler.enumeration.ErrorCodeEnum;
import com.fabricio.sevents.api.exceptionhandler.errors.constants.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings( "rawtypes")
public abstract class RequestAlertException extends AbstractThrowableProblem {

     private static final long serialVersionUID = 1L;

     protected final String entityName;

     protected final String errorKey;

     protected RequestAlertException(String defaultMessage, Status status, Class entidade, ErrorCodeEnum error){
          this(defaultMessage, status, entidade.getSimpleName(), error.getCode());
     }

     protected RequestAlertException(String defaultMessage, Status status, String entityName, String errorKey){
          this(ErrorConstants.DEFAULT_TYPE, status, defaultMessage, entityName, errorKey);
     }

     private RequestAlertException(URI type, Status status, String defaultMessage, String entityName, String errorKey){

          super(type, defaultMessage, status, null, null, null, getAlertParameters(entityName, errorKey));
          this.entityName = entityName;
          this.errorKey = errorKey;
     }

     public String getEntityName() {

          return entityName;
     }

     public String getErrorKey() {

          return errorKey;
     }

     protected static Map<String, Object> getAlertParameters(String entityName, String errorKey) {

          Map<String, Object> parameters = new HashMap<>();
          parameters.put("message", "error." + errorKey);
          parameters.put("params", entityName);
          return parameters;
     }
}