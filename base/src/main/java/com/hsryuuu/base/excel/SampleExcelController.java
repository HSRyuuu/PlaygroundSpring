package com.hsryuuu.base.excel;


import com.hsryuuu.base.application.response.StandardResponse;
import com.hsryuuu.base.excel.constant.ExcelConstants;
import com.hsryuuu.base.excel.model.SampleUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name= "엑셀 파일 처리", description = "Excel 파일 업로드 / 다운로드 / 샘플 다운로드")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/excel")
public class SampleExcelController {

  private final SampleExcelService sampleExcelService;
  private final BasicPoiExcelService basicPoiExcelService;

  @Operation(summary = "엑셀 업로드 - 데이터 추출(커스텀 모듈 사용)", description = "소스코드 내의 SampleData 목록 데이터 추출")
  @PostMapping(value = "/extract", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public List<Map<String, Object>> extractExcelData(@RequestPart("file") MultipartFile file) {
    return sampleExcelService.extractExcelData(file);
  }

  @Operation(summary = "엑셀 다운로드(커스텀 모듈 사용)", description = "소스코드 내의 List<SampleUserDto> 목록 엑셀 다운로드")
  @GetMapping("/download")
  public ResponseEntity<Resource> downloadExcel(@RequestParam(required = false, defaultValue = "5") Integer maxRow) throws Exception {
    Resource result = sampleExcelService.downloadExcel(maxRow);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userList.xlsx")
        .contentType(ExcelConstants.MEDIA_TYPE_XLSX)
        .body(result);
  }

  @Operation(summary = "multi-sheet 엑셀 다운로드(커스텀 모듈 사용)", description = "소스코드 내의 List<SampleUserDto>, List<SampleTeamDto> 목록 엑셀 다운로드")
  @GetMapping("/download/multi-sheet")
  public ResponseEntity<Resource> downloadMultiSheetExcel(@RequestParam(required = false, defaultValue = "5") Integer maxRow ) throws Exception {
    Resource resource = sampleExcelService.downloadMultiSheetExcel(maxRow);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userList.xlsx")
        .contentType(ExcelConstants.MEDIA_TYPE_XLSX)
        .body(resource);
  }

  @Operation(summary = "엑셀 업로드 - 데이터 추출 (Apache POI 직접 사용)", description = "소스코드 내의 SampleData 목록 데이터 추출")
  @PostMapping(value = "/poi/extract", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public StandardResponse<List<SampleUserDto>> extractExcelDataByPoi(@RequestPart("file") MultipartFile file) {
    return basicPoiExcelService.extractExcelData(file);
  }


  @Operation(summary = "엑셀 다운로드 (Apache POI 직접 사용)", description = "소스코드 내의 List<SampleUserDto> 목록 엑셀 다운로드")
  @GetMapping("/poi/download")
  public ResponseEntity<Resource> downloadExcelByPoi() {
    Resource result = basicPoiExcelService.excelDownload();
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userList.xlsx")
        .contentType(ExcelConstants.MEDIA_TYPE_XLSX)
        .body(result);
  }

  @Operation(summary = "엑셀 템플릿 다운로드 (Apache POI 직접 사용)", description = "소스코드 내의 SampleData 목록 엑셀 다운로드")
  @GetMapping("/poi/download/template")
  public ResponseEntity<Resource> downloadExcelTemplateByPoi() {
    Resource resource = basicPoiExcelService.downloadExcelTemplate();
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userAddTempleate.xlsx")
        .contentType(ExcelConstants.MEDIA_TYPE_XLSX)
        .body(resource);
  }
}
