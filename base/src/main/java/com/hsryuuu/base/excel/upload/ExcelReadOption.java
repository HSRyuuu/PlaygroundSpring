package com.hsryuuu.base.excel.upload;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

/*
 * 엑셀 파일을 읽을 때 옵션을 설정
 */
@Data
@SuperBuilder
public class ExcelReadOption {
  private String filePath; // 엑셀 파일의 경로
  private MultipartFile file; // 엑셀 파일
  private List<String> outputColumns = new ArrayList<>(); // 추출할 컬럼명
  private int startRow = 1; // 추출을 시작할 행 번호
  private int sheetNum = 0; // 시트 번호
}
