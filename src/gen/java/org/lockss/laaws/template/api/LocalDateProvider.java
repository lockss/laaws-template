package org.lockss.laaws.template.api;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

@Provider
public class LocalDateProvider implements ParamConverterProvider {

  @Override
  public <T> ParamConverter<T> getConverter(Class<T> type, Type type1, Annotation[] antns) {
    if (LocalDate.class.equals(type)) {
      return (ParamConverter<T>) new LocalDateConverter();
    }
    return null;
  }

  public static class LocalDateConverter implements ParamConverter<LocalDate> {

    @Override
    public LocalDate fromString(String string) {
      LocalDate localDate = LocalDate.parse(string);
      return localDate;
    }

    @Override
    public String toString(LocalDate t) {
      return t.toString();
    }
  }
}