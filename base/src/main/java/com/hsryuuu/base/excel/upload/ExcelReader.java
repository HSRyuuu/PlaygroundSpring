package com.hsryuuu.base.excel.upload;

import com.hsryuuu.base.excel.util.ExcelUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/*
 * Excel 파일을 읽어온다.
 */
@Slf4j
public class ExcelReader {
  public static List<Map<String, Object>> read(ExcelReadOption excelReadOption) {
    // Workbook : Apache POI 라이브러리에서 Excel 파일 전체를 나타내는 객체
    Workbook workbook = ExcelUtils.getWorkbook(excelReadOption.getFile());
    // Sheet : 엑셀 파일의 시트. 0번부터 시작한다.
    Sheet sheet = workbook.getSheetAt(excelReadOption.getSheetNum());
    // Row: 엑셀 파일의 행을 나타낸다. 0부터 시작한다.
    Row headerRow = sheet.getRow(0);

    List<Map<String, Object>> result = new ArrayList<>();

    int startRowNum = excelReadOption.getStartRow();
    int lastRowNum = sheet.getLastRowNum();
    // 각 Row만큼 반복
    for (int rowIndex = startRowNum; rowIndex <= lastRowNum ; rowIndex++) {
      Row row = sheet.getRow(rowIndex);
      if(row == null){
        continue;
      }

      int cellCount = row.getLastCellNum();
      Map<String, Object> rowData = new HashMap<>();

      // rowData 추출
      for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
        Cell cell = row.getCell(cellIndex);
        // 현재 Cell의 Header 이름을 가져온다.
        String cellName = ExcelCellConverter.getStringValue(headerRow.getCell(cellIndex));
        // 추출 대상 컬럼이 아닐 경우 skip
        if (!excelReadOption.getOutputColumns().contains(cellName)) {
          continue;
        }
        rowData.put(cellName, ExcelCellConverter.getObjectValue(cell));
      } // rowData 추출 끝

      result.add(rowData);
    }
    return result;
  }

}
