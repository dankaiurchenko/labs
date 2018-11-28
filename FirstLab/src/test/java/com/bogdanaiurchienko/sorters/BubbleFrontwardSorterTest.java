package com.bogdanaiurchienko.sorters;

import org.junit.Test;

public class BubbleFrontwardSorterTest {

  @Test(timeout = 10000, expected = NullPointerException.class)
  public void sort() {
    new BubbleFrontwardSorter().sort(null);
  }
}