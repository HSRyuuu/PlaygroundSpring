package com.hsryuuu.base.excel.download;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ByteArrayResource;

public interface ExcelFile {

  void write(ByteArrayOutputStream stream) throws IOException;

  ByteArrayResource writeWithEncryption(ByteArrayOutputStream stream, String password)
      throws IOException;

  default <T> void createCell(Row row, int column, T value, CellStyle style) {
    if (value == null)
      return; // avoid NPE

    Cell cell = row.createCell(column);
    switch (value) {
      case Integer i -> cell.setCellValue(i);
      case Long l -> cell.setCellValue(l);
      case Boolean b -> cell.setCellValue(b);
      default -> cell.setCellValue((String) value);
    }
    cell.setCellStyle(style);
  }

  default CellStyle createCellStyle(Workbook workbook, boolean isBold) {
    CellStyle style = workbook.createCellStyle();
    Font font = workbook.createFont();
    font.setBold(isBold);
    style.setFont(font);

    return style;
  }
}
