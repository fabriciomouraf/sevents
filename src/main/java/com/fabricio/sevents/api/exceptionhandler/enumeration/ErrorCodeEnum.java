package com.fabricio.sevents.api.exceptionhandler.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

     GLOBAL("E00000"),

     EVENTO_ANCORA("E01000"),
     ;

     private String code;

}