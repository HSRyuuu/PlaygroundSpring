package com.hsryuuu.base.excel.download;


import jakarta.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.Getter;
import org.springframework.core.io.Resource;

@Getter
public class SXSSFExcelFile extends BaseSXSSFExcelFile {
  private Resource resource;

  public SXSSFExcelFile(ExcelSheetData data) throws IOException {
    this(data, null);
  }

  public SXSSFExcelFile(ExcelSheetData data, @Nullable String password) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ExcelMetaData metadata = ExcelMetaDataFactory.getInstance().createMetadata(data.getType());
    this.exportExcelFile(data, metadata, outputStream, password);
  }

  private void exportExcelFile(ExcelSheetData data, ExcelMetaData metadata,
      ByteArrayOutputStream outputStream, String password) throws IOException {
    this.renderHeaders(metadata);
    this.renderDataLines(data);
    // if password is null, encryption will not be applied.
    this.resource = this.writeWithEncryption(outputStream, password);
  }

}
