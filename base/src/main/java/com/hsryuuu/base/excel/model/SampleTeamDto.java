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
@ExcelSheet(name = "Teams")
public class SampleTeamDto {
  @ExcelColumn(headerName = "팀번호")
  private int number;
  @ExcelColumn(headerName = "팀명")
  private String name;
  @ExcelColumn(headerName = "인원")
  private int members;
  @ExcelColumn(headerName = "그룹명")
  private String groupName;
  @ExcelColumn(headerName = "팀장")
  private String leaderName;

  @Builder
  public SampleTeamDto(int number, String name, int members, String groupName, String leaderName) {
    this.number = number;
    this.name = name;
    this.members = members;
    this.groupName = groupName;
    this.leaderName = leaderName;
  }

}
