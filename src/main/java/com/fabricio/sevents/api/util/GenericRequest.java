package com.fabricio.sevents.api.util;

import com.fabricio.sevents.api.util.page.PageableSevent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class GenericRequest extends Request implements Serializable {

     @Min(0)
     @JsonProperty(access = JsonProperty.Access.READ_ONLY)
     private Integer page;

     @Min(0)
     @Max(PageableSevent.LIMIT_DEFAULT)
     @JsonProperty(access = JsonProperty.Access.READ_ONLY)
     private Integer limit;

     private String[] sort;
     
}