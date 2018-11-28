package com.bogdanaiurchienko.fillers;

import org.junit.Assert;
import org.junit.Test;


public class FillerTest {

  @Test(timeout = 100)
  public void getRandomArrayIsArrayRandomIsArraySorted() {
    Assert.assertFalse(isArraySorted(Filler.getRandomArray(50, 50)));
  }

  @Test(timeout = 100)
  public void getRandomArrayIsArrayEmpty() {
    Assert.assertEquals(0, Filler.getRandomArray(50, -50).length);
  }

  @Test(timeout = 100)
  public void getSortedArrayIsArrayDemandedLength() {
    Assert.assertEquals(50, Filler.getSortedArray(50, 8).length);
  }

  @Test(timeout = 100)
  public void getSortedArrayIsArraySorted() {
    Assert.assertTrue(isArraySorted(Filler.getSortedArray(20, 8)));
  }

  @Test(timeout = 100)
  public void getSortedArrayIsArrayEmpty() {
    Assert.assertEquals(0, Filler.getSortedArray(0, 8).length);
  }

  @Test(timeout = 100)
  public void getSortedArrayWithRandomEndIsArraySorted() {
    Assert.assertFalse(isArraySorted(Filler.getSortedArrayWithRandomEnd(6, 10)));
  }

  @Test(timeout = 100)
  public void getSortedArrayWithRandomEndIsArrayEmpty() {
    Assert.assertEquals(0, Filler.getSortedArrayWithRandomEnd(0, 8).length);
  }

  @Test(timeout = 100)
  public void getReverseSortedArrayIsArraySortedAsc() {
    Assert.assertFalse(isArraySorted(Filler.getReverseSortedArray(4, 6)));
  }

  @Test(timeout = 100)
  public void getReverseSortedArrayIsArrayEmpty() {
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