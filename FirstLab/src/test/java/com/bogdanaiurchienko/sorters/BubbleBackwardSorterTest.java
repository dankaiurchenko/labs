package com.bogdanaiurchienko.sorters;

import com.bogdanaiurchienko.fillers.Filler;
import com.bogdanaiurchienko.fillers.FillerException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BubbleBackwardSorterTest {
  private BubbleBackwardSorter sorter;
  @Before
  public void setUp() {
    sorter = new BubbleBackwardSorter();
  }

  @After
  public void tearDown() {
    sorter = null;
  }


  @Test(timeout = 10000, expected = NullPointerException.class)
  public void sort() {
    sorter.sort(null);
  }

  @Test
  public void sortArray() throws FillerException {
    Assert.assertTrue(ArrayChecker.isArraySorted(sorter.sort(Filler.getRandomArray(50, 100))));
  }
}