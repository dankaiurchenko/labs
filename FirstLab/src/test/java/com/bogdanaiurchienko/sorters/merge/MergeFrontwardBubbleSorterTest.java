package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.fillers.Filler;
import com.bogdanaiurchienko.fillers.FillerException;
import com.bogdanaiurchienko.sorters.ArrayChecker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MergeFrontwardBubbleSorterTest {
  private MergeFrontwardBubbleSorter sorter;
  @Before
  public void setUp() {
    sorter = new MergeFrontwardBubbleSorter();
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

  @Test
  public void sortArray() throws FillerException {
    Assert.assertTrue(ArrayChecker.isArraySorted(sorter.sort(Filler.getRandomArray(50, 100))));
  }
}