package com.bogdanaiurchienko.sorters;

import com.bogdanaiurchienko.fillers.Filler;
import com.bogdanaiurchienko.fillers.FillerException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SystemSorterTest {
  private SystemSorter sorter;
  @Before
  public void setUp() {
    sorter = new SystemSorter();
  }

  @After
  public void tearDown() {
    sorter = null;
  }

  @Test(expected = NullPointerException.class)
  public void sort() {
    sorter.sort(null);
  }

  @Test(timeout = 1000)
  public void sortArray() throws FillerException {
    Assert.assertTrue(ArrayChecker.isArraySorted(sorter.sort(Filler.getRandomArray(50, 100))));
  }
}