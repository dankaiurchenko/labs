package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;
import com.bogdanaiurchienko.sorters.SystemSorter;

import java.util.Arrays;
/**
 * Sorts the array using merge method of half partition and sorting the parts with SystemSorter
 * @see com.bogdanaiurchienko.sorters.SystemSorter
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge system sort")
@SuppressWarnings("unused")
public class MergeSystemSorter extends AbstractMergeSorter {

  @SuppressWarnings("WeakerAccess")
  public MergeSystemSorter() {
  }

  private MergeSystemSorter(int[] subArrayToSort) {
    super(subArrayToSort);
    thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    new SystemSorter().sort(subArrayToSort);
  }

  @Override
  protected Thread getNewThread(int[] subArrayToSort) {
    return new MergeSystemSorter(subArrayToSort).getThread();
  }
}
