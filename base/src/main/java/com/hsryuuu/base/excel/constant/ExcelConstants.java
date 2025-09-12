package com.hsryuuu.base.excel.constant;

import org.springframework.http.MediaType;

public class ExcelConstants {
  public static final String EXTENSION_XLSX = ".xlsx";
  public static final String EXTENSION_XLS = ".xls";

  public static final MediaType MEDIA_TYPE_XLSX =
      MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

  public static final MediaType MEDIA_TYPE_XLS =
      MediaType.parseMediaType("application/vnd.ms-excel");

}
