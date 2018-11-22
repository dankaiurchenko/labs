package com.bogdanaiurchienko.sorters.MergeSorters;

import com.bogdanaiurchienko.sorters.MergeSorter;
import com.bogdanaiurchienko.sorters.QuickSorter;

public class MergeQuickSorter extends MergeSorter {

  protected void sortPart(int[] arrayToSort, int low, int high){
    QuickSorter sorter = new QuickSorter();
    sorter.quickSort(arrayToSort, low, high);
  }
}
