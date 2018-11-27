package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.BubbleFrontwardSorter;
import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;

import java.util.Arrays;
/**
 * Sorts the array using merge method of half partition and sorting the parts with BubbleFrontwardSorter
 * @see com.bogdanaiurchienko.sorters.BubbleFrontwardSorter
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge bubble frontward sort")
@SuppressWarnings("unused")
public class MergeFrontwardBubbleSorter extends AbstractMergeSorter {

  /**
   *
   * @param arrayToSort array to sort
   * @param low the beginning of the part to sort
   * @param high the end of the part to sort
   */
  protected void sortPart(int[] arrayToSort, int low, int high){
    BubbleFrontwardSorter sorter = new BubbleFrontwardSorter();
    int[] buf = Arrays.copyOfRange(arrayToSort, low, high+1);
    System.arraycopy(sorter.sort(buf), 0, arrayToSort, low, buf.length);
  }
}

