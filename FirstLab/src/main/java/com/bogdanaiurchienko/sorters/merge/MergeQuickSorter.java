package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.QuickSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;


/**
 * Sorts the array using merge method of half partition and sorting the parts with QuickSorter
 * @see com.bogdanaiurchienko.sorters.QuickSorter
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge quick sort")
@SuppressWarnings("unused")
public class MergeQuickSorter extends AbstractMergeSorter {

  @SuppressWarnings("WeakerAccess")
  public MergeQuickSorter() {
  }

  private MergeQuickSorter(int[] subArrayToSort) {
    super(subArrayToSort);
    thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    new QuickSorter().sort(subArrayToSort);
  }

  @Override
  protected Thread getNewThread(int[] subArrayToSort) {
    return new MergeQuickSorter(subArrayToSort).getThread();
  }
}
