package com.bogdanaiurchienko.analyzer;

import org.junit.Test;

public class AnalyzerTest {

  @Test(timeout = 10000, expected = Exception.class)
  public void analyzeAllArrayTypes() {
    new Analyzer(new int[] {0}).analyzeAllArrayTypes("some.random.non.existing.package");
  }
}