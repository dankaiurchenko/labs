package com.bogdanaiurchienko.sorters;

public class ArrayChecker {
  public static boolean isArraySorted(int [] array){
    int i = 0;
    while (i < array.length - 1 && array[i] <= array[i+1]) {
      i++;
    }
    return (i == array.length - 1);
  }
}
