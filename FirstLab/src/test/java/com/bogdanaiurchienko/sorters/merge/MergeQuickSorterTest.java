package com.bogdanaiurchienko.sorters.merge;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MergeQuickSorterTest {
  private MergeQuickSorter sorter;
  @Before
  public void setUp() {
    sorter = new MergeQuickSorter();
  }

  @After
  public void tearDown() {
    sorter = null;
  }


  @Test(timeout = 1000, expected = IndexOutOfBoundsException.class)
  public void sortPart() {
    sorter.sortPart(new int[]{1, 2, 3}, 2, 7);
  }

  @Test(timeout = 1000, expected = NullPointerException.class)
  public void sort() {
    sorter.sort(null);

  }
}