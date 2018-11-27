package com.bogdanaiurchienko.sorters;

import java.util.Arrays;
/**
 * Sorter class that uses System method to sort the array.
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("System sort")
public class SystemSorter extends AbstractSorter {

  /**
   *
   * @param arrayToSort array of int to be sorted
   * @return sorted array
   */
  public int [] sort(int[] arrayToSort){
    Arrays.sort(arrayToSort);
    return arrayToSort;
  }
}
