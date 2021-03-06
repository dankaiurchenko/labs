package com.bogdanaiurchienko.analyzer;

import com.bogdanaiurchienko.output.PrinterException;
import com.bogdanaiurchienko.sorters.SorterException;
import org.junit.Assert;
import org.junit.Test;

public class AnalyzerTest {

  @Test(timeout = 10000)
  public void analyzeAllArrayTypes() throws AnalyzerException, PrinterException, SorterException {
    new Analyzer(new int[] {10, 50}).analyzeAllArrayTypes("com.bogdanaiurchienko.sorters");
  }

  @Test(timeout = 10000)
  public void analyzeAllArrayTypes2() throws AnalyzerException {
    Assert.assertEquals(5, new Analyzer(new int[] {-3, -1, 0, -54, 0}).getArrayLength().length);
  }

  @Test(timeout = 10000, expected = AnalyzerException.class)
  public void analyzeAllArrayTypes3() throws AnalyzerException, PrinterException, SorterException {
    new Analyzer(new int[] {10, 50}).analyzeAllArrayTypes("some.random.package");
  }

  @Test(timeout = 10000, expected = AnalyzerException.class)
  public void analyzeAllArrayTypes4() throws AnalyzerException, PrinterException, SorterException {
    new Analyzer(new int[] {}).analyzeAllArrayTypes("some.random.package");
  }
}