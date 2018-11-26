package com.bogdanaiurchienko.sorters;

import java.util.Arrays;

@SorterAnnotation("System sort")
public class SystemSorter extends AbstractSorter {

  int [] sortArray(int[] arrayToSort){
    Arrays.sort(arrayToSort);
    return arrayToSort;
  }
}
