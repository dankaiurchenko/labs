package com.bogdanaiurchienko.sorters;


public abstract class MergeSorter extends AbstractSorter {

  protected int[] sortArray(int[] arrayToSort){
    this.mergeSort(arrayToSort, arrayToSort.length - 1);
    return arrayToSort;
  }

  private void mergeSort(int[] arrayToSort, int high) {
    int mid = (high) / 2;
    sortPart(arrayToSort, 0, mid);
    sortPart(arrayToSort, mid + 1, high);
    int[] buf = new int[high + 1];//Arrays.copyOf(arrayToSort, arrayToSort.length);
    System.arraycopy(arrayToSort, 0, buf, 0, high + 1);
    int i = 0, j = mid + 1;
    for (int k = 0; k <= high; k++) {
      if (i > mid) {
        arrayToSort[k] = buf[j];
        j++;
      } else if (j > high) {
        arrayToSort[k] = buf[i];
        i++;
      } else if (buf[j] < buf[i]) {
        arrayToSort[k] = buf[j];
        j++;
      } else {
        arrayToSort[k] = buf[i];
        i++;
      }
    }

  }

  abstract protected void sortPart(int[] arrayToSort, int low, int high);

}
