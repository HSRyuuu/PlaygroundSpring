package com.hsryuuu.base.excel.model;


import com.hsryuuu.base.excel.annotation.ExcelColumn;
import com.hsryuuu.base.excel.annotation.ExcelSheet;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ExcelSheet(name = "Users")
public class ExcelUserDto {
  @ExcelColumn(headerName = "순번")
  private int number; // 순번
  @ExcelColumn(headerName = "이름")
  private String name; // 이름
  @ExcelColumn(headerName = "나이")
  private int age; // 나이
  @ExcelColumn(headerName = "성별")
  private String gender; // 성별
  @ExcelColumn(headerName = "연락처")
  private String contact; // 연락처

  @Builder
  public ExcelUserDto(int number, String name, int age, String gender, String contact) {
    this.number = number;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.contact = contact;
  }

}
