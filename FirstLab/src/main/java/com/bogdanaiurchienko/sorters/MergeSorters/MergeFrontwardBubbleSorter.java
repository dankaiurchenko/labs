package com.bogdanaiurchienko.sorters.MergeSorters;

import com.bogdanaiurchienko.sorters.BubbleFrontwardSorter;
import com.bogdanaiurchienko.sorters.MergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;

import java.util.Arrays;

@SorterAnnotation("Merge bubble frontward sort")
public class MergeFrontwardBubbleSorter extends MergeSorter {

  protected void sortPart(int[] arrayToSort, int low, int high){
    BubbleFrontwardSorter sorter = new BubbleFrontwardSorter();
    int[] buf = Arrays.copyOfRange(arrayToSort, low, high+1);
    System.arraycopy(sorter.sort(buf), 0, arrayToSort, low, buf.length);
  }
}

