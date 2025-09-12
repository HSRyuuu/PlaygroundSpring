package com.hsryuuu.base.excel.download;

import jakarta.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.Getter;
import org.springframework.core.io.Resource;
@Getter
public class SXSSFMultiSheetExcelFile extends BaseSXSSFExcelFile {
  private Resource resource;

  public SXSSFMultiSheetExcelFile(ExcelSheetDataGroup dataGroup)
      throws IOException {
    this(dataGroup, null);
  }

  public SXSSFMultiSheetExcelFile(ExcelSheetDataGroup dataGroup, @Nullable String password) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    this.exportExcelFile(dataGroup, outputStream, password);
  }


  private void exportExcelFile(ExcelSheetDataGroup dataGroup, ByteArrayOutputStream stream,
      String password) throws IOException {
    for (ExcelSheetData data : dataGroup.getExcelSheetData()) {
      ExcelMetaData metadata = ExcelMetaDataFactory.getInstance().createMetadata(data.getType());
      this.renderHeaders(metadata);
      this.renderDataLines(data);
    }

    // if password is null, encryption will not be applied.
    this.resource = this.writeWithEncryption(stream, password);
  }

}
