package com.hsryuuu.base.excel.upload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/*
 * Excel 파일을 읽어 확장자를 비교
 */
@Slf4j
public class ExcelFileType {
  public static Workbook getWorkbook(String filePath) {
    // FileInputStream fis = null;
    InputStream fis = null;
    try {
      fis = new FileInputStream(filePath);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e.getMessage(), e);
    }

    return getWorkbook(filePath, fis);
  }

  public static Workbook getWorkbook(MultipartFile file) {
    // FileInputStream fis = null;
    String originalFilename = null;
    InputStream fis = null;
    try {
      originalFilename = file.getOriginalFilename();
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
    /*
     * 파일의 확장자를 체크해서 .XLS라면 HSSFWorkbook에 .XLSX라면 XSSFWorkbook에 각각 초기화한다.
     */
    Workbook wb = null;
    if (filename.toUpperCase().endsWith(".XLS")) {
      try {
        wb = new HSSFWorkbook(fis);
      } catch (IOException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    } else if (filename.toUpperCase().endsWith(".XLSX")) {
      try {
        wb = new XSSFWorkbook(fis);
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
    return wb;

  }
}
