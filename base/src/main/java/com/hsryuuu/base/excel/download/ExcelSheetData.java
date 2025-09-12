package com.hsryuuu.base.excel.download;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExcelSheetData {
  private final List<?> dataList;
  private final Class<?> type;

  public static ExcelSheetData of(List<?> dataList, Class<?> type) {
    return new ExcelSheetData(dataList, type);
  }
}
