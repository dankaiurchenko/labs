package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.BubbleFrontwardSorter;
import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;

/**
 * Sorts the array using merge method of half partition and sorting the parts with BubbleFrontwardSorter
 * @see com.bogdanaiurchienko.sorters.BubbleFrontwardSorter
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge bubble frontward sort")
@SuppressWarnings("unused")
public class MergeFrontwardBubbleSorter extends AbstractMergeSorter {

  @SuppressWarnings("WeakerAccess")
  public MergeFrontwardBubbleSorter() {
  }

  private MergeFrontwardBubbleSorter(int[] subArrayToSort) {
    super(subArrayToSort);
    thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    new BubbleFrontwardSorter().sort(subArrayToSort);
  }


  @Override
  protected Thread getNewThread(int[] subArrayToSort) {
    return new MergeFrontwardBubbleSorter(subArrayToSort).getThread();
  }
}

