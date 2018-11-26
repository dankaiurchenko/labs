package com.bogdanaiurchienko.sorters.MergeSorters;

import com.bogdanaiurchienko.sorters.MergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;
import com.bogdanaiurchienko.sorters.SystemSorter;

import java.util.Arrays;

@SorterAnnotation("Merge system sort")
public class MergeSystemSorter extends MergeSorter {

  protected void sortPart(int[] arrayToSort, int low, int high){
    SystemSorter sorter = new SystemSorter();
    int[] buf = Arrays.copyOfRange(arrayToSort, low, high+1);
    System.arraycopy(sorter.sort(buf), 0, arrayToSort, low, buf.length);
  }
}
