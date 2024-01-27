package com.fabricio.sevents.api.util.page;

import com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum;
import com.fabricio.sevents.api.util.object.Objeto;
import com.fabricio.sevents.api.util.reflexao.Reflexao;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PageableSevents extends PageRequest {

     public static final int PAGE_DEFAULT = 0;

     public static final int LIMIT_DEFAULT = 50;

     private static final long serialVersionUID = -644380311337417661L;
     
     private PageableSevents(Integer page, Integer limit){
          super(page, limit, Sort.unsorted());
     }

     private PageableSevents(Integer page, Integer limit, Sort sort){
          super(page, limit, sort);
     }
     
     public static PageableSevents get() {
    	 return new PageableSevents(PAGE_DEFAULT, LIMIT_DEFAULT);
     }
     
     public static PageableSevents setPageable(Integer page, Integer limit) {

          if (Objeto.isBlank(page)) {
               page = 0;
          }

          if (Objeto.isBlank(limit) || limit >= LIMIT_DEFAULT) {
               limit = LIMIT_DEFAULT;
          }

          return new PageableSevents(page, limit);
     }
     
     public static <T> PageableSevents setPageable(Integer page, Integer limit, Class<T> typeClass, String[] sorts) {
          
          PageableSevents pageBevent = null;
          if (sorts != null) {
               
               Sort sort = gerarSort(typeClass, sorts);
               if (Objeto.isBlank(page)) {
                    page = 0;
               }
               
               if (Objeto.isBlank(limit) || limit >= LIMIT_DEFAULT) {
                    limit = LIMIT_DEFAULT;
               }

               pageBevent = new PageableSevents(page, limit, sort);
               
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
          
          ExceptionEnum.checkThrow(!result, ExceptionEnum.GLOBAL_EXPRESSAO_SORT_INVALIDO);
          return Sort.by(orders);
          
     }
     
}