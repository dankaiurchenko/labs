package com.bogdanaiurchienko.sorters.MergeSorters;

import com.bogdanaiurchienko.sorters.BubbleSorterFromBeginning;
import com.bogdanaiurchienko.sorters.MergeSorter;

import java.util.Arrays;

public class MergeBubbleFromBeginningSorter extends MergeSorter {

  protected void sortPart(int[] arrayToSort, int low, int high){
    BubbleSorterFromBeginning sorter = new BubbleSorterFromBeginning();
    int[] buf = Arrays.copyOfRange(arrayToSort, low, high+1);
    System.arraycopy(sorter.sort(buf), 0, arrayToSort, low, buf.length);
  }
}

