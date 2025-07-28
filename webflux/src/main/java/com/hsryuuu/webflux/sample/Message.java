package com.hsryuuu.webflux.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class Message {
  private Integer key;
  private String message;
}