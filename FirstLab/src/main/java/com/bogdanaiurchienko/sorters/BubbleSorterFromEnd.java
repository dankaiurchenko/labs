package com.bogdanaiurchienko.sorters;

public class BubbleSorterFromEnd extends Sorter {

  int [] sortArray(int [] arrayToSort){
    for (int i = arrayToSort.length - 1; i > 0 ; i--) {
      for (int j = arrayToSort.length - i; j > 0 ; j--) {
        if (arrayToSort[j - 1] > arrayToSort[j]) {
          swap(arrayToSort, j,j-1);
        }
      }
    }
    return arrayToSort;
  }
}
