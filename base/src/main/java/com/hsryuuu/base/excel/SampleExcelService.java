package com.hsryuuu.base.excel;

import com.hsryuuu.base.excel.constant.ExcelSampleData;
import com.hsryuuu.base.excel.download.SXSSFExcelFile;
import com.hsryuuu.base.excel.download.SXSSFMultiSheetExcelFile;
import com.hsryuuu.base.excel.download.ExcelSheetData;
import com.hsryuuu.base.excel.download.ExcelSheetDataGroup;
import com.hsryuuu.base.excel.model.SampleTeamDto;
import com.hsryuuu.base.excel.model.SampleUserDto;
import com.hsryuuu.base.excel.upload.ExcelReader;
import com.hsryuuu.base.excel.upload.ExcelReadOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleExcelService {

  /**
   * 엑셀 업로드 - 데이터 추출
   * @param file
   */
  public List<Map<String, Object>> extractExcelData(MultipartFile file) {
    List<String> columns = List.of(ExcelSampleData.SAMPLE_HEADERS);
    ExcelReadOption excelReadOption =
        ExcelReadOption.builder()
            .file(file)
            .outputColumns(columns)
            .startRow(2) // 헤더제외
            .build();
    try {
      List<Map<String, Object>> excelDataList = ExcelReader.read(excelReadOption);
      return excelDataList;
    } catch (Exception e) {
      log.error("엑셀 업로드 처리 중 에러 : {}", e);
    }
    return null;
  }

  /**
   * 엑셀 다운로드
   */
  public Resource downloadExcel(Integer maxRow) throws Exception {
    List<SampleUserDto> testDataList = new ArrayList<>();
    for (int i = 1; i <= maxRow; i++) {
      testDataList.add( SampleUserDto.builder()
          .number(i)
          .name("test-name"+ i)
          .age(i + 20)
          .gender(i % 2 == 0 ? "male" : "female")
          .contact("010-1234-" + String.format("%04d", i))
          .build());
    }

    // new SXSSFExcelFile(ExcelSheetData.of(userInfoList, UserExcelDto.class), response);
    SXSSFExcelFile excelFile =
        new SXSSFExcelFile(ExcelSheetData.of(testDataList, SampleUserDto.class));

    return excelFile.getResource();
  }

  /**
   * 엑셀 다운로드
   */
  public Resource downloadMultiSheetExcel(Integer maxRow) throws Exception {
    List<SampleUserDto> userList = new ArrayList<>();
    for (int i = 1; i <= maxRow; i++) {
      userList.add( SampleUserDto.builder()
          .number(i)
          .name("111-test-name"+ i)
          .age(i + 20)
          .gender(i % 2 == 0 ? "male" : "female")
          .contact("010-1234-" + String.format("%04d", i))
          .build());
    }
    List<SampleTeamDto> teamList = new ArrayList<>();
    for (int i = 1; i <= maxRow; i++) {
      teamList.add( SampleTeamDto.builder()
          .number(i)
          .name("222-test-name"+ i)
          .members(i)
          .groupName("group" + i)
          .leaderName("leader" + i)
          .build());
    }
    ExcelSheetData sheet1 = ExcelSheetData.of(userList, SampleUserDto.class);
    ExcelSheetData sheet2 = ExcelSheetData.of(teamList, SampleTeamDto.class);
    ExcelSheetDataGroup excelSheetDataGroup = ExcelSheetDataGroup.of(sheet1, sheet2);



    SXSSFMultiSheetExcelFile excelFile =
        new SXSSFMultiSheetExcelFile(excelSheetDataGroup);

    return excelFile.getResource();
  }

}
