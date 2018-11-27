package com.bogdanaiurchienko.sorters;

/**
 * Sorter that uses Quick sort algorithm to sort the array
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Quick sort")
public class QuickSorter extends AbstractSorter {

  /**
   * Calls recursive function  to sort the array from the first element to the last
   * @param arrayToSort array of int to be sorted
   * @return sorted array
   */
  public int[] sort(int[] arrayToSort){
    this.quickSort(arrayToSort, 0, arrayToSort.length - 1);
    return arrayToSort;
  }

  /**
   * Recursive function that sorts the particular part of array from the low to high indexes.
   * @param arrayToSort array to sort
   * @param low beginning of the part to sort
   * @param high ending of the part to sort
   */
  public void quickSort(int arrayToSort[], int low, int high) {
    int i = low, j = high;
    int middleElement = arrayToSort[low + (high-low)/2];
    while (i <= j) {

      while (arrayToSort[i] < middleElement) {
        i++;
      }
      while (arrayToSort[j] > middleElement) {
        j--;
      }

      if (i <= j) {
        swap(arrayToSort, i, j);
        i++;
        j--;
      }
    }
    if (low < j)
      quickSort(arrayToSort, low, j);
    if (i < high)
      quickSort(arrayToSort, i, high);
  }

}
