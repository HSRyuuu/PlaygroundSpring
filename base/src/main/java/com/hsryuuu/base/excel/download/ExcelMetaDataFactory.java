package com.hsryuuu.base.excel.download;

import com.hsryuuu.base.excel.annotation.ExcelColumn;
import com.hsryuuu.base.excel.annotation.ExcelSheet;
import com.hsryuuu.base.excel.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelMetaDataFactory {
  private ExcelMetaDataFactory() {}

  private static class SingletonHolder {
    private static final ExcelMetaDataFactory INSTANCE = new ExcelMetaDataFactory();
  }

  public static ExcelMetaDataFactory getInstance() {
    return SingletonHolder.INSTANCE;
  }

  public ExcelMetaData createMetadata(Class<?> clazz) {
    Map<String, String> headerNamesMap = this.extractFields(clazz);
    List<String> dataFieldNamesList = new ArrayList<>(headerNamesMap.keySet());
    return new ExcelMetaData(headerNamesMap, dataFieldNamesList, getSheetName(clazz));
  }

  /**
   * 헤더(필드)명 추출 - @ExcelColumn(headerName = "xxx")
   * @param clazz 클래스 타입
   * @return 헤더 명 Map
   */
  private Map<String, String> extractFields(Class<?> clazz){
    Map<String, String> headerNamesMap  = new LinkedHashMap<>();
    for (Field field : ReflectionUtils.getAllFields(clazz)) {
      if (field.isAnnotationPresent(ExcelColumn.class)) {
        ExcelColumn excelColumnAnnotation = field.getAnnotation(ExcelColumn.class);
        headerNamesMap.put(field.getName(), excelColumnAnnotation.headerName());
      }
    }
    if(headerNamesMap.isEmpty()){
      throw new RuntimeException(String.format("Class %s 이(가) @ExcelColumn 어노테이션을 가지고있지 않습니다.", clazz));
    }
    return headerNamesMap;
  }

  /**
   * sheetName 추출 - @ExcelSheet(name = "xxx")
   * @param clazz 클래스 타입
   * @return 시트 명
   */
  private String getSheetName(Class<?> clazz) {
    ExcelSheet annotation = (ExcelSheet) ReflectionUtils.getAnnotation(clazz, ExcelSheet.class);
    if (annotation != null) {
      return annotation.name();
    }
    return "sheet1";
  }
}
