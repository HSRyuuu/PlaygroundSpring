package com.hsryuuu.base.file;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class FileInfoDto {
  private String fileName;
  private String originalFilename;
  private String filePath;
  private String mimeType;
  private String extension;
  private long fileSize;
}
