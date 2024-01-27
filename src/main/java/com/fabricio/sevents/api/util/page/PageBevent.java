package com.fabricio.sevents.api.util.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageBevent<T>{

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public int number;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public int size;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public int totalPages;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public int numberOfElements;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public long totalElements;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public boolean firstPage;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public boolean hasPrevious;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public boolean hasNext;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public boolean hasContent;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public boolean first;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public boolean last;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public int nextPage;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public int previousPage;

     @JsonInclude(JsonInclude.Include.ALWAYS)
     public List<T> content;
     
}