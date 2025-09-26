package com.hsryuuu.base.application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 날짜 및 시간 관련 유틸리티 클래스
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.NONE)
public class DateUtils {

  /**
   * 현재 날짜를 지정된 형식의 문자열로 반환합니다.
   *
   * @param dateFormat 날짜 포맷 (예: "yyyyMMdd HH:mm:ss")
   * @return 현재 날짜 (예: "20250926 16:50:31")
   */
  public static String getCurrentDateTime(String dateFormat) {
    LocalDateTime today = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
    return today.format(formatter);
  }

  /**
   * 현재 날짜를 지정된 형식의 문자열로 반환합니다.
   *
   * @param dateFormat 날짜 포맷 (예: "yyyy-MM-dd")
   * @return 현재 날짜 (예: "2025-09-26")
   */
  public static String getCurrentDate(String dateFormat) {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
    return today.format(formatter);
  }

  /**
   * 현재 시간을 지정된 형식의 문자열로 반환합니다.
   *
   * @param timeFormat 시간 포맷 (예: "HH:mm:ss")
   * @return 현재 시간 (예: "16:50:31")
   */
  public static String getCurrentTime(String timeFormat) {
    LocalTime now = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);

    return now.format(formatter);
  }

  /**
   * 주어진 날짜를 기준으로 해당 주(Week)의 시작일(월요일)과 마지막일(일요일)을 반환
   */
  public static DateRange getWeekRange(LocalDate baseDate) {
    LocalDate start = baseDate.with(DayOfWeek.MONDAY);
    LocalDate end = baseDate.with(DayOfWeek.SUNDAY);
    return new DateRange(start, end);
  }

  /**
   * 주어진 날짜를 기준으로 해당 달(Month)의 시작일(1일)과 마지막일을 반환
   */
  public static DateRange getMonthRange(LocalDate baseDate) {
    LocalDate start = baseDate.withDayOfMonth(1);
    LocalDate end = baseDate.withDayOfMonth(baseDate.lengthOfMonth());
    return new DateRange(start, end);
  }
  /**
   * 날짜 구간(시작일, 종료일)을 나타내는 간단한 레코드
   */
  public record DateRange(LocalDate startDate, LocalDate endDate) {
  }

  public static Date stringToDate(String dateStr, String formatStr) {
    if (dateStr == null || dateStr.trim().isEmpty())
      return null;
    // 포맷터
    SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
    Date parsedDate = null;
    try {
      // 문자열 -> Date
      parsedDate = formatter.parse(dateStr);
    } catch (ParseException e) {
      log.error("String To Date Conversion Error : {}", e.getMessage());
    }

    return parsedDate;
  }

  /**
   * 기준 시간 리스트 중에 가장 최근의 시간을 반환합니다.
   *
   * @param baseTimes 기준 시간 리스트 (예: ["02:00", "05:00", ..., "17:00"])
   * @param timeFormat 시간 포맷 (예: "HH:mm")
   * @return 기준 시간 (예: "1700")
   */
  public static String getLatestTimeFromList(List<String> baseTimes, String timeFormat) {
    LocalTime now = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);

    return baseTimes.stream().filter(baseTime -> LocalTime.parse(baseTime, formatter).isBefore(now))
        .max(String::compareTo).orElse(null);
  }


  public static void main(String[] args) {

    String currentDateTime = getCurrentDateTime("yyyyMMdd HH:mm:ss");
    log.info("currentDateTime: {}", currentDateTime);
    String currentDate = getCurrentDate("yyyy-MM-dd");
    log.info("currentDate: {}", currentDate);
    String currentTime = getCurrentTime("HH:mm:ss");
    log.info("currentTime: {}", currentTime);
    DateRange weekRange = getWeekRange(LocalDate.now());
    log.info("weekRange: {}", weekRange);
    DateRange monthRange = getMonthRange(LocalDate.now());
    log.info("monthRange: {}", monthRange);
    Date date = stringToDate(LocalDateTime.now().toString(), "yyyy-MM-dd");
    log.info("stringToDate: {}", date);

    List<String> baseTimes = List.of("00:30", "09:40", "13:30", "17:50", "19:30", "21:25");
    log.info("baseTimes: {}", baseTimes);
    String latestTime = getLatestTimeFromList(baseTimes, "HH:mm");
    log.info("latestTime in baseTimes: {}", latestTime);

  }
}
