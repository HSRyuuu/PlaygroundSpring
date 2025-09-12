package com.hsryuuu.base.excel.download;

import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class ExcelMetaData {
  private final Map<String, String> excelHeaderNames;
  private final List<String> dataFieldNames;
  private final String sheetName;

  public ExcelMetaData(Map<String, String> excelHeaderNames, List<String> dataFieldNames,
      String sheetName) {
    this.excelHeaderNames = excelHeaderNames;
    this.dataFieldNames = dataFieldNames;
    this.sheetName = sheetName;
  }

  public String getHeaderName(String fieldName) {
    return excelHeaderNames.getOrDefault(fieldName, "");
  }
}
