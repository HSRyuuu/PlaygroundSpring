package com.hsryuuu.base.file;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

@Slf4j
public class CommonFileUtils {

  /**
   * 파일 다운로드용 HttpHeader
   * @param fileName 파일명
   * @param contentLength 파일 크기
   * @return HttpHeaders
   */
  public static HttpHeaders fileDownloadHttpHeaders(String fileName, long contentLength) {
    HttpHeaders headers = new HttpHeaders();
    String encodedFileName=  URLEncoder.encode(fileName, StandardCharsets.UTF_8);
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentLength(contentLength);
    headers.set(HttpHeaders.CONTENT_DISPOSITION,
        String.format("attachment; filename=\"%s\"; filename*=UTF-8''%s", encodedFileName, encodedFileName));
    return headers;
  }

  /**
   * 디렉터리 생성
   * @param dir 디렉터리 path
   * @return 결과 boolean
   */
  public static Boolean createDirectories(String dir) {
    try {
      Path fileLocation = Paths.get(dir).toAbsolutePath().normalize();
      Files.createDirectories(fileLocation);
    } catch (IOException e) {
      throw new RuntimeException(String.format("파일 디렉터리 생성 실패: dir=%s", dir), e);
    }
    return Boolean.TRUE;
  }

  public static Boolean isAllowedExtension(String fileName, List<String> allowedExtensions) {
    return new HashSet<>(allowedExtensions)
        .stream()
        .anyMatch(ext -> ext.equalsIgnoreCase(getExtension(fileName)));
  }

  public static String getExtension(String fileName) {
    // String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
    return Optional.ofNullable(StringUtils.getFilenameExtension(fileName))
        .orElseThrow(() -> new RuntimeException("파일에 확장자가 없습니다. fileName=" + fileName));
  }

  public static String getFilenameExcludeExtension(String fileName) {
    return fileName.substring(0, fileName.lastIndexOf("."));
  }

  /**
   * 바이트 사이즈를 단위를 붙인 파일 크기 문자열로 변환
   * @param byteSize
   * @return
   */
  public static String byteToFormat(long byteSize) {
    List<String> units = Arrays.asList("B", "KB", "MB", "GB");
    double size = (double) byteSize;
    int idx = 0;
    while (size >= 1024 && idx < units.size() - 1) {
      size /= 1024;
      idx++;
    }
    DecimalFormat df = new DecimalFormat("#,###.##");
    return df.format(size) + " " + units.get(idx);
  }

  public static void main(String[] args) {
    long byteSize = 999;
    log.info("retFormat-1 : {} => {}", byteSize, byteToFormat(byteSize));
    byteSize = 10342;
    log.info("retFormat-2 : {} => {}", byteSize, byteToFormat(byteSize));
    byteSize = 1024 * 1024;
    log.info("retFormat-3 : {} => {}", byteSize, byteToFormat(byteSize));
    byteSize = 10485760;
    log.info("retFormat-4 : {} => {}", byteSize, byteToFormat(byteSize));
    byteSize = 104857600;
    log.info("retFormat-5 : {} => {}", byteSize, byteToFormat(byteSize));
    byteSize = 1024 * 1024 * 1024;
    log.info("retFormat-6 : {} => {}", byteSize, byteToFormat(byteSize));
    byteSize = 1979991824;
    log.info("retFormat-7 : {} => {}", byteSize, byteToFormat(byteSize));
  }

}
