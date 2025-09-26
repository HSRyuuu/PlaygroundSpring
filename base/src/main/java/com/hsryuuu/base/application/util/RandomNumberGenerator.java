package com.hsryuuu.base.application.util;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {

  /**
   * n자리 랜덤 숫자 생성
   * @param maxDigit 랜덤 숫자 자릿수 n
   * @return 랜덤숫자 문자열 반환
   */
  public static String generateSecureRandomDigitCode(int maxDigit) {
    SecureRandom random = new SecureRandom();
    StringBuilder code = new StringBuilder();
    for (int i = 0; i < maxDigit; i++) {
      code.append(random.nextInt(10)); // 0~9까지의 숫자 추가
    }
    return code.toString();
  }

  public static String generateThreadLocalRandomDigitCode(int startDigit, int endDigit) {
    int code = ThreadLocalRandom.current().nextInt(100000, 1000000); // 100000 ~ 999999
    return String.valueOf(code);
  }


  public static void main(String[] args) {
    int maxLoop = 10;
    String sixDigitCode = null;
    for (int i = 0; i < maxLoop; i++) {
      sixDigitCode = generateSecureRandomDigitCode(6);
      System.out.println("6자리 SecureRandom 인증번호: " + sixDigitCode); // 예: 123456
    }

    for (int i = 0; i < maxLoop; i++) {
      sixDigitCode = generateThreadLocalRandomDigitCode(100000, 1000000);
      System.out.println("6자리 ThreadLocalRandom 인증번호: " + sixDigitCode); // 예: 123456
    }
  }
}
