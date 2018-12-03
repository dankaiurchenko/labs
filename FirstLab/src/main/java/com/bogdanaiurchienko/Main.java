package com.bogdanaiurchienko;

import com.bogdanaiurchienko.analyzer.Analyzer;
import com.bogdanaiurchienko.analyzer.AnalyzerException;
import com.bogdanaiurchienko.output.PrinterException;

/**
 * @author Bogdana Iurchienko
 */
public class Main {

  public static void main(String[] args){
    int [] arrayLength = new int[]{1000, 2000, 4000, 8000};
    Analyzer analyzer = new Analyzer(arrayLength);
    try {
      analyzer.analyzeAllArrayTypes("com.bogdanaiurchienko.sorters");
    } catch (AnalyzerException | PrinterException e) {
      e.printStackTrace();
    }
//    System.out.println(Printer.name("name"));
  }
}
