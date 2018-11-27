package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.BubbleBackwardSorter;
import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;

import java.util.Arrays;

/**
 * Sorts the array using merge method of half partition and sorting the parts with BubbleBackwardSorter
 * @see com.bogdanaiurchienko.sorters.BubbleBackwardSorter
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge bubble backward sort")
@SuppressWarnings("unused")
public class MergeBackwardBubbleSorter extends AbstractMergeSorter {

  /**
   *
   * @param arrayToSort array to sort
   * @param low the beginning of the part to sort
   * @param high the end of the part to sort
   */
  protected void sortPart(int[] arrayToSort, int low, int high){
    BubbleBackwardSorter sorter = new BubbleBackwardSorter();
    int[] buf = Arrays.copyOfRange(arrayToSort, low, high+1);
    System.arraycopy(sorter.sort(buf), 0, arrayToSort, low, buf.length);
  }

}
