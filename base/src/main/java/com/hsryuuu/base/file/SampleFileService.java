package com.hsryuuu.base.file;

import com.hsryuuu.base.application.util.constants.DateFormatterConstants;
import com.hsryuuu.base.application.util.DateUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class SampleFileService {
  private static final Long MAX_FILE_SIZE = 1000000L;

  /**
   * 파일 업로드 -  Multipart 파일을 입력받아 서버 내부 스토리지에 물리 파일 저장.
   * @param file MultipartFile
   * @param uploadDir 업로드 대상 위치
   * @param allowedExtensions 허용 Extension
   * @return
   */
  public FileInfoDto uploadFile(MultipartFile file, String uploadDir, List<String> allowedExtensions) {
    String originalFileName = file.getOriginalFilename();
    String mimeType = file.getContentType();
    String extension = CommonFileUtils.getExtension(originalFileName);

    if (!CommonFileUtils.createDirectories(uploadDir)) {
      throw new RuntimeException("업로드 디렉토리 생성 중 오류입니다.");
    }

    // 최대용량 체크
    if (file.getSize() > MAX_FILE_SIZE) {
      throw new RuntimeException(String.format("%s 이하 파일만 업로드 할 수 있습니다.", CommonFileUtils.byteToFormat(MAX_FILE_SIZE)));
    }

    // 확장자[EXTENSION] 체크
    if (allowedExtensions != null && !allowedExtensions.isEmpty()
        && !CommonFileUtils.isAllowedExtension(originalFileName, allowedExtensions)) {
      throw new RuntimeException(String.format("허용 가능한 확장자 목록[%s]에 없습니다.", String.join("_", allowedExtensions)));
    }

    // 저장 파일명을 중복방지 고유명으로 변경
    String timeStamp = DateUtils.getCurrentDateTime(DateFormatterConstants.DATE_YMDHMS_FORMATTER);
    String fullDir = String.format("%s/%s", uploadDir, timeStamp);
    String fileName = this.generateUniqueFileName(originalFileName, fullDir);
    Path filePath = Paths.get(fullDir + File.separator + fileName);

    // 서버 내부 스토리지에 업로드
    try {
      Files.copy(file.getInputStream(), filePath);
    } catch (IOException e) {
      throw new RuntimeException(String.format("Could not store file %s. Please try again!", originalFileName), e);
    }

    return FileInfoDto.builder()
        .fileName(fileName)
        .originalFilename(originalFileName)
        .filePath(filePath.toString())
        .mimeType(mimeType)
        .extension(extension)
        .fileSize(file.getSize())
        .build();
  }
  /**
   * 파일 다운로드
   * @param filePath 파일 위치
   * @return byte[] file
   */
  public byte[] downloadFile(String filePath) {
    byte[] data = null;
    try {
      Path path = Paths.get(filePath);
      data = Files.readAllBytes(path);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(String.format("파일 다운로드 중 오류가 발생했습니다."), e);
    }
    return data;
  }

  /**
   * 중복방지를 위한 파일 고유명 생성
   *
   * @param originalFileName 확장자
   * @return String 파일 고유이름
   */
  private String generateUniqueFileName(String originalFileName, String uploadDir) {
    // Random 객체 생성
    Random random = new Random();
    // 0 이상 100 미만의 랜덤한 정수 반환
    String randomNumber = String.format("%06d",random.nextInt(999999));
    if (!CommonFileUtils.createDirectories(uploadDir)) {
      throw new RuntimeException("업로드 하위 디렉토리 생성 중 오류입니다.");
    }

    String filename = CommonFileUtils.getFilenameExcludeExtension(originalFileName);
    String extension = CommonFileUtils.getExtension(originalFileName);

    return String.format("%s_%s.%s", filename, randomNumber, extension);
  }
}
