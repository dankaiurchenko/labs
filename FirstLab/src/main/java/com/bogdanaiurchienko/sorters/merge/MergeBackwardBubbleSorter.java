package com.bogdanaiurchienko.sorters.merge;

import com.bogdanaiurchienko.sorters.BubbleBackwardSorter;
import com.bogdanaiurchienko.sorters.AbstractMergeSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;

/**
 * Sorts the array using merge method of half partition and sorting the parts with BubbleBackwardSorter
 * @see com.bogdanaiurchienko.sorters.BubbleBackwardSorter
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Merge bubble backward sort")
public class MergeBackwardBubbleSorter extends AbstractMergeSorter {

  @SuppressWarnings("WeakerAccess")
  public MergeBackwardBubbleSorter() {
  }

  private MergeBackwardBubbleSorter(int[] subArrayToSort) {
    super(subArrayToSort);
    thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run(){
    new BubbleBackwardSorter().sort(this.subArrayToSort);
  }

  @Override
  protected Thread getNewThread(int[] subArrayToSort) {
    return new MergeBackwardBubbleSorter(subArrayToSort).getThread();
  }
}
