package com.bogdanaiurchienko.sorters.MergeSorters;

import com.bogdanaiurchienko.sorters.BubbleBackwardSorter;
import com.bogdanaiurchienko.sorters.MergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;

import java.util.Arrays;

@SorterAnnotation("Merge bubble backward sort")
public class MergeBackwardBubbleSorter extends MergeSorter {

  protected void sortPart(int[] arrayToSort, int low, int high){
    BubbleBackwardSorter sorter = new BubbleBackwardSorter();
    int[] buf = Arrays.copyOfRange(arrayToSort, low, high+1);
    System.arraycopy(sorter.sort(buf), 0, arrayToSort, low, buf.length);
  }

}
