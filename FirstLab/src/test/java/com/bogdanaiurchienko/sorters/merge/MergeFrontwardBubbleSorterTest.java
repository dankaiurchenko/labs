package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.fillers.Filler;
import com.bogdanaiurchienko.fillers.FillerException;
import com.bogdanaiurchienko.sorters.ArrayChecker;
import com.bogdanaiurchienko.sorters.SorterException;
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

  @Test(timeout = 1000, expected = NullPointerException.class)
  public void sort() throws SorterException {
    sorter.sort(null);
  }

  @Test
  public void sortArray() throws FillerException, SorterException {
    int[] array = Filler.getRandomArray(50, 100);
    int[] sortedArray = sorter.sort(array);
    Assert.assertTrue(ArrayChecker.isArraySorted(sortedArray));
  }

  @Test
  public void arrayEvenLength() throws FillerException, SorterException {
    int[] array = Filler.getRandomArray(50, 100);
    int[] sortedArray = sorter.sort(array);
    Assert.assertEquals(array.length, sortedArray.length);
  }

  @Test
  public void arrayUnevenLength() throws FillerException, SorterException {
    int[] array = Filler.getRandomArray(27, 100);
    int[] sortedArray = sorter.sort(array);
    Assert.assertEquals(array.length, sortedArray.length);
  }

}