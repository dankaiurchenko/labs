package com.bogdanaiurchienko.sorters.MergeSorters;

import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.QuickSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;

@SorterAnnotation("Merge quick sort")
public class MergeQuickSorter extends AbstractMergeSorter {

  protected void sortPart(int[] arrayToSort, int low, int high){
    QuickSorter sorter = new QuickSorter();
    sorter.quickSort(arrayToSort, low, high);
  }
}
