package com.bogdanaiurchienko.sorters;

/**
 * Parent class to bubble sorts.
 * @author Bogdana Iurchienko
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractBubbleSorter extends AbstractSorter {
  protected int[] arrayToSort;
  /**
   *
   * @param arrayToSort array of int to be sorted
   * @return sorted array
   */
  public int [] sort(int [] arrayToSort){
    this.arrayToSort = arrayToSort;
    boolean swappedAtLeastOnce;
    for (int i = getFirstOuterIndex(); isLastOuterIndex(i); i = getNextIndex(i)) {
      swappedAtLeastOnce = false;
      for (int j = getFirstInnerIndex(i); isLastInnerIndex(i, j); j = getNextIndex(j)) {
        if (elementsNotInOrder(j)) {
          swappedAtLeastOnce = true;
          swapElements(j);
        }
      }
      if(!swappedAtLeastOnce)
        return arrayToSort;
    }
    return arrayToSort;
  }

  abstract protected int getFirstOuterIndex();
  abstract protected int getFirstInnerIndex(int i);
  abstract protected boolean isLastOuterIndex(int i);
  abstract protected boolean isLastInnerIndex(int i, int j);
  abstract protected boolean elementsNotInOrder(int j);
  abstract protected void swapElements(int j);
  abstract protected int getNextIndex(int i);


}
