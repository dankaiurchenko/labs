package com.bogdanaiurchienko.fillers;

import org.junit.Assert;
import org.junit.Test;


public class FillerTest {

  @Test(timeout = 100)
  public void getRandomArrayIsArrayRandomIsArraySorted() throws FillerException {
    Assert.assertFalse(isArraySorted(Filler.getRandomArray(50, 50)));
  }

  @Test(timeout = 100, expected = FillerException.class)
  public void getRandomArrayIsArrayEmpty() throws FillerException {
    Assert.assertEquals(0, Filler.getRandomArray(50, -50).length);
  }

  @Test(timeout = 100)
  public void getSortedArrayIsArrayDemandedLength() throws FillerException {
    Assert.assertEquals(50, Filler.getSortedArray(50, 8).length);
  }

  @Test(timeout = 100)
  public void getSortedArrayIsArraySorted() throws FillerException {
    Assert.assertTrue(isArraySorted(Filler.getSortedArray(20, 8)));
  }

  @Test(timeout = 100, expected = FillerException.class)
  public void getSortedArrayIsArrayEmpty() throws FillerException {
    Assert.assertEquals(0, Filler.getSortedArray(0, 8).length);
  }

  @Test(timeout = 100)
  public void getSortedArrayWithRandomEndIsArraySorted() throws FillerException {
    Assert.assertFalse(isArraySorted(Filler.getSortedArrayWithRandomEnd(6, 10)));
  }

  @Test(timeout = 100, expected = FillerException.class)
  public void getSortedArrayWithRandomEndIsArrayEmpty() throws FillerException {
    Assert.assertEquals(0, Filler.getSortedArrayWithRandomEnd(0, 8).length);
  }

  @Test(timeout = 100)
  public void getReverseSortedArrayIsArraySortedAsc() throws FillerException {
    Assert.assertFalse(isArraySorted(Filler.getReverseSortedArray(4, 6)));
  }

  @Test(timeout = 100, expected = FillerException.class)
  public void getReverseSortedArrayIsArrayEmpty() throws FillerException {
    Assert.assertEquals(0, Filler.getReverseSortedArray(0, 8).length);
  }

  private boolean isArraySorted(int [] array){
    int i = 0;
    while (i < array.length - 1 && array[i] <= array[i+1]) {
      i++;
    }
    return (i == array.length - 1);
  }
}