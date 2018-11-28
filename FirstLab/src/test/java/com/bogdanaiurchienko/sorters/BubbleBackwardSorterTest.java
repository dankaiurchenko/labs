package com.bogdanaiurchienko.sorters;

import org.junit.Test;

public class BubbleBackwardSorterTest {

  @Test(timeout = 10000, expected = NullPointerException.class)
  public void sort() {
    new BubbleBackwardSorter().sort(null);
  }
}