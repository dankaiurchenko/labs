package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.fillers.Filler;
import com.bogdanaiurchienko.fillers.FillerException;
import com.bogdanaiurchienko.sorters.ArrayChecker;
import com.bogdanaiurchienko.sorters.SorterException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class MergeBackwardBubbleSorterTest {
  private MergeBackwardBubbleSorter sorter;
  @Before
  public void setUp() {
    sorter = new MergeBackwardBubbleSorter();
  }

  @After
  public void tearDown() {
    sorter = null;
  }

  @Test(timeout = 1000, expected = NullPointerException.class)
  public void sort() throws SorterException {
    sorter.sort(null);
  }

  @Test
  public void sortArray() throws FillerException, SorterException {
    Assert.assertTrue(ArrayChecker.isArraySorted(sorter.sort(Filler.getRandomArray(12, 100))));
  }
}