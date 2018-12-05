package com.bogdanaiurchienko.sorters;
/**
 * Sorter class that sorts array with bubble method.
 * Each iteration begins from the last element and goes to the first one.
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Bubble backward sort")
public class BubbleBackwardSorter extends AbstractBubbleSorter {

  @Override
  protected int getFirstOuterIndex() {
    return arrayToSort.length - 1;
  }

  @Override
  protected int getFirstInnerIndex(int i) {
    return arrayToSort.length - 1;
  }

  @Override
  protected boolean isLastOuterIndex(int i) {
    return i > 0;
  }

  @Override
  protected boolean isLastInnerIndex(int i, int j) {
    return j > 0;
  }

  @Override
  protected boolean elementsNotInOrder(int j) {
    return arrayToSort[j - 1] > arrayToSort[j];
  }

  @Override
  protected void swapElements(int j) {
    swap(arrayToSort, j,j-1);
  }

  @Override
  protected int getNextIndex(int i) {
    return i - 1;
  }
}
