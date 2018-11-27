package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.BubbleBackwardSorter;
import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;

import java.util.Arrays;

/**
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge bubble backward sort")
@SuppressWarnings("unused")
public class MergeBackwardBubbleSorter extends AbstractMergeSorter {

  protected void sortPart(int[] arrayToSort, int low, int high){
    BubbleBackwardSorter sorter = new BubbleBackwardSorter();
    int[] buf = Arrays.copyOfRange(arrayToSort, low, high+1);
    System.arraycopy(sorter.sort(buf), 0, arrayToSort, low, buf.length);
  }

}
