package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;
import com.bogdanaiurchienko.sorters.SystemSorter;

import java.util.Arrays;
/**
 * Sorts the array using merge method of half partition and sorting the parts with SystemSorter
 * @see com.bogdanaiurchienko.sorters.SystemSorter
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge system sort")
@SuppressWarnings("unused")
public class MergeSystemSorter extends AbstractMergeSorter {

  /**
   *
   * @param arrayToSort array to sort
   * @param low the beginning of the part to sort
   * @param high the end of the part to sort
   */
  protected void sortPart(int[] arrayToSort, int low, int high){
    SystemSorter sorter = new SystemSorter();
    int[] buf = Arrays.copyOfRange(arrayToSort, low, high+1);
    System.arraycopy(sorter.sort(buf), 0, arrayToSort, low, buf.length);
  }
}
