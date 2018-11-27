package com.bogdanaiurchienko;

import com.bogdanaiurchienko.analyzer.Analyzer;

public class Main {

  public static void main(String[] args) {
    int [] arrayLength = new int[]{100, 1000, 10000};
    Analyzer analyzer = new Analyzer(arrayLength);
    analyzer.analyzeAllArrayTypes("com.bogdanaiurchienko.sorters");
  }
}
