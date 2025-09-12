package com.hsryuuu.base.excel.util;

import com.hsryuuu.base.excel.constant.ExcelConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
public class ExcelUtils {
  private ExcelUtils() {}

  public static Workbook getWorkbook(MultipartFile file) {
    if(file == null || file.isEmpty()) {
      throw new RuntimeException("비어있는 파일입니다.");
    }
    String originalFilename = file.getOriginalFilename();
    if(originalFilename == null || originalFilename.isEmpty()){
      throw new RuntimeException("파일 명이 존재하지 않습니다.");
    }
    InputStream fis = null;
    try{
      fis = file.getInputStream();
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
    return getWorkbook(originalFilename, fis);
  }

  /*
   * 엑셀 파일을 읽어서 Workbook 객체에 리턴한다. .xls와 .xlsx 확장자를 비교한다.
   * FileInputStream은 파일의 경로에 있는 파일을 읽어서 Byte로 가져온다.
   * 파일이 존재하지 않는다면 RuntimeException 발생
   */
  public static Workbook getWorkbook(String filename, InputStream fis) {
    if(!isValidExtension(filename)){
      throw new RuntimeException("Excel 파일만 업로드 가능합니다.");
    }
    Workbook workbook = null;
    // .xlsx => XSSFWorkbook
    if (filename.toLowerCase().endsWith(ExcelConstants.EXTENSION_XLSX)) {
      try {
        workbook = new XSSFWorkbook(fis);
      } catch (IOException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    }
    // .xls => HSSFWorkbook
    else if (filename.toLowerCase().endsWith(ExcelConstants.EXTENSION_XLS)) {
      try {
        workbook = new HSSFWorkbook(fis);
      } catch (IOException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    }

    if (fis != null) {
      try {
        fis.close();
      } catch (IOException e) {
        log.error(e.getMessage());
      }
    }
    return workbook;

  }

  public static boolean isValidExtension(String fileNameWithExtension){
    if(fileNameWithExtension == null || fileNameWithExtension.isEmpty()){
      return false;
    }
    return fileNameWithExtension.toLowerCase().endsWith(ExcelConstants.EXTENSION_XLSX)
        || fileNameWithExtension.toLowerCase().endsWith(ExcelConstants.EXTENSION_XLS);
  }

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
