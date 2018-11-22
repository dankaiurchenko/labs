package com.bogdanaiurchienko.sorters;


public class BubbleSorterFromBeginning extends Sorter {

  int [] sortArray(int [] arrayToSort){
    for (int i = 0; i < arrayToSort.length -1; i++) {
      for (int j = 0; j < arrayToSort.length - i - 1; j++) {
        if (arrayToSort[j] > arrayToSort[j + 1]) {
          swap(arrayToSort, j,j+1);
        }
      }
    }
    return arrayToSort;
  }
}
