package com.hsryuuu.base.excel.download;


import com.hsryuuu.base.excel.util.ReflectionUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.util.List;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;

public abstract class BaseSXSSFExcelFile implements ExcelFile {
  protected static final int ROW_ACCESS_WINDOW_SIZE = 1000;
  protected static final int ROW_START_INDEX = 0;
  protected static final int COLUMN_START_INDEX = 0;

  protected SXSSFWorkbook workbook;
  protected Sheet sheet;

  public BaseSXSSFExcelFile() {
    this.workbook = new SXSSFWorkbook(ROW_ACCESS_WINDOW_SIZE);
  }

  protected void renderHeaders(ExcelMetaData excelMetadata) {
    this.sheet = workbook.createSheet(excelMetadata.getSheetName());
    Row row = sheet.createRow(ROW_START_INDEX);
    int columnIndex = COLUMN_START_INDEX;
    CellStyle style = this.createCellStyle(workbook, true);

    for (String fieldName : excelMetadata.getDataFieldNames()) {
      createCell(row, columnIndex++, excelMetadata.getHeaderName(fieldName), style);
    }
  }

  protected void renderDataLines(ExcelSheetData data) {
    CellStyle style = this.createCellStyle(workbook, false);
    int rowIndex = ROW_START_INDEX + 1;
    List<Field> fields = ReflectionUtils.getAllFields(data.getType());
    for (Object record : data.getDataList()) {
      Row row = sheet.createRow(rowIndex++);
      int columnIndex = COLUMN_START_INDEX;

      try {
        for (Field field : fields) {
          field.setAccessible(true);
          createCell(row, columnIndex++, field.get(record), style);
        }
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Error accessing data field rendering data lines.", e);
      }
    }
  }

  @Override
  public void write(ByteArrayOutputStream outputStream) throws IOException {
    workbook.write(outputStream);
  }

  @Override
  public ByteArrayResource writeWithEncryption(ByteArrayOutputStream stream, String password) throws IOException {
    if (password == null) {
      write(stream);
    } else {
      POIFSFileSystem fileSystem = new POIFSFileSystem();
      OutputStream encryptorStream = getEncryptorStream(fileSystem, password);
      workbook.write(encryptorStream);
      encryptorStream.close(); // this is necessary before writing out the FileSystem

      fileSystem.writeFilesystem(stream); // write the encrypted file to the response stream
      fileSystem.close();
    }

    workbook.close();
    workbook.dispose();

    return new ByteArrayResource(stream.toByteArray());
  }

  private OutputStream getEncryptorStream(POIFSFileSystem fileSystem, String password) {
    try {
      Encryptor encryptor = new EncryptionInfo(EncryptionMode.agile).getEncryptor();
      encryptor.confirmPassword(password);
      return encryptor.getDataStream(fileSystem);
    } catch (IOException | GeneralSecurityException e) {
      throw new RuntimeException("Failed to obtain encrypted data stream from POIFSFileSystem.");
    }
  }
}
