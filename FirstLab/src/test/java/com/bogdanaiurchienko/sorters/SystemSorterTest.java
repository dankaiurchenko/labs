package com.bogdanaiurchienko.sorters;

import org.junit.Test;

public class SystemSorterTest {

  @Test(timeout = 1000, expected = NullPointerException.class)
  public void sort() {
    new SystemSorter().sort(null);
  }
}