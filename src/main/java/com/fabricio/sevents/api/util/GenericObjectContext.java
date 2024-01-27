package com.fabricio.sevents.api.util;

import com.fabricio.sevents.api.util.object.Objeto;
import com.fabricio.sevents.api.util.page.PageSevent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public abstract class GenericObjectContext{

     @Autowired
     private ModelMapper modelMapper;

     protected <E, T> E convert(T source, Class<E> typeDestination) {

          E model = null;
          if (source != null && typeDestination != null) {

               model = modelMapper.map(source, typeDestination);

          }

          return model;
     }

     private <E, T> List<E> convertModelMapper(List<T> source, Type destinationType) {

          List<E> model = null;
          if (source != null && destinationType != null) {

               model = modelMapper.map(source, destinationType);

          }

          return model;
     }

     protected <T> PageSevent<T> convertToPageSevent(Page<T> p) {

          int nextPage = 0;
          int previousPage = 0;

          if (p.hasNext()) {
               nextPage = p.getNumber() + 1;
          }

          if (p.hasPrevious()) {
               previousPage = -1;
          }

          return new PageSevent<T>(p.getNumber(), p.getSize(), p.getTotalPages(), p.getNumberOfElements(), p.getTotalElements(), p.isFirst(), p.hasPrevious(), p.hasNext(), p.hasContent(), p.isFirst(), p.isLast(), nextPage, previousPage, p.getContent());
     }

     protected <E, T> PageSevent<E> convertToPageSevent(Page<T> p, Type type) {

          int nextPage = 0;
          int previousPage = 0;

          if (p.hasNext()) {
               nextPage = p.getNumber() + 1;
          }

          if (p.hasPrevious()) {
               previousPage = p.getNumber() - 1;
          }

          List<E> list = new ArrayList<E>();
          if (Objeto.notBlank(p, p.getContent())) {
               list = convertModelMapper(p.getContent(), type);
          }

          PageSevent<E> pageBevent = new PageSevent<E>();
          pageBevent.setFirst(p.isFirst());
          pageBevent.setLast(p.isLast());
          pageBevent.setNumber(p.getNumber());
          pageBevent.setSize(p.getSize());
          pageBevent.setTotalPages(p.getTotalPages());
          pageBevent.setNumberOfElements(p.getNumberOfElements());
          pageBevent.setTotalElements(p.getTotalElements());
          pageBevent.setHasNext(p.hasNext());
          pageBevent.setHasPrevious(p.hasPrevious());
          pageBevent.setHasContent(p.hasContent());
          pageBevent.setNextPage(nextPage);
          pageBevent.setPreviousPage(previousPage);
          pageBevent.setContent(list);

          return pageBevent;

     }

     protected void copyNonNullProperties(Object source, Object destination) {

          BeanUtils.copyProperties(source, destination, getNullPropertyNames(source));
          
     }

     private String[] getNullPropertyNames(Object source) {

          final BeanWrapper src = new BeanWrapperImpl(source);
          java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

          Set<String> emptyNames = new HashSet<>();
          for (java.beans.PropertyDescriptor pd : pds) {
               Object srcValue = src.getPropertyValue(pd.getName());
               if (srcValue == null) {
                    emptyNames.add(pd.getName());
               }
          }

          String[] myArray = new String[emptyNames.size()];
          emptyNames.toArray(myArray);
          return myArray;

     }

}