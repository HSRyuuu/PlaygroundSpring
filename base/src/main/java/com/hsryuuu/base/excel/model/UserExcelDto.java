package com.hsryuuu.base.excel.model;


import com.hsryuuu.base.excel.annotation.ExcelColumn;
import com.hsryuuu.base.excel.annotation.ExcelSheet;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ExcelSheet(name = "Users")
public class UserExcelDto {
  @ExcelColumn(headerName = "이름")
  private String name;
  @ExcelColumn(headerName = "이메일")
  private String email = "";
  @ExcelColumn(headerName = "생년월일")
  private String birthday = "";
  @ExcelColumn(headerName = "가입일시")
  private String registrationDate = "";

}
