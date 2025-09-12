package com.hsryuuu.base.application.util;

import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

@Slf4j
public class LogUtils {

  public static <T> void loggingCollection(Iterable<T> iterable){
    for (T t : iterable) {
      log.debug(t.toString());
    }
  }
}
