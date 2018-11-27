package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.QuickSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;
/**
 * Sorts the array using merge method of half partition and sorting the parts with QuickSorter
 * @see com.bogdanaiurchienko.sorters.QuickSorter
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge quick sort")
@SuppressWarnings("unused")
public class MergeQuickSorter extends AbstractMergeSorter {

  /**
   *
   * @param arrayToSort array to sort
   * @param low the beginning of the part to sort
   * @param high the end of the part to sort
   */
  protected void sortPart(int[] arrayToSort, int low, int high){
    QuickSorter sorter = new QuickSorter();
    sorter.quickSort(arrayToSort, low, high);
  }
}
