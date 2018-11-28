package com.bogdanaiurchienko.sorters;

import org.junit.Test;

public class QuickSorterTest {

  @Test(timeout = 1000, expected = NullPointerException.class)
  public void sort() {
    new QuickSorter().sort(null);
  }

  @Test(timeout = 1000, expected = IndexOutOfBoundsException.class)
  public void sort2() {
    new QuickSorter().quickSort(new int[]{1, 2, 3}, 9, -1);
  }
}