package com.example.gopherslanguagetranslator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class WordResponse {

  @Getter
  @JsonProperty("gopher-word")
  private final String gopherWord;
}
