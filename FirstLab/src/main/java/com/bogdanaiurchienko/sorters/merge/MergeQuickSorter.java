package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.QuickSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;
/**
 * Sorts the array using merge method of half partition and sorting the parts with SystemSorter
 * @see com.bogdanaiurchienko.sorters.QuickSorter
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge quick sort")
@SuppressWarnings("unused")
public class MergeQuickSorter extends AbstractMergeSorter {

  protected void sortPart(int[] arrayToSort, int low, int high){
    QuickSorter sorter = new QuickSorter();
    sorter.quickSort(arrayToSort, low, high);
  }
}
