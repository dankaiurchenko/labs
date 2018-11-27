package com.bogdanaiurchienko.sorters;
/**
 * Sorter class that sorts array with bubble method.
 * Each iteration begins from the first element and goes to the last one.
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Bubble frontward sort")
public class BubbleFrontwardSorter extends AbstractBubbleSorter {

  /**
   *
   * @param arrayToSort array of int to be sorted
   * @return sorted array
   */
  public int [] sort(int [] arrayToSort){
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
