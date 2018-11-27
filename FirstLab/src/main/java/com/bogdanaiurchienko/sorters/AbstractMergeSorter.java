package com.bogdanaiurchienko.sorters;

/**
 * Parent class to all merge sorters with method of half partition that can be analyzed by utility.
 * @author Bogdana Iurchienko
 */
public abstract class AbstractMergeSorter extends AbstractSorter {

  public int[] sort(int[] arrayToSort){
    this.mergeSort(arrayToSort, arrayToSort.length - 1);
    return arrayToSort;
  }

  /**
   * Divides <b>arrayToSort</b> into 2 halves, sorts each half with another sorter. <br>
   *   Then merges two parts into one array.
   * @see AbstractMergeSorter#sortPart(int[], int, int)
   * @param arrayToSort array to sort
   * @param high index of the last element to sort
   */
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

  /**
   * Sorts the part (elements with indexes from low to high) of the arrayToSort.
   * @param arrayToSort array to sort
   * @param low the beginning of the part to sort
   * @param high the end of the part to sort
   */
  abstract protected void sortPart(int[] arrayToSort, int low, int high);

}
