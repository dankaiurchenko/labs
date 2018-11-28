package com.bogdanaiurchienko.fillers;

import org.junit.Assert;
import org.junit.Test;


public class FillerTest {

  @Test(timeout = 100)
  public void getRandomArray() {
    Assert.assertFalse(isArraySorted(Filler.getRandomArray(4, 6)));
  }

  @Test(timeout = 100)
  public void getRandomArray2() {
    Assert.assertFalse(isArraySorted(Filler.getRandomArray(4, 3)));
  }

  @Test(timeout = 100, expected = Exception.class)
  public void getRandomArray3() {
    Filler.getRandomArray(4, -3);
  }

  @Test(timeout = 100)
  public void getSortedArray() {
    Assert.assertTrue(isArraySorted(Filler.getSortedArray(7, 8)));
  }

  @Test(timeout = 100)
  public void getSortedArray2() {
    Assert.assertTrue(isArraySorted(Filler.getSortedArray(20, 8)));
  }

  @Test(timeout = 100, expected = ArithmeticException.class)
  public void getSortedArray3() {
    Assert.assertFalse(isArraySorted(Filler.getSortedArray(0, 8)));
  }

  @Test(timeout = 100)
  public void getSortedArrayWithRandomEnd() {
    Assert.assertFalse(isArraySorted(Filler.getSortedArrayWithRandomEnd(6, 10)));
  }

  @Test(timeout = 100)
  public void getSortedArrayWithRandomEnd2() {
    Assert.assertTrue(isArraySorted(Filler.getSortedArrayWithRandomEnd(6, 0)));
  }

  @Test(timeout = 100)
  public void getReverseSortedArray() {
    Assert.assertFalse(isArraySorted(Filler.getReverseSortedArray(4, 6)));
  }

  @Test(timeout = 100)
  public void getReverseSortedArray2() {
    Assert.assertTrue(isArraySorted(Filler.getReverseSortedArray(4, 0)));
  }

  private boolean isArraySorted(int [] array){
    int i = 0;
    while (i < array.length - 1 && array[i] <= array[i+1]) {
      i++;
    }
    return (i == array.length - 1);
  }
}