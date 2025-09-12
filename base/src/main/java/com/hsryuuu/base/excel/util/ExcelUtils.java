package com.hsryuuu.base.excel.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;


public class ExcelUtils {
  private ExcelUtils() {}
  public static CellStyle getHeaderCellStyle(Workbook workbook){
    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    setCellStyleBorder(headerStyle, BorderStyle.MEDIUM);
    setCellStyleAlignmentCenter(headerStyle);
    return headerStyle;
  }

  public static void setCellStyleBorder(CellStyle cellStyle,BorderStyle borderStyle){
    cellStyle.setBorderTop(borderStyle);
    cellStyle.setBorderBottom(borderStyle);
    cellStyle.setBorderLeft(borderStyle);
    cellStyle.setBorderRight(borderStyle);
  }

  public static void setCellStyleAlignmentCenter(CellStyle cellStyle){
    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 수직 가운데 정렬
    cellStyle.setAlignment(HorizontalAlignment.CENTER); // 수평 가운데 정렬
  }

  public static DataValidation getCellValidation(
      DataValidationHelper dvHelper,
      DataValidationConstraint constraint,
      CellRangeAddressList addressList,
      String errorTitle,
      String errorMessage
      ){
    DataValidation validation = dvHelper.createValidation(constraint, addressList);
    validation.setShowErrorBox(true);
    validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
    validation.createErrorBox(errorTitle, errorMessage);
    return validation;
  }

  public static ByteArrayResource getByteArrayResource(Workbook workbook) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    workbook.write(outputStream);
    return new ByteArrayResource(outputStream.toByteArray());
  }


}
