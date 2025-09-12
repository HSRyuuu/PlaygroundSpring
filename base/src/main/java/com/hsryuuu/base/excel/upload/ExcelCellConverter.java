package com.hsryuuu.base.excel.upload;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/*
 * Cell의 이름과 값을 가져온다.
 */
public class ExcelCellConverter {

  public static String getStringValue(Cell cell) {
    String value = "";

    if(cell == null){
      return value;
    }

    switch (cell.getCellType()) {
      case CellType.FORMULA -> value = cell.getCellFormula(); // 수식
      case CellType.NUMERIC -> value = cell.getNumericCellValue() + ""; // 숫자
      case CellType.BOOLEAN -> value = cell.getBooleanCellValue() + ""; // bool
      case CellType.ERROR -> value = cell.getErrorCellValue() + ""; // 에러
      case CellType.BLANK -> value = ""; // 공백
      default -> value = cell.getStringCellValue(); // CellType.String
    }
    return value;
  }

  public static Object getObjectValue(Cell cell) {
    Object value = null;

    if(cell == null){
      return "";
    }

    switch (cell.getCellType()) {
      case CellType.FORMULA -> value = cell.getCellFormula(); // 수식
      case CellType.NUMERIC -> value = cell.getNumericCellValue(); // 숫자
      case CellType.BOOLEAN -> value = cell.getBooleanCellValue(); // bool
      case CellType.ERROR -> value = cell.getErrorCellValue(); // 에러
      case CellType.BLANK -> value = ""; // 공백
      default -> value = cell.getStringCellValue(); // CellType.String
    }
    return value;
  }

}
