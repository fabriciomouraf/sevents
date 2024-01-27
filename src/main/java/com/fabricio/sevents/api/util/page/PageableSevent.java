package com.fabricio.sevents.api.util.page;

import com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum;
import com.fabricio.sevents.api.util.object.Objeto;
import com.fabricio.sevents.api.util.reflexao.Reflexao;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PageableSevent extends PageRequest {

     public static final int PAGE_DEFAULT = 0;

     public static final int LIMIT_DEFAULT = 50;

     private static final long serialVersionUID = -644380311337417661L;
     
     private PageableSevent(Integer page, Integer limit){
          super(page, limit, Sort.unsorted());
     }

     private PageableSevent(Integer page, Integer limit, Sort sort){
          super(page, limit, sort);
     }
     
     public static PageableSevent get() {
    	 return new PageableSevent(PAGE_DEFAULT, LIMIT_DEFAULT);
     }
     
     public static PageableSevent setPageable(Integer page, Integer limit) {

          if (Objeto.isBlank(page)) {
               page = 0;
          }

          if (Objeto.isBlank(limit) || limit >= LIMIT_DEFAULT) {
               limit = LIMIT_DEFAULT;
          }

          return new PageableSevent(page, limit);
     }
     
     public static <T> PageableSevent setPageable(Integer page, Integer limit, Class<T> typeClass, String[] sorts) {
          
          PageableSevent pageBevent = null;
          if (sorts != null) {
               
               Sort sort = gerarSort(typeClass, sorts);
               if (Objeto.isBlank(page)) {
                    page = 0;
               }
               
               if (Objeto.isBlank(limit) || limit >= LIMIT_DEFAULT) {
                    limit = LIMIT_DEFAULT;
               }

               pageBevent = new PageableSevent(page, limit, sort);
               
          } else {

               pageBevent = setPageable(page, limit);
          }
          
          return pageBevent;
     }
          

     private static Sort gerarSort(Class<?> typeClass, String... sort) {

          boolean result = false;
          List<Sort.Order> orders = new ArrayList<Sort.Order>();
          if (sort != null) {

               for (String string : sort) {

                    String[] split = string.trim().split(" ");
                    if (split.length != 2) {

                         break;

                    } else {

                         String field = split[0];
                         String direction = split[1];

                         if (Objeto.notBlank(field) && Objeto.notBlank(direction)) {
                              
                              Object o = Reflexao.criaInstancia(typeClass);
                              boolean existeGet = Reflexao.existeGet(o, field);
                              
                              if (existeGet && direction.equals("asc")) {
                                   orders.add(new Sort.Order(Sort.Direction.ASC, field));
                                   result = true;
                              } else if (existeGet && direction.equals("desc")) {
                                   orders.add(new Sort.Order(Sort.Direction.DESC, field));
                                   result = true;
                              }else {
                                   result = false;
                                   break;
                              }

                         }

                    }

               }

          }
          
          ExceptionEnum.checkThrow(!result, ExceptionEnum.GLOBAL_EXPRESSION_SORT_INVALID);
          return Sort.by(orders);
          
     }
     
}