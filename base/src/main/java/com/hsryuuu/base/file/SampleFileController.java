package com.hsryuuu.base.file;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sample/file")
public class SampleFileController {

  private static final String FILE_UPLOAD_DIR = "./temp/upload";
  private final SampleFileService sampleFileService;


  /**
   * 파일 업로드
   *
   * @param file 파일
   * @return 파일 정보
   */
  @PostMapping(value = {"/upload"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public FileInfoDto handleFileUpload(@RequestParam("file") MultipartFile file) {
    List<String> allowedExtensions = List.of("XLX", "XLSX");
    return sampleFileService.uploadFile(file, FILE_UPLOAD_DIR, allowedExtensions);
  }

  /**
   * 파일 다운로드
   *
   * @param filePath 파일 Path(샘플에서는, 상대경로)
   * @param fileName 파일명
   * @return 파일 정보
   */
  @Operation(summary = "파일 다운로드", description = "파일 다운로드을 실행한다.",
      parameters = {
          @Parameter(name = "filePath", description = "다운로드 파일 디렉토리",
              example = "temp/upload/sample"),
          @Parameter(name = "fileName", description = "다운로드 파일", example = "sample-file.xlsx")})
  @GetMapping(value = "/download")
  public ResponseEntity<ByteArrayResource> downloadFile(
      @RequestParam String filePath,
      @RequestParam String fileName) {
    String fileFullPath = String.format("%s/%s", filePath, fileName);
    byte[] data = sampleFileService.downloadFile(fileFullPath);
    ByteArrayResource resource = new ByteArrayResource(data);
    HttpHeaders httpHeaders = CommonFileUtils.fileDownloadHttpHeaders(fileName, data.length);
    return ResponseEntity.ok()
        .contentLength(data.length)
        .headers(httpHeaders)
        .body(resource);
  }

  /**
   * 파일 다운로드
   *
   * @return 파일 정보
   */
  @Operation(summary = "샘플파일 다운 (고정)", description = "파일 다운로드을 실행한다.")
  @GetMapping(value = "/download/sample")
  public ResponseEntity<ByteArrayResource> downloadSampleFile() {
    final String filePath = "static";
    final String fileName = "sample-file.xlsx";

    String fileFullPath = String.format("%s/%s", filePath, fileName);
    byte[] data = null;

    try {
      Path path = new ClassPathResource(fileFullPath).getFile().toPath();
      data = Files.readAllBytes(path);
    } catch (Exception e) {
      log.error("downloadFile Exception : " + e);
      return ResponseEntity.badRequest().contentLength(0).body(null);
    }

    ByteArrayResource resource = new ByteArrayResource(data);
    HttpHeaders httpHeaders = CommonFileUtils.fileDownloadHttpHeaders(fileName, data.length);
    return ResponseEntity.ok()
        .contentLength(data.length)
        .headers(httpHeaders)
        .body(resource);
  }

}
