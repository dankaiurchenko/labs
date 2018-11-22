package com.bogdanaiurchienko.sorters;

public class QuickSorter extends Sorter {

  int[] sortArray(int[] arrayToSort){
    this.quickSort(arrayToSort, 0, arrayToSort.length - 1);
    return arrayToSort;
  }

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
