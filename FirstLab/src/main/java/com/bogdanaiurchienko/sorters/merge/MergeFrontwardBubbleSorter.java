package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.BubbleFrontwardSorter;
import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;

import java.util.Arrays;
/**
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge bubble frontward sort")
@SuppressWarnings("unused")
public class MergeFrontwardBubbleSorter extends AbstractMergeSorter {

  protected void sortPart(int[] arrayToSort, int low, int high){
    BubbleFrontwardSorter sorter = new BubbleFrontwardSorter();
    int[] buf = Arrays.copyOfRange(arrayToSort, low, high+1);
    System.arraycopy(sorter.sort(buf), 0, arrayToSort, low, buf.length);
  }
}

