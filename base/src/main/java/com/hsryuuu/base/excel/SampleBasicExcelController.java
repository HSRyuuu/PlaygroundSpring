package com.hsryuuu.base.excel;


import com.hsryuuu.base.application.response.StandardResponse;
import com.hsryuuu.base.excel.constant.ExcelConstants;
import com.hsryuuu.base.excel.model.ExcelUserDto;
import com.hsryuuu.base.excel.sample.SampleBasicExcelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name= "엑셀 파일 처리", description = "")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/excel/basic")
public class SampleBasicExcelController {

  private final SampleBasicExcelService sampleBasicExcelService;

  /**
   * 엑셀 업로드 - 데이터 추출
   *
   * @param file .xlsx / .xls 파일
   * @return 추출 결과
   */
  @PostMapping("/extract")
  public StandardResponse<List<ExcelUserDto>> extractExcelData(@RequestParam("file") MultipartFile file) {
    return sampleBasicExcelService.extractExcelData(file);
  }

  /**
   * 엑셀 다운로드
   */
  @GetMapping("/download")
  public ResponseEntity<Resource> downloadExcel() {

    Resource result = sampleBasicExcelService.excelDownload();

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userList.xlsx")
        .contentType(ExcelConstants.MEDIA_TYPE_XLSX)
        .body(result);
  }

  /**
   * 엑셀 템플릿 다운로드
   */
  @GetMapping("/download/template")
  public ResponseEntity<Resource> downloadExcelTemplate() {
    Resource resource = sampleBasicExcelService.downloadExcelTemplate();
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userAddTempleate.xlsx")
        .contentType(ExcelConstants.MEDIA_TYPE_XLSX)
        .body(resource);
  }
}
