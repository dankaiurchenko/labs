package com.bogdanaiurchienko.sorters;
/**
 * Sorter class that sorts array with bubble method.
 * Each iteration begins from the last element and goes to the first one.
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Bubble backward sort")
public class BubbleBackwardSorter extends AbstractBubbleSorter {

  /**
   *
   * @param arrayToSort array of int to be sorted
   * @return sorted array
   */
  public int [] sort(int [] arrayToSort){
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
