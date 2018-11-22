package com.bogdanaiurchienko.sorters;

import java.util.Arrays;

public class SystemSorter extends Sorter {

  int [] sortArray(int[] arrayToSort){
    Arrays.sort(arrayToSort);
    return arrayToSort;
  }
}
